import request from '@/config/axios'

export interface ProductRelationVO {
  id: number
  uid: number
  productId: number
  type: string
  category: string
}

// 兼容旧 yshop 类型名，后续逐步迁移调用方到新类型名。
export type StoreProductRelationVO = ProductRelationVO

export type ProductRelationPageReqVO = Record<string, any>
export type StoreProductRelationPageReqVO = ProductRelationPageReqVO

export type ProductRelationExportReqVO = Record<string, any>
export type StoreProductRelationExportReqVO = ProductRelationExportReqVO

export const getProductRelationPage = async (params: ProductRelationPageReqVO) => {
  return await request.get({ url: `/product/store-product-relation/page`, params })
}

export const getProductRelation = async (id: number) => {
  return await request.get({ url: `/product/store-product-relation/get?id=` + id })
}

export const createProductRelation = async (data: StoreProductRelationVO) => {
  return await request.post({ url: `/product/store-product-relation/create`, data })
}

export const updateProductRelation = async (data: StoreProductRelationVO) => {
  return await request.put({ url: `/product/store-product-relation/update`, data })
}

export const deleteProductRelation = async (id: number) => {
  return await request.delete({ url: `/product/store-product-relation/delete?id=` + id })
}

export const exportProductRelation = async (params: ProductRelationExportReqVO) => {
  return await request.download({ url: `/product/store-product-relation/export-excel`, params })
}

// 兼容旧 yshop API 函数名，调用方逐步迁移到新命名。
export const getStoreProductRelationPage = getProductRelationPage
export const getStoreProductRelation = getProductRelation
export const createStoreProductRelation = createProductRelation
export const updateStoreProductRelation = updateProductRelation
export const deleteStoreProductRelation = deleteProductRelation
export const exportStoreProductRelation = exportProductRelation
