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

// 查询商品点赞和收藏列表
export const getStoreProductRelationPage = async (params: ProductRelationPageReqVO) => {
  return await request.get({ url: `/product/store-product-relation/page`, params })
}

// 查询商品点赞和收藏详情
export const getStoreProductRelation = async (id: number) => {
  return await request.get({ url: `/product/store-product-relation/get?id=` + id })
}

// 新增商品点赞和收藏
export const createStoreProductRelation = async (data: StoreProductRelationVO) => {
  return await request.post({ url: `/product/store-product-relation/create`, data })
}

// 修改商品点赞和收藏
export const updateStoreProductRelation = async (data: StoreProductRelationVO) => {
  return await request.put({ url: `/product/store-product-relation/update`, data })
}

// 删除商品点赞和收藏
export const deleteStoreProductRelation = async (id: number) => {
  return await request.delete({ url: `/product/store-product-relation/delete?id=` + id })
}

// 导出商品点赞和收藏 Excel
export const exportStoreProductRelation = async (params: ProductRelationExportReqVO) => {
  return await request.download({ url: `/product/store-product-relation/export-excel`, params })
}
