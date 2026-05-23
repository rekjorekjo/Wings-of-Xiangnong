import api from './api'

export function getOrderDelivery(orderId) {
  return api.get(`/order/${orderId}/delivery`, {}, { login: false }).then((res) => {
    const data = res || {}
    const progress = Array.isArray(data.progress) ? data.progress : []
    
    return {
      orderId: data.orderId || orderId,
      deliveryTaskId: data.deliveryTaskId || '',
      status: data.status || 'pending',
      statusText: data.statusText || '配送任务处理中',
      estimatedArrivalTime: data.estimatedArrivalTime || '暂无预计时间',
      currentLocation: data.currentLocation || '暂无位置信息',
      progress: progress.map((item) => ({
        status: item.status || '',
        title: item.title || '',
        time: item.time || ''
      }))
    }
  })
}
