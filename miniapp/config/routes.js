// 小程序页面路径集中管理。
// 新增跳转优先使用 ROUTES，避免 pages/subpages 路径调整时到处漏改。

export const ROUTES = {
  tabs: {
    home: '/pages/index/index',
    menu: '/pages/menu/menu',
    cart: '/pages/cart/cart',
    order: '/pages/order/order',
    mine: '/pages/mine/mine'
  },

  subpages: {
    pay: '/pages/subpages/pay/pay',
    shop: '/pages/subpages/shop/shop',
    orderDetail: '/pages/subpages/orders/detail',
    orderList: '/pages/subpages/orders/orders',
    orderRefund: '/pages/subpages/orders/refund',
    coupons: '/pages/subpages/coupons/coupons',
    address: '/pages/subpages/address/address',
    login: '/pages/subpages/login/login'
  }
}
