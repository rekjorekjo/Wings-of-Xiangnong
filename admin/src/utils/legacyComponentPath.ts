// 兼容数据库中旧的 system_menu.component 路径。
// 只改前端组件解析路径，不修改菜单 URL、权限或数据库数据。

// 精确映射优先于正则前缀映射
const exactPathMap: Record<string, string> = {
  // 门店
  'mall/shop': 'business/sites/index',
  'mall/shop/index': 'business/sites/index',
  'business/shop': 'business/sites/index',
  'business/shop/index': 'business/sites/index',
  'business/store/shop': 'business/sites/index',
  'business/store/shop/index': 'business/sites/index',
  'mall/store/shop': 'business/sites/index',
  'mall/store/shop/index': 'business/sites/index',

  // 商品列表
  'mall/product/storeProduct': 'business/products/items/index',
  'mall/product/storeProduct/index': 'business/products/items/index',
  'business/product/storeProduct': 'business/products/items/index',
  'business/product/storeProduct/index': 'business/products/items/index',

  // 商品分类
  'mall/product/category': 'business/products/categories/index',
  'mall/product/category/index': 'business/products/categories/index',
  'business/product/category': 'business/products/categories/index',
  'business/product/category/index': 'business/products/categories/index',

  // 商品规格
  'mall/shop/storeProductRule': 'business/products/rules/index',
  'mall/shop/storeProductRule/index': 'business/products/rules/index',
  'business/shop/storeProductRule': 'business/products/rules/index',
  'business/shop/storeProductRule/index': 'business/products/rules/index',

  // 订单管理
  'mall/order/storeOrder': 'business/orders/index',
  'mall/order/storeOrder/index': 'business/orders/index',
  'business/order/storeOrder': 'business/orders/index',
  'business/order/storeOrder/index': 'business/orders/index'
}

const legacyComponentPathMap: Array<[RegExp, string]> = [
  // mall/product/storeProductRelation -> business/products/relations
  [/^mall\/product\/storeProductRelation\//, 'business/products/relations/'],
  [/^business\/product\/storeProductRelation\//, 'business/products/relations/'],

  // mall/product/storeProductReply -> business/products/reviews
  [/^mall\/product\/storeProductReply\//, 'business/products/reviews/'],
  [/^business\/product\/storeProductReply\//, 'business/products/reviews/'],

  // mall/shop/ads -> business/operations/ads
  [/^mall\/shop\/ads\//, 'business/operations/ads/'],
  [/^business\/shop\/ads\//, 'business/operations/ads/'],

  // mall/shop/recharge -> business/operations/recharge
  [/^mall\/shop\/recharge\//, 'business/operations/recharge/'],
  [/^business\/shop\/recharge\//, 'business/operations/recharge/'],

  // mall/shop/service -> business/operations/services
  [/^mall\/shop\/service\//, 'business/operations/services/'],
  [/^business\/shop\/service\//, 'business/operations/services/'],

  // mall/coupon -> business/coupons
  [/^mall\/coupon\//, 'business/coupons/'],
  [/^business\/coupon\//, 'business/coupons/'],

  // mall/member/userAddress -> business/members/addresses
  [/^mall\/member\/userAddress\//, 'business/members/addresses/'],
  [/^business\/member\/userAddress\//, 'business/members/addresses/'],

  // mall/member/user -> business/members/users
  [/^mall\/member\/user\//, 'business/members/users/'],
  [/^business\/member\/user\//, 'business/members/users/'],

  // 通用兜底：mall/xxx -> business/xxx
  [/^mall\//, 'business/']
]

export const normalizeLegacyComponentPath = (component?: string): string | undefined => {
  if (!component) return component

  // 清洗：trim、去掉开头 /、去掉末尾多余的 /
  let cleaned = component.trim()
  if (cleaned.startsWith('/')) cleaned = cleaned.slice(1)
  cleaned = cleaned.replace(/\/+$/, '')

  // 精确映射优先
  if (exactPathMap[cleaned]) {
    return exactPathMap[cleaned]
  }

  // 正则前缀映射
  for (const [pattern, replacement] of legacyComponentPathMap) {
    if (pattern.test(cleaned)) {
      return cleaned.replace(pattern, replacement)
    }
  }

  return component
}
