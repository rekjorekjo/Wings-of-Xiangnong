// 香农之翼 MVP：当前使用前端 mock 数据。
// 后续接入后端 delivery 模块时，只替换本文件内部实现，页面层不直接依赖 mock。

import type { DeliveryTask, Drone } from '@/views/delivery/types'
import { getMockDeliveryTasks, getMockDrones } from './mock'

export const getDeliveryTasks = (): Promise<DeliveryTask[]> => {
  return getMockDeliveryTasks()
}

export const getDrones = (): Promise<Drone[]> => {
  return getMockDrones()
}
