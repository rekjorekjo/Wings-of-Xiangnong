import { ElMessage } from 'element-plus'
import { getMockDeliveryTasks, getMockDrones } from '@/api/delivery/mock'
import type { DeliveryTask, Drone } from '../types'

export const useDeliveryDashboard = () => {
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

  const filteredTasks = computed(() => {
    return tasks.value.filter(task => {
      if (filterStatus.value && task.status !== filterStatus.value) return false
      if (filterDroneNo.value && !task.droneNo.toLowerCase().includes(filterDroneNo.value.toLowerCase())) return false
      if (filterOrderNo.value && !task.orderNo.toLowerCase().includes(filterOrderNo.value.toLowerCase())) return false
      return true
    })
  })

  const loadData = async () => {
    loading.value = true
    dronesLoading.value = true
    tasksLoading.value = true
    
    try {
      const [dronesData, tasksData] = await Promise.all([
        getMockDrones(),
        getMockDeliveryTasks()
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
