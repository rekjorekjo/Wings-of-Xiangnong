# package.ps1
# 用途：打包香农之翼项目代码，方便上传给 ChatGPT 审核
# 特点：排除 node_modules、target、.git、dist、unpackage、zip、sql、日志等

$ErrorActionPreference = "Stop"

$Root = Split-Path -Parent $MyInvocation.MyCommand.Path
$ProjectName = "wings-of-xiangnong"
$Timestamp = Get-Date -Format "yyyyMMdd-HHmmss"

$OutputDir = Join-Path $Root "_upload_packages"
$StageDir = Join-Path $OutputDir "_staging_$Timestamp"
$ZipPath = Join-Path $OutputDir "$ProjectName-$Timestamp.zip"

if (!(Test-Path $OutputDir)) {
    New-Item -ItemType Directory -Path $OutputDir | Out-Null
}

if (Test-Path $StageDir) {
    Remove-Item $StageDir -Recurse -Force
}

New-Item -ItemType Directory -Path $StageDir | Out-Null

# 需要排除的目录名
$ExcludeDirs = @(
    ".git",
    ".idea",
    ".vscode",
    "node_modules",
    "target",
    "dist",
    "build",
    "unpackage",
    ".gradle",
    ".mvn",
    "_upload_packages",
    "assets"
)

# 需要排除的文件名
$ExcludeFileNames = @(
    ".DS_Store",
    "Thumbs.db",
    "package-lock.json"
)

# 需要排除的扩展名
$ExcludeExtensions = @(
    ".zip",
    ".rar",
    ".7z",
    ".tar",
    ".gz",
    ".log",
    ".sql"
)

# 可选：排除可能包含本地隐私/密钥的文件
$ExcludeSensitivePatterns = @(
    ".env.local",
    ".env.*.local",
    "application-prod.yaml",
    "application-prod.yml",
    "application-dev-secret.yaml",
    "application-dev-secret.yml"
)

function Should-ExcludeFile {
    param (
        [System.IO.FileInfo] $File
    )

    $relativePath = $File.FullName.Substring($Root.Length).TrimStart([char[]]@('\', '/'))
    $parts = $relativePath -split "[\\/]"

    foreach ($dir in $ExcludeDirs) {
        if ($parts -contains $dir) {
            return $true
        }
    }

    if ($ExcludeFileNames -contains $File.Name) {
        return $true
    }

    if ($ExcludeExtensions -contains $File.Extension.ToLower()) {
        return $true
    }

    foreach ($pattern in $ExcludeSensitivePatterns) {
        if ($File.Name -like $pattern) {
            return $true
        }
    }

    return $false
}

Write-Host ""
Write-Host "开始收集文件..." -ForegroundColor Cyan

$files = Get-ChildItem -Path $Root -Recurse -File | Where-Object {
    -not (Should-ExcludeFile $_)
}

$count = 0

foreach ($file in $files) {
    $relativePath = $file.FullName.Substring($Root.Length).TrimStart([char[]]@('\', '/'))
    $targetPath = Join-Path $StageDir $relativePath
    $targetDir = Split-Path -Parent $targetPath

    if (!(Test-Path $targetDir)) {
        New-Item -ItemType Directory -Path $targetDir -Force | Out-Null
    }

    Copy-Item -LiteralPath $file.FullName -Destination $targetPath -Force
    $count++
}

if (Test-Path $ZipPath) {
    Remove-Item $ZipPath -Force
}

Write-Host "开始压缩..." -ForegroundColor Cyan

Compress-Archive -Path (Join-Path $StageDir "*") -DestinationPath $ZipPath -Force

Remove-Item $StageDir -Recurse -Force

$sizeMb = [Math]::Round((Get-Item $ZipPath).Length / 1MB, 2)

Write-Host ""
Write-Host "打包完成：" -ForegroundColor Green
Write-Host $ZipPath
Write-Host ""
Write-Host "文件数量：$count"
Write-Host "压缩包大小：$sizeMb MB"
Write-Host ""
Write-Host "你可以上传这个 zip："
Write-Host $ZipPath