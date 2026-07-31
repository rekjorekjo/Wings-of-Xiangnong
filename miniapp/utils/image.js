// 图片 URL 规范化工具
// 修复旧端口 48081 -> 当前后端端口，统一从配置派生文件服务地址

import { VUE_APP_API_URL } from '@/config'

// 从 API URL 提取基础地址，例如 http://localhost:18081
const API_BASE = VUE_APP_API_URL.replace(/\/app-api$/, '')

// 旧端口列表，这些端口在数据库图片字段中可能残留
const OLD_PORT_PATTERNS = [
  /http:\/\/localhost:48081/g,
  /http:\/\/127\.0\.0\.1:48081/g
]

/**
 * 规范化图片 URL
 * - 将旧端口 48081 替换为当前 API 端口
 * - 处理相对路径（以 / 开头但不以 // 开头）
 * - null/undefined/空字符串返回空字符串
 */
export function normalizeImageUrl(url) {
  if (!url || typeof url !== 'string') return ''
  let result = url.trim()
  if (!result) return ''
  if (result.startsWith('/static/') || result.startsWith('data:')) return result

  // 替换旧端口
  for (const pattern of OLD_PORT_PATTERNS) {
    result = result.replace(pattern, API_BASE)
  }

  if (result.startsWith('/') && !result.startsWith('//')) {
    return `${API_BASE}${result}`
  }

  return result
}
