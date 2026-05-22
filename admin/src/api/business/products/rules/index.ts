import request from '@/config/axios'

export interface ProductRuleVO {
  id: number
  ruleName: string
  ruleValue: string
}

// 兼容旧 yshop 类型名，后续逐步迁移调用方到新类型名。
export type StoreProductRuleVO = ProductRuleVO

export type ProductRulePageReqVO = Record<string, any>
export type StoreProductRulePageReqVO = ProductRulePageReqVO

export type ProductRuleExportReqVO = Record<string, any>
export type StoreProductRuleExportReqVO = ProductRuleExportReqVO

// 查询商品规则值(规格)列表
export const getStoreProductRulePage = async (params: ProductRulePageReqVO) => {
  return await request.get({ url: `/product/store-product-rule/page`, params })
}

// 查询商品规则值(规格)详情
export const getStoreProductRule = async (id: number) => {
  return await request.get({ url: `/product/store-product-rule/get?id=` + id })
}

// 新增商品规则值(规格)
export const createStoreProductRule = async (data: StoreProductRuleVO,id: number) => {
  return await request.post({ url: `/product/store-product-rule/save/` + id, data })
}

// 修改商品规则值(规格)
export const updateStoreProductRule = async (data: StoreProductRuleVO) => {
  return await request.put({ url: `/product/store-product-rule/update`, data })
}

// 删除商品规则值(规格)
export const deleteStoreProductRule = async (id: number) => {
  return await request.delete({ url: `/product/store-product-rule/delete?id=` + id })
}

// 导出商品规则值(规格) Excel
export const exportStoreProductRule = async (params: ProductRuleExportReqVO) => {
  return await request.download({ url: `/product/store-product-rule/export-excel`, params })
}
