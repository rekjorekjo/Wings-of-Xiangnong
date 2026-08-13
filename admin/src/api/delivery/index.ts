import request from '@/config/axios'
import dayjs from 'dayjs'
import type { DeliveryPoint, DeliveryTask, Drone } from '@/views/delivery/types'

export interface DeliveryTaskQuery {
  status?: string
  droneNo?: string
  orderNo?: string
}

export interface WaypointMissionQuery {
  destinationLatitude: string | number
  destinationLongitude: string | number
  startLatitude?: string | number
  startLongitude?: string | number
  homeAltitude?: string | number
  flightAltitude?: string | number
}

export interface DeliveryPointQuery {
  userLatitude?: string | number
  userLongitude?: string | number
}

export interface DeliveryPointSaveReq {
  code: string
  name: string
  address?: string
  latitude: string | number
  longitude: string | number
  flightAltitude: string | number
  enabled: boolean
  verified: boolean
  sort?: number
  remark?: string
}

interface RawDeliveryTask {
  id?: number | string
  orderNo?: string | null
  pickupSite?: string | null
  dropoffSite?: string | null
  droneNo?: string | null
  status?: string | null
  statusText?: string | null
  progress?: number | null
  etaMinutes?: number | null
  createdAt?: string | number | null
}

interface RawDrone {
  id?: number | string
  droneNo?: string | null
  status?: string | null
  statusText?: string | null
  battery?: number | null
  location?: string | null
  payload?: string | null
  lastUpdatedAt?: string | number | null
}

/**
 * Normalize timestamp to milliseconds
 * - If value < 1000000000000, treat as seconds and multiply by 1000
 * - Otherwise treat as milliseconds
 */
const normalizeTimestamp = (value: number): number => {
  // Threshold: 1000000000000 (approx 2001-09-09 in milliseconds)
  // Values smaller than this are likely seconds, not milliseconds
  return value < 1000000000000 ? value * 1000 : value
}

/**
 * Normalize time field to YYYY-MM-DD HH:mm:ss format
 * Compatible with string, number (timestamp), null, undefined, empty string
 * Handles both seconds and milliseconds timestamps
 */
const normalizeTime = (time?: string | number | null): string => {
  // Handle null, undefined, empty string, or whitespace-only string
  if (time === null || time === undefined) return ''
  if (typeof time === 'string') {
    const trimmed = time.trim()
    if (trimmed === '') return ''
  }

  // Handle number timestamp (seconds or milliseconds)
  if (typeof time === 'number') {
    const ms = normalizeTimestamp(time)
    const parsed = dayjs(ms)
    return parsed.isValid() ? parsed.format('YYYY-MM-DD HH:mm:ss') : ''
  }

  // Handle string
  if (typeof time === 'string') {
    const trimmed = time.trim()

    // Check if it's a numeric string (timestamp)
    if (/^\d+$/.test(trimmed)) {
      const num = Number(trimmed)
      const ms = normalizeTimestamp(num)
      const parsed = dayjs(ms)
      return parsed.isValid() ? parsed.format('YYYY-MM-DD HH:mm:ss') : ''
    }

    // ISO string or other date format - use dayjs to parse
    const parsed = dayjs(trimmed)
    return parsed.isValid() ? parsed.format('YYYY-MM-DD HH:mm:ss') : ''
  }

  return ''
}

const normalizeDeliveryTask = (raw: RawDeliveryTask): DeliveryTask => ({
  id: raw.id != null ? Number(raw.id) : 0,
  orderNo: raw.orderNo || '',
  pickupSite: raw.pickupSite || '',
  dropoffSite: raw.dropoffSite || '',
  droneNo: raw.droneNo || '',
  status: (raw.status as DeliveryTask['status']) || 'pending',
  statusText: raw.statusText || '',
  progress: raw.progress ?? 0,
  etaMinutes: raw.etaMinutes ?? 0,
  createdAt: normalizeTime(raw.createdAt)
})

const normalizeDrone = (raw: RawDrone): Drone => ({
  id: raw.id != null ? Number(raw.id) : 0,
  droneNo: raw.droneNo || '',
  status: (raw.status as Drone['status']) || 'idle',
  statusText: raw.statusText || '',
  battery: raw.battery ?? 0,
  location: raw.location || '',
  payload: raw.payload || '无',
  lastUpdatedAt: normalizeTime(raw.lastUpdatedAt)
})

export const getDeliveryTasks = async (params?: DeliveryTaskQuery): Promise<DeliveryTask[]> => {
  const list = await request.get<RawDeliveryTask[]>({
    url: '/delivery/tasks',
    params
  })
  return (Array.isArray(list) ? list : []).map(normalizeDeliveryTask)
}

export const getDrones = async (): Promise<Drone[]> => {
  const list = await request.get<RawDrone[]>({
    url: '/delivery/drones'
  })
  return (Array.isArray(list) ? list : []).map(normalizeDrone)
}

export const getDeliveryPoints = async (params?: DeliveryPointQuery): Promise<DeliveryPoint[]> => {
  const list = await request.get<DeliveryPoint[]>({
    url: '/delivery/points',
    params
  })
  return Array.isArray(list) ? list : []
}

export const createDeliveryPoint = async (data: DeliveryPointSaveReq): Promise<number> => {
  return await request.post<number>({
    url: '/delivery/points',
    data
  })
}

export const updateDeliveryPoint = async (
  id: number,
  data: DeliveryPointSaveReq
): Promise<boolean> => {
  return await request.put<boolean>({
    url: `/delivery/points/${id}`,
    data
  })
}

export const deleteDeliveryPoint = async (id: number): Promise<boolean> => {
  return await request.delete<boolean>({
    url: `/delivery/points/${id}`
  })
}

export const recommendDeliveryPoint = async (
  params: Required<DeliveryPointQuery>
): Promise<DeliveryPoint> => {
  return await request.get<DeliveryPoint>({
    url: '/delivery/points/recommend',
    params
  })
}

export const downloadWaypointMission = async (
  taskId: number,
  params: WaypointMissionQuery
): Promise<Blob> => {
  return await request.download<Blob>({
    url: `/delivery/tasks/${taskId}/waypoints`,
    params
  })
}

export const previewWaypointMission = async (
  taskId: number,
  params: WaypointMissionQuery
): Promise<string> => {
  return await request.get<string>({
    url: `/delivery/tasks/${taskId}/waypoints/preview`,
    params
  })
}
