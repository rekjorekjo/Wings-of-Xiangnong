import type { TagType } from './types'

export const getDroneStatusType = (status: string): TagType => {
  const statusMap: Record<string, TagType> = {
    idle: 'success',
    assigned: 'warning',
    flying: 'primary',
    charging: 'info',
    maintenance: 'danger'
  }
  return statusMap[status] || 'info'
}

export const getTaskStatusType = (status: string): TagType => {
  const statusMap: Record<string, TagType> = {
    pending: 'info',
    assigned: 'warning',
    flying: 'primary',
    arrived: 'warning',
    completed: 'success',
    exception: 'danger'
  }
  return statusMap[status] || 'info'
}

export const getBatteryColor = (battery: number): string => {
  if (battery >= 80) return '#67c23a'
  if (battery >= 50) return '#e6a23c'
  return '#f56c6c'
}

export const getProgressColor = (progress: number): string => {
  if (progress >= 100) return '#67c23a'
  if (progress >= 50) return '#409eff'
  return '#e6a23c'
}
