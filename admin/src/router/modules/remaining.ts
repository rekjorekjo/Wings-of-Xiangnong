import { Layout } from '@/utils/routerHelper'

const { t } = useI18n()
/**
 * redirect: noredirect        当设置 noredirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'          设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
 hidden: true              当设置 true 的时候该路由不会再侧边栏出现 如404，login等页面(默认 false)

 alwaysShow: true          当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式，
 只有一个时，会将那个子路由当做根路由显示在侧边栏，
 若你想不管路由下面的 children 声明的个数都显示你的根路由，
 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，
 一直显示根路由(默认 false)

 title: 'title'            设置该路由在侧边栏和面包屑中展示的名字

 icon: 'svg-name'          设置该路由的图标

 noCache: true             如果设置为true，则不会被 <keep-alive> 缓存(默认 false)

 breadcrumb: false         如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)

 affix: true               如果设置为true，则会一直固定在tag项中(默认 false)

 noTagsView: true          如果设置为true，则不会出现在tag中(默认 false)

 activeMenu: '/dashboard'  显示高亮的路由路径

 followAuth: '/dashboard'  跟随哪个路由进行权限过滤

 canTo: true               设置为true即使hidden为true，也依然可以进行路由跳转(默认 false)

 workspace: true           标记为工作区入口，用于侧边栏过滤
 }
 **/
