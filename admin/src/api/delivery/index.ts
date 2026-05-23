import request from '@/config/axios'
import type { DeliveryTask, Drone } from '@/views/delivery/types'

export interface DeliveryTaskQuery {
  status?: string
  droneNo?: string
  orderNo?: string
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
  createdAt?: string | null
}

interface RawDrone {
  id?: number | string
  droneNo?: string | null
  status?: string | null
  statusText?: string | null
  battery?: number | null
  location?: string | null
  payload?: string | null
  lastUpdatedAt?: string | null
}

const normalizeTime = (time?: string | null): string => {
  if (!time || typeof time !== 'string') return ''
  return time.replace('T', ' ').replace(/\.\d{3}Z?$/, '').replace(/Z$/, '')
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
  const response = await request.get<{ list?: RawDeliveryTask[] }>('/delivery/tasks', { params })
  const list = response?.data?.list || response?.data || []
  return (Array.isArray(list) ? list : []).map(normalizeDeliveryTask)
}

export const getDrones = async (): Promise<Drone[]> => {
  const response = await request.get<RawDrone[]>('/delivery/drones')
  const list = response?.data || []
  return (Array.isArray(list) ? list : []).map(normalizeDrone)
}
