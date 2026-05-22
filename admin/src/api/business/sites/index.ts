import request from '@/config/axios'

export interface SiteVO {
  id: number
  name: string
  mobile: string
  image: string
  images: string
  address: string
  addressMap: string
  lng: string
  lat: string
  distance: number
  minPrice: number
  deliveryPrice: number
  notice: string
  status: boolean
  adminId: string
  uniprintId: string
  startTime: Date
  endTime: Date
}

// 兼容旧 yshop 类型名，后续逐步迁移调用方到新类型名。
export type ShopVO = SiteVO

export type SitePageReqVO = Record<string, any>
export type ShopPageReqVO = SitePageReqVO

export type SiteExportReqVO = Record<string, any>
export type ShopExportReqVO = SiteExportReqVO

export const getShopList = async () => {
  return await request.get({ url: `/store/shop/list` })
}

// 查询门店管理列表
export const getShopPage = async (params: SitePageReqVO) => {
  return await request.get({ url: `/store/shop/page`, params })
}

// 查询门店管理详情
export const getShop = async (id: number) => {
  return await request.get({ url: `/store/shop/get?id=` + id })
}

// 新增门店管理
export const createShop = async (data: ShopVO) => {
  return await request.post({ url: `/store/shop/create`, data })
}

// 修改门店管理
export const updateShop = async (data: ShopVO) => {
  return await request.put({ url: `/store/shop/update`, data })
}

// 删除门店管理
export const deleteShop = async (id: number) => {
  return await request.delete({ url: `/store/shop/delete?id=` + id })
}

// 导出门店管理 Excel
export const exportShop = async (params: SiteExportReqVO) => {
  return await request.download({ url: `/store/shop/export-excel`, params })
}
