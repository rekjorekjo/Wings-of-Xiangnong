import { ElMessage } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
import {
  downloadWaypointMission,
  getDeliveryPoints,
  getDeliveryTasks,
  getDrones,
  previewWaypointMission,
  recommendDeliveryPoint
} from '@/api/delivery'
import download from '@/utils/download'
import type { DeliveryPoint, DeliveryTask, Drone } from '../types'

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

  const waypointDialogVisible = ref(false)
  const waypointTargetTask = ref<DeliveryTask | null>(null)
  const waypointDownloading = ref(false)
  const waypointPreviewLoading = ref(false)
  const waypointRecommendLoading = ref(false)
  const waypointPreviewContent = ref('')
  const deliveryPoints = ref<DeliveryPoint[]>([])
  const recommendedDeliveryPoint = ref<DeliveryPoint | null>(null)
  const waypointForm = reactive({
    userLatitude: '31.88791480',
    userLongitude: '118.81327290',
    startLatitude: '31.88800930',
    startLongitude: '118.81510820',
    destinationLatitude: '31.88791480',
    destinationLongitude: '118.81327290',
    homeAltitude: '20',
    flightAltitude: '10'
  })

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

  // 页面统一从 delivery API 入口取数据；任务来自真实订单，无人机状态暂保留为 MVP 演示数据。
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
      deliveryPoints.value = await getDeliveryPoints()
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

  const handleOpenWaypointDialog = (task: DeliveryTask) => {
    waypointTargetTask.value = task
    waypointPreviewContent.value = ''
    recommendedDeliveryPoint.value = null
    waypointDialogVisible.value = true
  }

  const normalizeWaypointNumber = (value: string, fieldName: string): number => {
    const parsed = Number(value)
    if (!Number.isFinite(parsed)) {
      throw new Error(`${fieldName}必须是数字`)
    }
    return parsed
  }

  const buildWaypointParams = () => ({
    startLatitude: normalizeWaypointNumber(waypointForm.startLatitude, '起点纬度'),
    startLongitude: normalizeWaypointNumber(waypointForm.startLongitude, '起点经度'),
    destinationLatitude: normalizeWaypointNumber(waypointForm.destinationLatitude, '目标纬度'),
    destinationLongitude: normalizeWaypointNumber(waypointForm.destinationLongitude, '目标经度'),
    homeAltitude: normalizeWaypointNumber(waypointForm.homeAltitude, '起点海拔'),
    flightAltitude: normalizeWaypointNumber(waypointForm.flightAltitude, '巡航高度')
  })

  const handleRecommendDeliveryPoint = async () => {
    try {
      waypointRecommendLoading.value = true
      const params = {
        userLatitude: normalizeWaypointNumber(waypointForm.userLatitude, '用户纬度'),
        userLongitude: normalizeWaypointNumber(waypointForm.userLongitude, '用户经度')
      }
      const point = await recommendDeliveryPoint(params)
      recommendedDeliveryPoint.value = point
      waypointForm.destinationLatitude = String(point.latitude)
      waypointForm.destinationLongitude = String(point.longitude)
      waypointForm.flightAltitude = String(point.flightAltitude)
      deliveryPoints.value = await getDeliveryPoints(params)
      ElMessage.success(`已推荐 ${point.name}`)
    } catch (error: any) {
      ElMessage.error(error?.message || '配送点推荐失败')
    } finally {
      waypointRecommendLoading.value = false
    }
  }

  const handlePreviewWaypoints = async () => {
    if (!waypointTargetTask.value) return

    try {
      waypointPreviewLoading.value = true
      waypointPreviewContent.value = await previewWaypointMission(
        waypointTargetTask.value.id,
        buildWaypointParams()
      )
      ElMessage.success('航点文件已生成预览')
    } catch (error: any) {
      ElMessage.error(error?.message || '航点文件预览失败')
    } finally {
      waypointPreviewLoading.value = false
    }
  }

  const handleDownloadWaypoints = async () => {
    if (!waypointTargetTask.value) return

    try {
      waypointDownloading.value = true
      const data = await downloadWaypointMission(waypointTargetTask.value.id, buildWaypointParams())
      download.text(data, `${waypointTargetTask.value.orderNo || waypointTargetTask.value.id}.waypoints`)
      ElMessage.success('航点文件已生成')
      waypointDialogVisible.value = false
    } catch (error: any) {
      ElMessage.error(error?.message || '航点文件生成失败')
    } finally {
      waypointDownloading.value = false
    }
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
    waypointDialogVisible,
    waypointTargetTask,
    waypointDownloading,
    waypointPreviewLoading,
    waypointRecommendLoading,
    waypointPreviewContent,
    deliveryPoints,
    recommendedDeliveryPoint,
    waypointForm,
    filteredTasks,
    handleRefresh,
    handleResetFilter,
    handleShowDetail,
    handleOpenWaypointDialog,
    handleRecommendDeliveryPoint,
    handlePreviewWaypoints,
    handleDownloadWaypoints,
    handleAssignDrone,
    handleMarkFlying,
    handleMarkArrived,
    handleMarkCompleted,
    handleMarkException,
    handleResetToPending
  }
}
