// 香农之翼 MVP 功能开关。
// 冻结功能不删除源码，只隐藏入口；后续需要恢复时优先从这里打开。

export const FEATURES = {
  // MVP 主链路
  coupon: true,
  droneDelivery: true,
  takeout: true,
  takein: true,

  // 当前按单店运行
  multiStore: false,

  // 冻结功能：源码保留，入口隐藏
  scoreMall: false,
  balance: false,
  recharge: false,
  memberCard: false,
  tableOrder: false,
  cashier: false,
  cloudPrinter: false,
  reservation: false,
  mpOfficialAccount: false,

  // 当前暂不启用
  alipay: false
}
