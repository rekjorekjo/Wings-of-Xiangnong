import { ElMessage } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
import { getDeliveryTasks, getDrones } from '@/api/delivery'
import type { DeliveryTask, Drone } from '../types'

export const useDeliveryDashboard = () => {
  const route = useRoute()
  const router = useRouter()

  const loading = ref(false)
  const dronesLoading = ref(false)
  const tasksLoading = ref(false)

  const drones = ref<Drone[]>([])
  const tasks = ref<DeliveryTask[]>([])

  const filterStatus = ref('')
  const filterDroneNo = ref('')
  const filterOrderNo = ref('')

  const drawerVisible = ref(false)
  const currentTask = ref<DeliveryTask | null>(null)

  // MVP 管理端支持通过 URL 带入筛选条件，方便后续从订单页跳转到配送任务页。
  const syncFiltersToUrl = () => {
    const query: Record<string, string> = {}
    if (filterStatus.value) query.status = filterStatus.value
    if (filterDroneNo.value) query.droneNo = filterDroneNo.value
    if (filterOrderNo.value) query.orderNo = filterOrderNo.value
    router.replace({ query })
  }

  const initFiltersFromUrl = () => {
    const { orderNo, droneNo, status } = route.query
    if (typeof orderNo === 'string') filterOrderNo.value = orderNo
    if (typeof droneNo === 'string') filterDroneNo.value = droneNo
    if (typeof status === 'string') filterStatus.value = status
  }

  watch([filterStatus, filterDroneNo, filterOrderNo], () => {
    syncFiltersToUrl()
  })

  // 前端本地筛选，后续接真实接口时可改为服务端筛选。
  const filteredTasks = computed(() => {
    return tasks.value.filter(task => {
      if (filterStatus.value && task.status !== filterStatus.value) return false
      if (filterDroneNo.value && !task.droneNo.toLowerCase().includes(filterDroneNo.value.toLowerCase())) return false
      if (filterOrderNo.value && !task.orderNo.toLowerCase().includes(filterOrderNo.value.toLowerCase())) return false
      return true
    })
  })

  // 页面统一从 delivery API 入口取数据，当前是 mock，后续可替换真实接口。
  const loadData = async () => {
    loading.value = true
    dronesLoading.value = true
    tasksLoading.value = true
    
    try {
      const [dronesData, tasksData] = await Promise.all([
        getDrones(),
        getDeliveryTasks()
      ])
      drones.value = dronesData
      tasks.value = tasksData
    } finally {
      loading.value = false
      dronesLoading.value = false
      tasksLoading.value = false
    }
  }

  const handleRefresh = () => {
    loadData()
  }

  const handleResetFilter = () => {
    filterStatus.value = ''
    filterDroneNo.value = ''
    filterOrderNo.value = ''
  }

  const handleShowDetail = (task: DeliveryTask) => {
    currentTask.value = task
    drawerVisible.value = true
  }

  // 以下操作只修改前端本地状态，用于 MVP 演示，不会写入后端。
  const handleAssignDrone = (task: DeliveryTask) => {
    if (!task.droneNo) {
      task.droneNo = 'DR-001'
    }
    task.status = 'assigned'
    task.statusText = '已分配'
    task.progress = 0
    ElMessage.success('已分配无人机')
  }

  const handleMarkFlying = (task: DeliveryTask) => {
    task.status = 'flying'
    task.statusText = '配送中'
    task.progress = 50
    ElMessage.success('已标记为起飞')
  }

  const handleMarkArrived = (task: DeliveryTask) => {
    task.status = 'arrived'
    task.statusText = '已到达'
    task.progress = 90
    task.etaMinutes = 2
    ElMessage.success('已标记为到达')
  }

  const handleMarkCompleted = (task: DeliveryTask) => {
    task.status = 'completed'
    task.statusText = '已完成'
    task.progress = 100
    task.etaMinutes = 0
    ElMessage.success('已标记为完成')
  }

  const handleMarkException = (task: DeliveryTask) => {
    task.status = 'exception'
    task.statusText = '异常'
    ElMessage.warning('已标记为异常')
  }

  const handleResetToPending = (task: DeliveryTask) => {
    task.status = 'pending'
    task.statusText = '待分配'
    task.progress = 0
    task.etaMinutes = 0
    ElMessage.success('已重置为待分配')
  }

  onMounted(() => {
    initFiltersFromUrl()
    loadData()
  })

  return {
    loading,
    dronesLoading,
    tasksLoading,
    drones,
    tasks,
    filterStatus,
    filterDroneNo,
    filterOrderNo,
    drawerVisible,
    currentTask,
    filteredTasks,
    handleRefresh,
    handleResetFilter,
    handleShowDetail,
    handleAssignDrone,
    handleMarkFlying,
    handleMarkArrived,
    handleMarkCompleted,
    handleMarkException,
    handleResetToPending
  }
}
