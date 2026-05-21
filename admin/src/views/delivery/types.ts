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
  createdAt: string
}

export interface Drone {
  id: number
  droneNo: string
  status: DroneStatus
  statusText: string
  battery: number
  location: string
  payload: string
  lastUpdatedAt: string
}
