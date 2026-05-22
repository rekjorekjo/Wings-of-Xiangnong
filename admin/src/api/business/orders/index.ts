import request from '@/config/axios'

export interface OrderVO {
  id: number
  orderId: string
  extendOrderId: string
  uid: number
  realName: string
  userPhone: string
  userAddress: string
  cartId: string
  freightPrice: number
  totalNum: number
  totalPrice: number
  totalPostage: number
  payPrice: number
  payPostage: number
  deductionPrice: number
  couponId: number
  couponPrice: number
  paid: byte
  payTime: Date
  payType: string
  status: boolean
  refundStatus: byte
  refundReasonWapImg: string
  refundReasonWapExplain: string
  refundReasonTime: Date
  refundReasonWap: string
  refundReason: string
  refundPrice: number
  deliverySn: string
  deliveryName: string
  deliveryType: string
  deliveryId: string
  gainIntegral: number
  useIntegral: number
  payIntegral: number
  backIntegral: number
  mark: string
  unique: string
  remark: string
  merId: number
  combinationId: number
  pinkId: number
  cost: number
  seckillId: number
  bargainId: number
  verifyCode: string
  storeId: number
  shippingType: boolean
  isChannel: byte
  isSystemDel: boolean
}

// 兼容旧 yshop 类型名，后续逐步迁移调用方到新类型名。
export type StoreOrderVO = OrderVO

export type OrderPageReqVO = Record<string, any>
export type StoreOrderPageReqVO = OrderPageReqVO

export type OrderExportReqVO = Record<string, any>
export type StoreOrderExportReqVO = OrderExportReqVO

export const getOrderPage = async (params: OrderPageReqVO) => {
  return await request.get({ url: `/order/store-order/page`, params })
}

export const getOrder = async (id: number) => {
  return await request.get({ url: `/order/store-order/get?id=` + id })
}

export const createOrder = async (data: StoreOrderVO) => {
  return await request.post({ url: `/order/store-order/create`, data })
}

export const updateOrder = async (data: StoreOrderVO) => {
  return await request.put({ url: `/order/store-order/update`, data })
}

export const deleteOrder = async (id: number) => {
  return await request.delete({ url: `/order/store-order/delete?id=` + id })
}

export const payOrder = async (id: number) => {
  return await request.get({ url: `/order/store-order/pay?id=` + id })
}

export const takeOrder = async (id: number) => {
  return await request.get({ url: `/order/store-order/take?id=` + id })
}

export const refundOrder = async (data) => {
  return await request.post({ url: `/order/store-order/refund`,data })
}

export const getOrderRecordList = async (id: number) => {
  return await request.get({ url: `/order/store-order/record-list?id=` + id })
}

export const exportOrder = async (params: OrderExportReqVO) => {
  return await request.download({ url: `/order/store-order/export-excel`, params })
}

export const getLogistic = async (param1,param2) => {
  return await request.get({ url: `/order/express/getLogistic?shipperCode=` + param1 + `&logisticCode=` + param2})
}

export const getOrderHtml = async (param1,param2) => {
  return await request.get({ url: `/order/store-order/printOrder?id=` + param1 + `&electId=` + param2})
}

export const getOrderStats = async () => {
  return await request.get({ url: `/order/store-order/count`})
}

export const orderNoticeUrl = async () => {
  return await request.get({ url: `/order/store-order/notice`})
}

// 兼容旧 yshop API 函数名，调用方逐步迁移到新命名。
export const getStoreOrderPage = getOrderPage
export const getStoreOrder = getOrder
export const createStoreOrder = createOrder
export const updateStoreOrder = updateOrder
export const deleteStoreOrder = deleteOrder
export const payStoreOrder = payOrder
export const takeStoreOrder = takeOrder
export const rufundStoreOrder = refundOrder
export const getStoreOrderRecordList = getOrderRecordList
export const exportStoreOrder = exportOrder
export const getShopCount = getOrderStats
