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

// 兼容旧 wings 类型名，后续逐步迁移调用方到新类型名。
export type ShopVO = SiteVO

export type SitePageReqVO = Record<string, any>
export type ShopPageReqVO = SitePageReqVO

export type SiteExportReqVO = Record<string, any>
export type ShopExportReqVO = SiteExportReqVO

export const getSiteList = async () => {
  return await request.get({ url: `/store/shop/list` })
}

export const getSitePage = async (params: SitePageReqVO) => {
  return await request.get({ url: `/store/shop/page`, params })
}

export const getSite = async (id: number) => {
  return await request.get({ url: `/store/shop/get?id=` + id })
}

export const createSite = async (data: SiteVO) => {
  return await request.post({ url: `/store/shop/create`, data })
}

export const updateSite = async (data: SiteVO) => {
  return await request.put({ url: `/store/shop/update`, data })
}

export const deleteSite = async (id: number) => {
  return await request.delete({ url: `/store/shop/delete?id=` + id })
}

export const exportSite = async (params: SiteExportReqVO) => {
  return await request.download({ url: `/store/shop/export-excel`, params })
}

// 兼容旧 wings API 函数名，调用方逐步迁移到新命名。
export const getShopList = getSiteList
export const getShopPage = getSitePage
export const getShop = getSite
export const createShop = createSite
export const updateShop = updateSite
export const deleteShop = deleteSite
export const exportShop = exportSite
