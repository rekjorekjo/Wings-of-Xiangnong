import api from './api'
import { formatDateTime } from '@/utils/util'

/**
 * Normalize timestamp to milliseconds
 * - If value < 1000000000000, treat as seconds and multiply by 1000
 * - Otherwise treat as milliseconds
 */
const normalizeTimestamp = (value) => {
  // Threshold: 1000000000000 (approx 2001-09-09 in milliseconds)
  // Values smaller than this are likely seconds, not milliseconds
  return value < 1000000000000 ? value * 1000 : value
}

/**
 * Check if a Date object is valid
 */
const isValidDate = (date) => {
  return date instanceof Date && !isNaN(date.getTime())
}

/**
 * Parse date string like "2026-07-03 17:54:59" into Date object
 * Handles both "yyyy-MM-dd HH:mm:ss" and ISO formats
 */
const parseDateString = (str) => {
  // Try direct Date parse first (works for ISO format)
  const directDate = new Date(str)
  if (isValidDate(directDate)) return directDate

  // Try replacing "T" with space and removing milliseconds/Z
  // Format: yyyy-MM-ddTHH:mm:ss or yyyy-MM-dd HH:mm:ss
  const normalized = str.replace('T', ' ').replace(/\.\d{3}Z?$/, '').replace(/Z$/, '')
  
  // Try parsing normalized string
  const normalizedDate = new Date(normalized)
  if (isValidDate(normalizedDate)) return normalizedDate

  // Manual parse for "yyyy-MM-dd HH:mm:ss" format
  const match = normalized.match(/^(\d{4})-(\d{2})-(\d{2})\s+(\d{2}):(\d{2}):(\d{2})$/)
  if (match) {
    const [, year, month, day, hour, minute, second] = match
    const manualDate = new Date(
      parseInt(year),
      parseInt(month) - 1,
      parseInt(day),
      parseInt(hour),
      parseInt(minute),
      parseInt(second)
    )
    if (isValidDate(manualDate)) return manualDate
  }

  return null
}

/**
 * Normalize time field to formatted string
 * Compatible with string, number (timestamp), null, undefined, empty string
 * Handles both seconds and milliseconds timestamps
 */
const normalizeTime = (time) => {
  // Handle null, undefined, empty string, or whitespace-only string
  if (time === null || time === undefined) return ''
  if (typeof time === 'string') {
    const trimmed = time.trim()
    if (trimmed === '') return ''
  }

  // Handle number timestamp (seconds or milliseconds)
  if (typeof time === 'number') {
    const ms = normalizeTimestamp(time)
    const date = new Date(ms)
    if (isValidDate(date)) {
      return formatDateTime(date)
    }
    return ''
  }

  // Handle string
  if (typeof time === 'string') {
    const trimmed = time.trim()

    // Check if it's a numeric string (timestamp)
    if (/^\d+$/.test(trimmed)) {
      const num = Number(trimmed)
      const ms = normalizeTimestamp(num)
      const date = new Date(ms)
      if (isValidDate(date)) {
        return formatDateTime(date)
      }
      return ''
    }

    // Try parsing as date string
    const date = parseDateString(trimmed)
    if (date && isValidDate(date)) {
      return formatDateTime(date)
    }
    return ''
  }

  return ''
}

export function getOrderDelivery(orderId) {
  return api.get(`/order/${orderId}/delivery`, {}, { login: false }).then((res) => {
    const data = res || {}
    const progress = Array.isArray(data.progress) ? data.progress : []
    
    return {
      orderId: data.orderId || orderId,
      deliveryTaskId: data.deliveryTaskId || '',
      status: data.status || 'pending',
      statusText: data.statusText || '配送任务处理中',
      estimatedArrivalTime: normalizeTime(data.estimatedArrivalTime) || '待确认',
      currentLocation: data.currentLocation || '暂无位置信息',
      progress: progress.map((item) => ({
        status: item.status || '',
        title: item.title || '',
        time: normalizeTime(item.time) || ''
      }))
    }
  })
}
