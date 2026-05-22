// 香农之翼 MVP：订单配送进度前端 mock。
// 后续接入 backend delivery API 时，优先替换本文件实现。

export function getOrderDelivery(orderId) {
  const mockData = {
    orderId,
    deliveryTaskId: 'DT-' + orderId,
    status: 'in_flight',
    statusText: '无人机配送中',
    estimatedArrivalTime: '约 8 分钟',
    currentLocation: '九龙湖校区配送航线',
    progress: [
      {
        status: 'created',
        title: '配送任务已创建',
        time: '14:20'
      },
      {
        status: 'waiting_pickup',
        title: '等待无人机取货',
        time: '14:23'
      },
      {
        status: 'in_flight',
        title: '无人机配送中',
        time: '14:28'
      },
      {
        status: 'arrived',
        title: '预计送达站点',
        time: '14:36'
      }
    ]
  }
  
  return Promise.resolve(mockData)
}