const remainingRouter: AppRouteRecordRaw[] = [
  {
    path: '/redirect',
    component: Layout,
    name: 'Redirect',
    children: [
      {
        path: '/redirect/:path(.*)',
        name: 'Redirect1',
        component: () => import('@/views/Redirect/Redirect.vue'),
        meta: {}
      }
    ],
    meta: {
      hidden: true,
      noTagsView: true
    }
  },
  {
    path: '/',
    component: Layout,
    redirect: '/workspace/home',
    name: 'Home',
    meta: {},
    children: [
      {
        path: 'workspace/home',
        component: () => import('@/views/workspace/home/index.vue'),
        name: 'WorkspaceHome',
        meta: {
          title: '首页',
          icon: 'ep:home-filled',
          noCache: false,
          affix: true,
          workspace: true
        }
      },
      {
        path: 'workspace/operations',
        component: () => import('@/views/workspace/operations/index.vue'),
        name: 'WorkspaceOperations',
        meta: {
          title: '运营中心',
          icon: 'ep:shop',
          noCache: false,
          workspace: true
        }
      },
      {
        path: 'workspace/dispatch',
        component: () => import('@/views/workspace/dispatch/index.vue'),
        name: 'WorkspaceDispatch',
        meta: {
          title: '调度中心',
          icon: 'ep:position',
          noCache: false,
          workspace: true
        }
      },
      {
        path: 'workspace/settings',
        component: () => import('@/views/workspace/settings/index.vue'),
        name: 'WorkspaceSettings',
        meta: {
          title: '系统设置',
          icon: 'ep:setting',
          noCache: false,
          workspace: true
        }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    name: 'UserInfo',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'profile',
        component: () => import('@/views/Profile/Index.vue'),
        name: 'Profile',
        meta: {
          canTo: true,
          hidden: true,
          noTagsView: false,
          icon: 'ep:user',
          title: t('common.profile')
        }
      },
      {
        path: 'notify-message',
        component: () => import('@/views/system/notify/my/index.vue'),
        name: 'MyNotifyMessage',
        meta: {
          canTo: true,
          hidden: true,
          noTagsView: false,
          icon: 'ep:message',
          title: '我的站内信'
        }
      }
    ]
  },
  {
    path: '/dict',
    component: Layout,
    name: 'dict',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'type/data/:dictType',
        component: () => import('@/views/system/dict/data/index.vue'),
        name: 'SystemDictData',
        meta: {
          title: '字典数据',
          noCache: true,
          hidden: true,
          canTo: true,
          icon: '',
          activeMenu: '/system/dict'
        }
      }
    ]
  },

  {
    path: '/codegen',
    component: Layout,
    name: 'CodegenEdit',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'edit',
        component: () => import('@/views/infra/codegen/EditTable.vue'),
        name: 'InfraCodegenEditTable',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          icon: 'ep:edit',
          title: '修改生成配置',
          activeMenu: 'infra/codegen/index'
        }
      }
    ]
  },
  {
    path: '/job',
    component: Layout,
    name: 'JobL',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'job-log',
        component: () => import('@/views/infra/job/logger/index.vue'),
        name: 'InfraJobLog',
        meta: {
          noCache: true,
          hidden: true,
          canTo: true,
          icon: 'ep:edit',
          title: '调度日志',
          activeMenu: 'infra/job/index'
        }
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/Login/Login.vue'),
    name: 'Login',
    meta: {
      hidden: true,
      title: t('router.login'),
      noTagsView: true
    }
  },
  {
    path: '/sso',
    component: () => import('@/views/Login/Login.vue'),
    name: 'SSOLogin',
    meta: {
      hidden: true,
      title: t('router.login'),
      noTagsView: true
    }
  },
  {
    path: '/social-login',
    component: () => import('@/views/Login/SocialLogin.vue'),
    name: 'SocialLogin',
    meta: {
      hidden: true,
      title: t('router.socialLogin'),
      noTagsView: true
    }
  },
  {
    path: '/403',
    component: () => import('@/views/Error/403.vue'),
    name: 'NoAccess',
    meta: {
      hidden: true,
      title: '403',
      noTagsView: true
    }
  },
  // ========== MVP 静态兜底路由 ==========
  // 保证即使后端动态路由或旧 component 映射有问题，核心路径也能访问真实页面
  // hidden + canTo：不在侧边栏显示，但允许跳转
  {
    path: '/mall/shop',
    component: Layout,
    name: 'MvpLegacyMallShop',
    meta: { hidden: true, canTo: true, title: '门店管理' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/sites/index.vue'),
        name: 'MvpLegacyMallShopIndex',
        meta: { hidden: true, canTo: true, title: '门店管理', noCache: false }
      }
    ]
  },
  {
    path: '/business/sites',
    component: Layout,
    name: 'MvpLegacyBusinessSites',
    meta: { hidden: true, canTo: true, title: '门店管理' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/sites/index.vue'),
        name: 'MvpLegacyBusinessSitesIndex',
        meta: { hidden: true, canTo: true, title: '门店管理', noCache: false }
      }
    ]
  },
  {
    path: '/business/shop',
    component: Layout,
    name: 'MvpLegacyBusinessShop',
    meta: { hidden: true, canTo: true, title: '门店管理' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/sites/index.vue'),
        name: 'MvpLegacyBusinessShopIndex',
        meta: { hidden: true, canTo: true, title: '门店管理', noCache: false }
      }
    ]
  },
  {
    path: '/business/store/shop',
    component: Layout,
    name: 'MvpLegacyBusinessStoreShop',
    meta: { hidden: true, canTo: true, title: '门店管理' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/sites/index.vue'),
        name: 'MvpLegacyBusinessStoreShopIndex',
        meta: { hidden: true, canTo: true, title: '门店管理', noCache: false }
      }
    ]
  },
  {
    path: '/mall/store/shop',
    component: Layout,
    name: 'MvpLegacyMallStoreShop',
    meta: { hidden: true, canTo: true, title: '门店管理' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/sites/index.vue'),
        name: 'MvpLegacyMallStoreShopIndex',
        meta: { hidden: true, canTo: true, title: '门店管理', noCache: false }
      }
    ]
  },
  {
    path: '/mall/product/storeProduct',
    component: Layout,
    name: 'MvpLegacyMallProduct',
    meta: { hidden: true, canTo: true, title: '商品列表' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/products/items/index.vue'),
        name: 'MvpLegacyMallProductIndex',
        meta: { hidden: true, canTo: true, title: '商品列表', noCache: false }
      }
    ]
  },
  {
    path: '/business/product/storeProduct',
    component: Layout,
    name: 'MvpLegacyBusinessProduct',
    meta: { hidden: true, canTo: true, title: '商品列表' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/products/items/index.vue'),
        name: 'MvpLegacyBusinessProductIndex',
        meta: { hidden: true, canTo: true, title: '商品列表', noCache: false }
      }
    ]
  },
  {
    path: '/business/products/items',
    component: Layout,
    name: 'MvpLegacyBusinessProductsItems',
    meta: { hidden: true, canTo: true, title: '商品列表' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/products/items/index.vue'),
        name: 'MvpLegacyBusinessProductsItemsIndex',
        meta: { hidden: true, canTo: true, title: '商品列表', noCache: false }
      }
    ]
  },
  {
    path: '/mall/product/category',
    component: Layout,
    name: 'MvpLegacyMallProductCategory',
    meta: { hidden: true, canTo: true, title: '商品分类' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/products/categories/index.vue'),
        name: 'MvpLegacyMallProductCategoryIndex',
        meta: { hidden: true, canTo: true, title: '商品分类', noCache: false }
      }
    ]
  },
  {
    path: '/business/product/category',
    component: Layout,
    name: 'MvpLegacyBusinessProductCategory',
    meta: { hidden: true, canTo: true, title: '商品分类' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/products/categories/index.vue'),
        name: 'MvpLegacyBusinessProductCategoryIndex',
        meta: { hidden: true, canTo: true, title: '商品分类', noCache: false }
      }
    ]
  },
  {
    path: '/business/products/categories',
    component: Layout,
    name: 'MvpLegacyBusinessProductsCategories',
    meta: { hidden: true, canTo: true, title: '商品分类' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/products/categories/index.vue'),
        name: 'MvpLegacyBusinessProductsCategoriesIndex',
        meta: { hidden: true, canTo: true, title: '商品分类', noCache: false }
      }
    ]
  },
  {
    path: '/mall/shop/storeProductRule',
    component: Layout,
    name: 'MvpLegacyMallShopRule',
    meta: { hidden: true, canTo: true, title: '商品规格' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/products/rules/index.vue'),
        name: 'MvpLegacyMallShopRuleIndex',
        meta: { hidden: true, canTo: true, title: '商品规格', noCache: false }
      }
    ]
  },
  {
    path: '/business/shop/storeProductRule',
    component: Layout,
    name: 'MvpLegacyBusinessShopRule',
    meta: { hidden: true, canTo: true, title: '商品规格' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/products/rules/index.vue'),
        name: 'MvpLegacyBusinessShopRuleIndex',
        meta: { hidden: true, canTo: true, title: '商品规格', noCache: false }
      }
    ]
  },
  {
    path: '/business/products/rules',
    component: Layout,
    name: 'MvpLegacyBusinessProductsRules',
    meta: { hidden: true, canTo: true, title: '商品规格' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/products/rules/index.vue'),
        name: 'MvpLegacyBusinessProductsRulesIndex',
        meta: { hidden: true, canTo: true, title: '商品规格', noCache: false }
      }
    ]
  },
  {
    path: '/mall/order/storeOrder',
    component: Layout,
    name: 'MvpLegacyMallOrder',
    meta: { hidden: true, canTo: true, title: '订单管理' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/orders/index.vue'),
        name: 'MvpLegacyMallOrderIndex',
        meta: { hidden: true, canTo: true, title: '订单管理', noCache: false }
      }
    ]
  },
  {
    path: '/business/order/storeOrder',
    component: Layout,
    name: 'MvpLegacyBusinessOrder',
    meta: { hidden: true, canTo: true, title: '订单管理' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/orders/index.vue'),
        name: 'MvpLegacyBusinessOrderIndex',
        meta: { hidden: true, canTo: true, title: '订单管理', noCache: false }
      }
    ]
  },
  {
    path: '/business/orders',
    component: Layout,
    name: 'MvpLegacyBusinessOrders',
    meta: { hidden: true, canTo: true, title: '订单管理' },
    children: [
      {
        path: '',
        component: () => import('@/views/business/orders/index.vue'),
        name: 'MvpLegacyBusinessOrdersIndex',
        meta: { hidden: true, canTo: true, title: '订单管理', noCache: false }
      }
    ]
  },
  // ========== MVP 静态兜底路由结束 ==========
  {
    path: '/404',
    component: () => import('@/views/Error/404.vue'),
    name: 'NoFound',
    meta: {
      hidden: true,
      title: '404',
      noTagsView: true
    }
  },
  {
    path: '/500',
    component: () => import('@/views/Error/500.vue'),
    name: 'Error',
    meta: {
      hidden: true,
      title: '500',
      noTagsView: true
    }
  },
  {
    path: '/materials/editor',
    component: () => import('@/components/Materials/src/editorMaterials.vue'),
    name: 'EditorMaterials',
    meta: {
      noCache: true,
      hidden: true,
      title: '上传图片',
    },

  },
  {
    path: '/delivery',
    component: Layout,
    name: 'Delivery',
    redirect: '/delivery/tasks',
    meta: {
      title: '配送管理',
      icon: 'ep:promotion',
      alwaysShow: true,
      hidden: true,
      canTo: true
    },
    children: [
      {
        path: 'tasks',
        component: () => import('@/views/delivery/index.vue'),
        name: 'DeliveryTasks',
        meta: {
          title: '配送任务',
          icon: 'ep:position',
          noCache: false,
          hidden: true,
          canTo: true
        }
      }
    ]
  }

]

export default remainingRouter
