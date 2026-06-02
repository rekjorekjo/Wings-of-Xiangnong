import request from '@/config/axios'

export interface ProductRuleVO {
  id: number
  ruleName: string
  ruleValue: string
}

// 兼容旧 app 类型名，后续逐步迁移调用方到新类型名。
export type StoreProductRuleVO = ProductRuleVO

export type ProductRulePageReqVO = Record<string, any>
export type StoreProductRulePageReqVO = ProductRulePageReqVO

export type ProductRuleExportReqVO = Record<string, any>
export type StoreProductRuleExportReqVO = ProductRuleExportReqVO

export const getProductRulePage = async (params: ProductRulePageReqVO) => {
  return await request.get({ url: `/product/store-product-rule/page`, params })
}

export const getProductRule = async (id: number) => {
  return await request.get({ url: `/product/store-product-rule/get?id=` + id })
}

export const createProductRule = async (data: ProductRuleVO,id: number) => {
  return await request.post({ url: `/product/store-product-rule/save/` + id, data })
}

export const updateProductRule = async (data: ProductRuleVO) => {
  return await request.put({ url: `/product/store-product-rule/update`, data })
}

export const deleteProductRule = async (id: number) => {
  return await request.delete({ url: `/product/store-product-rule/delete?id=` + id })
}

export const exportProductRule = async (params: ProductRuleExportReqVO) => {
  return await request.download({ url: `/product/store-product-rule/export-excel`, params })
}

// 兼容旧 app API 函数名，调用方逐步迁移到新命名。
export const getStoreProductRulePage = getProductRulePage
export const getStoreProductRule = getProductRule
export const createStoreProductRule = createProductRule
export const updateStoreProductRule = updateProductRule
export const deleteStoreProductRule = deleteProductRule
export const exportStoreProductRule = exportProductRule
