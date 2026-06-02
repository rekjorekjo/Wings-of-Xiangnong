import request from '@/config/axios'

export interface ProductReviewVO {
  id: number
  uid: number
  oid: number
  unique: string
  productId: number
  replyType: string
  productScore: boolean
  serviceScore: boolean
  comment: string
  pics: string
  merchantReplyContent: string
  merchantReplyTime: Date
  isReply: boolean
}

// 兼容旧 app 类型名，后续逐步迁移调用方到新类型名。
export type StoreProductReplyVO = ProductReviewVO

export type ProductReviewPageReqVO = Record<string, any>
export type StoreProductReplyPageReqVO = ProductReviewPageReqVO

export type ProductReviewExportReqVO = Record<string, any>
export type StoreProductReplyExportReqVO = ProductReviewExportReqVO

export const getProductReviewPage = async (params: ProductReviewPageReqVO) => {
  return await request.get({ url: `/product/store-product-reply/page`, params })
}

export const getProductReview = async (id: number) => {
  return await request.get({ url: `/product/store-product-reply/get?id=` + id })
}

export const createProductReview = async (data: ProductReviewVO) => {
  return await request.post({ url: `/product/store-product-reply/create`, data })
}

export const updateProductReview = async (data: ProductReviewVO) => {
  return await request.put({ url: `/product/store-product-reply/update`, data })
}

export const deleteProductReview = async (id: number) => {
  return await request.delete({ url: `/product/store-product-reply/delete?id=` + id })
}

export const exportProductReview = async (params: ProductReviewExportReqVO) => {
  return await request.download({ url: `/product/store-product-reply/export-excel`, params })
}

// 兼容旧 app API 函数名，调用方逐步迁移到新命名。
export const getStoreProductReplyPage = getProductReviewPage
export const getStoreProductReply = getProductReview
export const createStoreProductReply = createProductReview
export const updateStoreProductReply = updateProductReview
export const deleteStoreProductReply = deleteProductReview
export const exportStoreProductReply = exportProductReview
