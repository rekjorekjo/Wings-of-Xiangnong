import api from './api'

/**
 * 订单列表  
 */
export function orderTakeFoods(data) {
  return api.get('/order/list', data, { login: false })
}

/**
 * 订单创建  
 */
export function orderSubmit(data) {
  return api.post(`/order/create`, data, { login: false })
}

/**
 * 订单列表  
 */
export function orderGetOrders(data) {
  return api.get(`/order/list`, data, { login: false })
}

/**
 * 计算详情 
 */
export function orderDetail(data) {
  return api.get(`/order/detail/${data}`, data, { login: false })
}

/**
 * 订单收货 
 */
export function orderReceive(data) {
  return api.post(`/order/take`, data, { login: false })
}

/**
 * 订单退款 
 */
export function orderRefund(data) {
  return api.post(`/order/refund`, data, { login: false })
}
