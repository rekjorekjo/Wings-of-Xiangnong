import api from './api'

/**
 * 订单支付 
 */
export function payUnify(data) {
  return api.post(`/order/pay`, data, { login: false })
}

/**
 * getWechatConfig 	
 */
export function getWechatConfig() {
  return api.get(`/member/wx-mp/create-jsapi-signature`, { url: location.href }, { login: false })
}
