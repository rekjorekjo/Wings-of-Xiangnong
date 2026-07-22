<template>
  <div class="workspace-operations">
    <div class="page-header">
      <h1>运营中心</h1>
      <p class="subtitle">管理商品、订单、用户、优惠券、站点、运营内容与支付配置。</p>
    </div>

    <div class="modules-grid">
      <el-card shadow="hover" class="module-card" v-for="module in modules" :key="module.title">
        <div class="module-header">
          <div class="module-icon" :style="{ backgroundColor: module.color }">
            <Icon :icon="module.icon" :size="24" />
          </div>
          <div class="module-title">{{ module.title }}</div>
        </div>
        <div class="module-desc">{{ module.desc }}</div>
        <div class="module-actions">
          <el-button type="primary" link @click="navigateByCandidates(module.mainPaths)">
            {{ module.mainAction }}
          </el-button>
          <template v-if="module.subActions">
            <el-divider direction="vertical" />
            <el-button
              v-for="sub in module.subActions"
              :key="sub.label"
              type="default"
              link
              size="small"
              @click="navigateByCandidates(sub.paths)"
            >
              {{ sub.label }}
            </el-button>
          </template>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Icon } from '@/components/Icon'

const router = useRouter()

// 404 兜底路由的 name 或 path 特征
const FALLBACK_ROUTE_NAMES = ['404Page', 'NoFound']
const FALLBACK_ROUTE_PATH = '/:path(.*)*'

const isRealRoute = (path: string): boolean => {
  const resolved = router.resolve(path)
  if (resolved.matched.length === 0) return false
  // 排除匹配到兜底 404 路由的情况
  return resolved.matched.every(
    (record) =>
      !FALLBACK_ROUTE_NAMES.includes(record.name as string) &&
      record.path !== FALLBACK_ROUTE_PATH
  )
}

const modules = [
  {
    title: '商品管理',
    desc: '管理商品列表、商品分类、商品规格和上下架状态',
    icon: 'ep:goods',
    color: '#409EFF',
    mainAction: '商品列表',
    mainPaths: ['/mall/product/storeProduct', '/business/product/storeProduct', '/business/products/items', '/mall/product/store-product'],
    subActions: [
      { label: '商品分类', paths: ['/mall/product/category', '/business/product/category', '/business/products/categories'] },
      { label: '商品规格', paths: ['/mall/shop/storeProductRule', '/business/shop/storeProductRule', '/business/products/rules', '/mall/shop/store-product-rule'] }
    ]
  },
  {
    title: '订单管理',
    desc: '查看订单、处理订单状态、退款和订单记录',
    icon: 'ep:document',
    color: '#67C23A',
    mainAction: '订单列表',
    mainPaths: ['/mall/order/storeOrder', '/business/order/storeOrder', '/business/orders', '/mall/order/store-order'],
    subActions: null
  },
  {
    title: '用户管理',
    desc: '查看用户信息、用户详情和收货地址',
    icon: 'ep:user',
    color: '#E6A23C',
    mainAction: '用户列表',
    mainPaths: ['/business/members/users', '/mall/member/user'],
    subActions: [
      { label: '用户地址', paths: ['/business/members/addresses', '/mall/member/user-address', '/mall/member/userAddress'] }
    ]
  },
  {
    title: '优惠券管理',
    desc: '管理优惠券、活动券和领取记录',
    icon: 'ep:ticket',
    color: '#F56C6C',
    mainAction: '优惠券列表',
    mainPaths: ['/business/coupons', '/mall/coupons', '/mall/coupon'],
    subActions: null
  },
  {
    title: '站点管理',
    desc: '管理站点信息、地址坐标、营业时间和配送基础配置',
    icon: 'ep:location',
    color: '#909399',
    mainAction: '站点列表',
    mainPaths: ['/mall/shop', '/business/sites', '/business/shop', '/business/store/shop', '/mall/store/shop'],
    subActions: null
  },
  {
    title: '运营内容',
    desc: '管理首页广告图和服务入口',
    icon: 'ep:picture',
    color: '#9B59B6',
    mainAction: '广告图管理',
    mainPaths: ['/business/operations/ads', '/mall/shop/ads'],
    subActions: [
      { label: '我的服务', paths: ['/business/operations/services', '/mall/shop/service'] }
    ]
  },
  {
    title: '支付配置',
    desc: '管理支付商户配置和支付基础参数',
    icon: 'ep:wallet',
    color: '#3498DB',
    mainAction: '支付商户配置',
    mainPaths: ['/pay/merchant-details', '/pay/merchant'],
    subActions: null
  }
]

const navigateByCandidates = (paths: string[]) => {
  const target = paths.find(isRealRoute)
  if (target) {
    router.push(target)
  } else {
    ElMessage.warning('该功能暂未开放')
  }
}
</script>

<style lang="scss" scoped>
.workspace-operations {
  padding: 20px;
  background-color: var(--el-bg-color-page);
  min-height: 100%;
}

.page-header {
  margin-bottom: 24px;

  h1 {
    font-size: 24px;
    font-weight: 600;
    margin: 0 0 8px 0;
    color: var(--el-text-color-primary);
  }

  .subtitle {
    font-size: 14px;
    color: var(--el-text-color-secondary);
    margin: 0;
  }
}

.modules-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
  gap: 20px;
  align-items: stretch;
}

.module-card {
  height: 100%;
  min-height: 180px;
  box-sizing: border-box;
  transition: transform 0.18s ease, box-shadow 0.18s ease, border-color 0.18s ease;
  background-color: var(--el-bg-color);

  &:hover {
    transform: translateY(-4px);
    box-shadow: var(--el-box-shadow-light);
    border-color: var(--el-color-primary-light-5);
  }

  :deep(.el-card__body) {
    padding: 20px;
    height: 100%;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
  }
}

.module-header {
  display: flex;
  align-items: center;
  margin-bottom: 12px;
}

.module-icon {
  width: 40px;
  height: 40px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  margin-right: 12px;
  flex-shrink: 0;
}

.module-title {
  font-size: 16px;
  font-weight: 600;
  color: var(--el-text-color-primary);
}

.module-desc {
  font-size: 13px;
  color: var(--el-text-color-secondary);
  line-height: 1.5;
  margin-bottom: 16px;
  flex: 1;
}

.module-actions {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 8px;
}
</style>
