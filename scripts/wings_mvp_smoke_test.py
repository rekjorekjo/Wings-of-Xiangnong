#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Wings of Xiangnong MVP smoke test script.

Purpose:
  Quickly check whether the existing system's MVP-facing backend APIs are reachable
  and whether the delivery mock/management APIs are still alive.

This is NOT a full automated test suite.
It is a smoke test / regression probe.

Usage from repository root:
  python scripts/wings_mvp_smoke_test.py

With custom server:
  python scripts/wings_mvp_smoke_test.py --base-url http://localhost:48080

With credentials:
  python scripts/wings_mvp_smoke_test.py --username admin --password your_password

Environment variables:
  WINGS_BASE_URL
  WINGS_ADMIN_USERNAME
  WINGS_ADMIN_PASSWORD

Outputs:
  .wings-test/smoke-test-report.md
  .wings-test/smoke-test-report.json

No third-party dependencies.
"""

from __future__ import annotations

import argparse
import json
import os
import sys
import time
import urllib.error
import urllib.request
from datetime import datetime
from pathlib import Path
from typing import Any, Dict, List, Optional


DEFAULT_ENDPOINTS = [
    {
        "name": "admin permission info",
        "method": "GET",
        "path": "/admin-api/system/auth/get-permission-info",
        "requires_auth": True,
        "critical": False,
        "accept": [200, 401, 403],
    },
    {
        "name": "delivery task list",
        "method": "GET",
        "path": "/admin-api/delivery/tasks",
        "requires_auth": True,
        "critical": True,
        "accept": [200, 401, 403],
    },
    {
        "name": "delivery drone list",
        "method": "GET",
        "path": "/admin-api/delivery/drones",
        "requires_auth": True,
        "critical": True,
        "accept": [200, 401, 403],
    },
    {
        "name": "admin product list candidate 1",
        "method": "GET",
        "path": "/admin-api/product/store-product/page?pageNo=1&pageSize=1",
        "requires_auth": True,
        "critical": False,
        "accept": [200, 401, 403, 404],
    },
    {
        "name": "admin product list candidate 2",
        "method": "GET",
        "path": "/admin-api/product/storeProduct/page?pageNo=1&pageSize=1",
        "requires_auth": True,
        "critical": False,
        "accept": [200, 401, 403, 404],
    },
    {
        "name": "admin order list candidate",
        "method": "GET",
        "path": "/admin-api/order/store-order/page?pageNo=1&pageSize=1",
        "requires_auth": True,
        "critical": False,
        "accept": [200, 401, 403, 404],
    },
    {
        "name": "app product/category candidate",
        "method": "GET",
        "path": "/app-api/product/category/list",
        "requires_auth": False,
        "critical": False,
        "accept": [200, 401, 403, 404],
    },
    {
        "name": "app store/product candidate",
        "method": "GET",
        "path": "/app-api/product/store-product/list",
        "requires_auth": False,
        "critical": False,
        "accept": [200, 401, 403, 404],
    },
]


LOGIN_CANDIDATES = [
    {
        "name": "standard admin login",
        "path": "/admin-api/system/auth/login",
        "payload": lambda username, password: {
            "username": username,
            "password": password,
        },
    },
    {
        "name": "admin login with empty captchaVerification",
        "path": "/admin-api/system/auth/login",
        "payload": lambda username, password: {
            "username": username,
            "password": password,
            "captchaVerification": "",
        },
    },
    {
        "name": "admin login with rememberMe",
        "path": "/admin-api/system/auth/login",
        "payload": lambda username, password: {
            "username": username,
            "password": password,
            "rememberMe": True,
        },
    },
]


def now_iso() -> str:
    return datetime.now().isoformat(timespec="seconds")


def join_url(base: str, path: str) -> str:
    return base.rstrip("/") + "/" + path.lstrip("/")


def extract_token(payload: Any) -> Optional[str]:
    if not isinstance(payload, dict):
        return None

    candidates = [
        payload.get("accessToken"),
        payload.get("token"),
        payload.get("access_token"),
    ]

    data = payload.get("data")
    if isinstance(data, dict):
        candidates.extend([
            data.get("accessToken"),
            data.get("token"),
            data.get("access_token"),
        ])

    for item in candidates:
        if isinstance(item, str) and item.strip():
            return item.strip()

    return None


def decode_json_maybe(data: bytes) -> Any:
    if not data:
        return None
    text = data.decode("utf-8", errors="replace")
    try:
        return json.loads(text)
    except Exception:
        return text[:1000]


def request_json(
    base_url: str,
    method: str,
    path: str,
    token: Optional[str] = None,
    payload: Optional[Dict[str, Any]] = None,
    timeout: float = 8.0,
) -> Dict[str, Any]:
    url = join_url(base_url, path)
    headers = {
        "Accept": "application/json, text/plain, */*",
        "User-Agent": "WingsMvpSmokeTest/1.0",
    }

    body = None
    if payload is not None:
        body = json.dumps(payload, ensure_ascii=False).encode("utf-8")
        headers["Content-Type"] = "application/json"

    if token:
        headers["Authorization"] = f"Bearer {token}"

    req = urllib.request.Request(url, data=body, method=method.upper(), headers=headers)
    started = time.time()

    try:
        with urllib.request.urlopen(req, timeout=timeout) as resp:
            raw = resp.read()
            elapsed_ms = round((time.time() - started) * 1000)
            return {
                "ok": True,
                "status": resp.status,
                "elapsed_ms": elapsed_ms,
                "url": url,
                "body": decode_json_maybe(raw),
                "error": "",
            }
    except urllib.error.HTTPError as exc:
        raw = exc.read()
        elapsed_ms = round((time.time() - started) * 1000)
        return {
            "ok": False,
            "status": exc.code,
            "elapsed_ms": elapsed_ms,
            "url": url,
            "body": decode_json_maybe(raw),
            "error": str(exc),
        }
    except Exception as exc:
        elapsed_ms = round((time.time() - started) * 1000)
        return {
            "ok": False,
            "status": None,
            "elapsed_ms": elapsed_ms,
            "url": url,
            "body": None,
            "error": repr(exc),
        }


def load_extra_endpoints(config_path: Optional[str]) -> List[Dict[str, Any]]:
    if not config_path:
        return []
    path = Path(config_path)
    if not path.exists():
        raise FileNotFoundError(f"Config file does not exist: {path}")
    data = json.loads(path.read_text(encoding="utf-8"))
    endpoints = data.get("endpoints", []) if isinstance(data, dict) else data
    if not isinstance(endpoints, list):
        raise ValueError("Config must be a list or an object with endpoints list.")
    return endpoints


def summarize_body(body: Any) -> str:
    if body is None:
        return ""
    if isinstance(body, str):
        return body[:300]
    if isinstance(body, dict):
        code = body.get("code")
        msg = body.get("msg") or body.get("message") or body.get("error_description") or body.get("error")
        data = body.get("data")
        if isinstance(data, dict):
            keys = ",".join(list(data.keys())[:8])
            return f"code={code}, msg={msg}, data_keys={keys}"
        return f"code={code}, msg={msg}"
    return str(body)[:300]


def probe_backend(base_url: str) -> Dict[str, Any]:
    candidates = [
        "/",
        "/admin-api",
        "/app-api",
        "/actuator/health",
        "/admin-api/system/auth/get-permission-info",
    ]
    results = []
    for path in candidates:
        res = request_json(base_url, "GET", path, timeout=4.0)
        item = {
            "path": path,
            "status": res["status"],
            "elapsed_ms": res["elapsed_ms"],
            "error": res["error"],
        }
        results.append(item)
        if res["status"] is not None:
            return {
                "reachable": True,
                "first_response": item,
                "attempts": results,
            }
    return {
        "reachable": False,
        "first_response": None,
        "attempts": results,
    }


def attempt_login(base_url: str, username: str, password: str) -> Dict[str, Any]:
    attempts = []
    for candidate in LOGIN_CANDIDATES:
        payload = candidate["payload"](username, password)
        res = request_json(base_url, "POST", candidate["path"], payload=payload, timeout=8.0)
        token = extract_token(res.get("body"))
        attempts.append({
            "name": candidate["name"],
            "path": candidate["path"],
            "status": res["status"],
            "elapsed_ms": res["elapsed_ms"],
            "token_found": bool(token),
            "error": res["error"],
            "body_summary": summarize_body(res.get("body")),
        })
        if token:
            return {
                "success": True,
                "token": token,
                "attempts": attempts,
            }
    return {
        "success": False,
        "token": None,
        "attempts": attempts,
    }


def run_endpoint(endpoint: Dict[str, Any], base_url: str, token: Optional[str]) -> Dict[str, Any]:
    method = endpoint.get("method", "GET")
    path = endpoint["path"]
    requires_auth = bool(endpoint.get("requires_auth", False))
    critical = bool(endpoint.get("critical", False))
    accept = endpoint.get("accept", [200])
    payload = endpoint.get("payload")

    use_token = token if requires_auth else None
    res = request_json(base_url, method, path, token=use_token, payload=payload, timeout=10.0)
    status = res.get("status")

    accepted = status in accept
    auth_blocked = requires_auth and not token and status in (401, 403)
    passed = accepted or auth_blocked

    return {
        "name": endpoint.get("name", path),
        "method": method,
        "path": path,
        "url": res["url"],
        "status": status,
        "elapsed_ms": res["elapsed_ms"],
        "requires_auth": requires_auth,
        "critical": critical,
        "accepted_statuses": accept,
        "passed": passed,
        "auth_blocked": auth_blocked,
        "body_summary": summarize_body(res.get("body")),
        "error": res["error"],
    }


def write_markdown(report: Dict[str, Any], path: Path) -> None:
    def table(rows: List[Dict[str, Any]], columns: List[str]) -> str:
        if not rows:
            return "_None._\n"
        lines = []
        lines.append("| " + " | ".join(columns) + " |")
        lines.append("| " + " | ".join(["---"] * len(columns)) + " |")
        for row in rows:
            vals = []
            for c in columns:
                value = row.get(c, "")
                vals.append(str(value).replace("|", "\\|").replace("\n", " "))
            lines.append("| " + " | ".join(vals) + " |")
        return "\n".join(lines) + "\n"

    md = []
    md.append("# Wings MVP Smoke Test Report\n")
    md.append(f"- Generated at: `{report['generated_at']}`")
    md.append(f"- Base URL: `{report['base_url']}`")
    md.append(f"- Critical failures: `{report['summary']['critical_failures']}`")
    md.append(f"- Warnings: `{report['summary']['warnings']}`")
    md.append(f"- Login success: `{report['login']['success']}`\n")

    md.append("## Backend reachability\n")
    md.append(f"- Reachable: `{report['backend']['reachable']}`\n")
    md.append(table(report["backend"]["attempts"], ["path", "status", "elapsed_ms", "error"]))

    md.append("## Login attempts\n")
    md.append(table(report["login"]["attempts"], ["name", "path", "status", "token_found", "elapsed_ms", "body_summary"]))

    md.append("## Endpoint probes\n")
    md.append(table(report["endpoints"], [
        "passed", "critical", "name", "method", "path", "status",
        "elapsed_ms", "auth_blocked", "body_summary", "error"
    ]))

    md.append("## Interpretation\n")
    md.append("- `passed=True` means the endpoint returned an accepted status, or auth blocked it in an expected way.")
    md.append("- `auth_blocked=True` usually means the route responded but a valid token was unavailable.")
    md.append("- A 404 on a candidate product/order endpoint means the guessed route may not match this project; update the custom config after confirming real routes.")
    md.append("- This script does not replace manual miniapp UI testing.\n")

    path.write_text("\n".join(md), encoding="utf-8")


def main() -> int:
    parser = argparse.ArgumentParser(description="Run Wings MVP backend smoke probes.")
    parser.add_argument("--base-url", default=os.getenv("WINGS_BASE_URL", "http://localhost:48080"))
    parser.add_argument("--username", default=os.getenv("WINGS_ADMIN_USERNAME", "admin"))
    parser.add_argument("--password", default=os.getenv("WINGS_ADMIN_PASSWORD", "123456"))
    parser.add_argument("--config", default="", help="Optional JSON file with additional endpoints.")
    parser.add_argument("--out", default=".wings-test", help="Output directory. Default: .wings-test")
    args = parser.parse_args()

    base_url = args.base_url.rstrip("/")
    out_dir = Path(args.out)
    out_dir.mkdir(parents=True, exist_ok=True)

    try:
        extra_endpoints = load_extra_endpoints(args.config) if args.config else []
    except Exception as exc:
        print(f"ERROR: failed to load config: {exc}", file=sys.stderr)
        return 2

    print(f"[smoke] base URL: {base_url}")
    print("[smoke] probing backend reachability...")
    backend = probe_backend(base_url)

    print("[smoke] attempting admin login...")
    login = attempt_login(base_url, args.username, args.password)
    token = login.get("token")

    endpoints = DEFAULT_ENDPOINTS + extra_endpoints
    results = []

    print(f"[smoke] probing {len(endpoints)} endpoints...")
    for ep in endpoints:
        result = run_endpoint(ep, base_url, token)
        results.append(result)
        status = result["status"]
        ok = "OK" if result["passed"] else "FAIL"
        print(f"[{ok}] {ep.get('name', ep['path'])}: status={status}, elapsed={result['elapsed_ms']}ms")

    critical_failures = [r for r in results if r.get("critical") and not r.get("passed")]
    warnings = [r for r in results if not r.get("critical") and not r.get("passed")]

    if not backend["reachable"]:
        critical_failures.append({
            "name": "backend reachability",
            "path": base_url,
            "status": None,
            "error": "backend did not respond",
        })

    report = {
        "generated_at": now_iso(),
        "base_url": base_url,
        "backend": backend,
        "login": {
            "success": bool(login.get("success")),
            "attempts": login.get("attempts", []),
            "token_found": bool(token),
        },
        "endpoints": results,
        "summary": {
            "critical_failures": len(critical_failures),
            "warnings": len(warnings),
        },
    }

    json_path = out_dir / "smoke-test-report.json"
    md_path = out_dir / "smoke-test-report.md"
    json_path.write_text(json.dumps(report, ensure_ascii=False, indent=2), encoding="utf-8")
    write_markdown(report, md_path)

    print(f"[smoke] wrote: {md_path}")
    print(f"[smoke] wrote: {json_path}")

    if critical_failures:
        print(f"[smoke] critical failures: {len(critical_failures)}")
        return 1

    print("[smoke] no critical failures.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
