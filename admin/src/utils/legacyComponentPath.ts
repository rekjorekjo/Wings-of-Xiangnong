// 兼容数据库中旧的 system_menu.component 路径。
// 只改前端组件解析路径，不修改菜单 URL、权限或数据库数据。

const legacyComponentPathMap: Array<[RegExp, string]> = [
  // mall/product/storeProduct/index -> business/products/items/index
  // mall/order/storeOrder/index -> business/orders/index
  // mall/coupon/index -> business/coupons/index

  [/^mall\/product\/category\//, 'business/products/categories/'],
  [/^business\/product\/category\//, 'business/products/categories/'],

  [/^mall\/product\/storeProductRelation\//, 'business/products/relations/'],
  [/^business\/product\/storeProductRelation\//, 'business/products/relations/'],

  [/^mall\/product\/storeProductReply\//, 'business/products/reviews/'],
  [/^business\/product\/storeProductReply\//, 'business/products/reviews/'],

  [/^mall\/product\/storeProduct\//, 'business/products/items/'],
  [/^business\/product\/storeProduct\//, 'business/products/items/'],

  [/^mall\/shop\/storeProductRule\//, 'business/products/rules/'],
  [/^business\/shop\/storeProductRule\//, 'business/products/rules/'],

  [/^mall\/order\/storeOrder\//, 'business/orders/'],
  [/^business\/order\/storeOrder\//, 'business/orders/'],

  [/^mall\/store\/shop\//, 'business/sites/'],
  [/^business\/store\/shop\//, 'business/sites/'],

  [/^mall\/shop\/ads\//, 'business/operations/ads/'],
  [/^business\/shop\/ads\//, 'business/operations/ads/'],

  [/^mall\/shop\/recharge\//, 'business/operations/recharge/'],
  [/^business\/shop\/recharge\//, 'business/operations/recharge/'],

  [/^mall\/shop\/service\//, 'business/operations/services/'],
  [/^business\/shop\/service\//, 'business/operations/services/'],

  [/^mall\/coupon\//, 'business/coupons/'],
  [/^business\/coupon\//, 'business/coupons/'],

  [/^mall\/member\/userAddress\//, 'business/members/addresses/'],
  [/^business\/member\/userAddress\//, 'business/members/addresses/'],

  [/^mall\/member\/user\//, 'business/members/users/'],
  [/^business\/member\/user\//, 'business/members/users/'],

  [/^mall\//, 'business/']
]

export const normalizeLegacyComponentPath = (component?: string): string | undefined => {
  if (!component) return component
  for (const [pattern, replacement] of legacyComponentPathMap) {
    if (pattern.test(component)) {
      return component.replace(pattern, replacement)
    }
  }
  return component
}
