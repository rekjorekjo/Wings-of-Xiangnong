export type TagType = '' | 'success' | 'warning' | 'info' | 'primary' | 'danger'

export type TaskStatus = 'pending' | 'assigned' | 'flying' | 'arrived' | 'completed' | 'exception'

export type DroneStatus = 'idle' | 'assigned' | 'flying' | 'charging' | 'maintenance'

export interface DeliveryTask {
  id: number
  orderNo: string
  pickupSite: string
  dropoffSite: string
  droneNo: string
  status: TaskStatus
  statusText: string
  progress: number
  etaMinutes: number
  createdAt: string // Formatted display string, raw value is string | number | null
}

export interface Drone {
  id: number
  droneNo: string
  status: DroneStatus
  statusText: string
  battery: number
  location: string
  payload: string
  lastUpdatedAt: string // Formatted display string, raw value is string | number | null
}

export interface DeliveryPoint {
  id: number
  code: string
  name: string
  address: string
  latitude: string | number
  longitude: string | number
  flightAltitude: string | number
  enabled: boolean
  verified: boolean
  sort: number
  remark?: string
  distanceMeters?: number | null
  recommended: boolean
}
