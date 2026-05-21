export interface DeliveryTask {
  id: number
  orderNo: string
  pickupSite: string
  dropoffSite: string
  droneNo: string
  status: 'pending' | 'assigned' | 'flying' | 'arrived' | 'completed' | 'exception'
  statusText: string
  progress: number
  etaMinutes: number
  createdAt: string
}

export interface Drone {
  id: number
  droneNo: string
  status: 'idle' | 'assigned' | 'flying' | 'charging' | 'maintenance'
  statusText: string
  battery: number
  location: string
  payload: string
  lastUpdatedAt: string
}

const deliveryTasks: DeliveryTask[] = [
  {
    id: 1,
    orderNo: 'ORD20250120001',
    pickupSite: '香农之翼-中心站',
    dropoffSite: '香农之翼-东区站',
    droneNo: 'DR-001',
    status: 'flying',
    statusText: '配送中',
    progress: 65,
    etaMinutes: 8,
    createdAt: '2025-01-20 10:30:00'
  },
  {
    id: 2,
    orderNo: 'ORD20250120002',
    pickupSite: '香农之翼-西区站',
    dropoffSite: '香农之翼-中心站',
    droneNo: 'DR-002',
    status: 'assigned',
    statusText: '已分配',
    progress: 0,
    etaMinutes: 15,
    createdAt: '2025-01-20 10:35:00'
  },
  {
    id: 3,
    orderNo: 'ORD20250120003',
    pickupSite: '香农之翼-中心站',
    dropoffSite: '香农之翼-南区站',
    droneNo: '',
    status: 'pending',
    statusText: '待分配',
    progress: 0,
    etaMinutes: 0,
    createdAt: '2025-01-20 10:40:00'
  },
  {
    id: 4,
    orderNo: 'ORD20250120004',
    pickupSite: '香农之翼-东区站',
    dropoffSite: '香农之翼-西区站',
    droneNo: 'DR-001',
    status: 'completed',
    statusText: '已完成',
    progress: 100,
    etaMinutes: 0,
    createdAt: '2025-01-20 09:15:00'
  },
  {
    id: 5,
    orderNo: 'ORD20250120005',
    pickupSite: '香农之翼-南区站',
    dropoffSite: '香农之翼-中心站',
    droneNo: 'DR-004',
    status: 'exception',
    statusText: '异常',
    progress: 30,
    etaMinutes: 0,
    createdAt: '2025-01-20 10:00:00'
  },
  {
    id: 6,
    orderNo: 'ORD20250120006',
    pickupSite: '香农之翼-中心站',
    dropoffSite: '香农之翼-北区站',
    droneNo: 'DR-002',
    status: 'arrived',
    statusText: '已到达',
    progress: 90,
    etaMinutes: 2,
    createdAt: '2025-01-20 10:20:00'
  },
  {
    id: 7,
    orderNo: 'ORD20250120007',
    pickupSite: '香农之翼-北区站',
    dropoffSite: '香农之翼-东区站',
    droneNo: '',
    status: 'pending',
    statusText: '待分配',
    progress: 0,
    etaMinutes: 0,
    createdAt: '2025-01-20 11:00:00'
  }
]

const drones: Drone[] = [
  {
    id: 1,
    droneNo: 'DR-001',
    status: 'flying',
    statusText: '配送中',
    battery: 72,
    location: '香农之翼-东区站上空',
    payload: '订单 ORD20250120001',
    lastUpdatedAt: '2025-01-20 10:45:00'
  },
  {
    id: 2,
    droneNo: 'DR-002',
    status: 'assigned',
    statusText: '已分配',
    battery: 95,
    location: '香农之翼-西区站',
    payload: '订单 ORD20250120002',
    lastUpdatedAt: '2025-01-20 10:40:00'
  },
  {
    id: 3,
    droneNo: 'DR-003',
    status: 'idle',
    statusText: '空闲',
    battery: 100,
    location: '香农之翼-中心站',
    payload: '无',
    lastUpdatedAt: '2025-01-20 10:30:00'
  },
  {
    id: 4,
    droneNo: 'DR-004',
    status: 'maintenance',
    statusText: '维护中',
    battery: 45,
    location: '香农之翼-中心站维修区',
    payload: '无',
    lastUpdatedAt: '2025-01-20 09:00:00'
  }
]

export const getMockDeliveryTasks = (): Promise<DeliveryTask[]> => {
  return Promise.resolve(deliveryTasks.map(item => ({ ...item })))
}

export const getMockDrones = (): Promise<Drone[]> => {
  return Promise.resolve(drones.map(item => ({ ...item })))
}
