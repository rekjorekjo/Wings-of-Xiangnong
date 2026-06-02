import request from '@/config/axios'

export interface ProductVO {
  id: number
  image: string
  sliderImage: string
  storeName: string
  storeInfo: string
  keyword: string
  barCode: string
  cateId: string
  price: number
  vipPrice: number
  otPrice: number
  postage: number
  unitName: string
  sort: number
  sales: number
  stock: number
  isShow: boolean
  isHot: boolean
  isBenefit: boolean
  isBest: boolean
  isNew: boolean
  description: string
  isPostage: byte
  merUse: byte
  giveIntegral: number
  cost: number
  isSeckill: byte
  isBargain: byte
  isGood: boolean
  ficti: number
  browse: number
  codePath: string
  isSub: boolean
  tempId: number
  specType: boolean
  isIntegral: byte
  integral: number
}

// 兼容旧 app 类型名，后续逐步迁移调用方到新类型名。
export type StoreProductVO = ProductVO

export type ProductPageReqVO = Record<string, any>
export type StoreProductPageReqVO = ProductPageReqVO

export type ProductExportReqVO = Record<string, any>
export type StoreProductExportReqVO = ProductExportReqVO

export const getProductPage = async (params: ProductPageReqVO) => {
  return await request.get({ url: `/product/store-product/page`, params })
}

export const getProduct = async (id: number) => {
  return await request.get({ url: `/product/store-product/get?id=` + id })
}

export const getProductInfo = async (id: number) => {
  return await request.get({ url: `/product/store-product/info/` + id })
}

export const createProduct = async (data) => {
  return await request.post({ url: `/product/store-product/create`, data })
}

export const updateProduct = async (data: ProductVO) => {
  return await request.put({ url: `/product/store-product/update`, data })
}

export const deleteProduct = async (id: number) => {
  return await request.delete({ url: `/product/store-product/delete?id=` + id })
}

export const exportProduct = async (params: ProductExportReqVO) => {
  return await request.download({ url: `/product/store-product/export-excel`, params })
}

export const isFormatAttr = async (id, data) => {
  return await request.post({ url: '/product/store-product/isFormatAttr/' + id, data })
}

export const updateProductSaleStatus = async (id,isShow) => {
  return await request.get({ url: `/product/store-product/sale?id=` + id + `&type=` + isShow })
}

// 兼容旧 app API 函数名，调用方逐步迁移到新命名。
export const getStoreProductPage = getProductPage
export const getStoreProduct = getProduct
export const getStoreProductInfo = getProductInfo
export const createStoreProduct = createProduct
export const updateStoreProduct = updateProduct
export const deleteStoreProduct = deleteProduct
export const exportStoreProduct = exportProduct
export const saleStoreProduct = updateProductSaleStatus
