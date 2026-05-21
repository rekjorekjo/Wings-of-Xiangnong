<template>
  <div class="p-4">
    <div class="mb-4">
      <div class="text-xl font-bold mb-1">配送任务</div>
      <div class="text-gray-500 text-sm">查看无人机状态和配送任务进度</div>
    </div>

    <div class="mb-4">
      <el-button type="primary" @click="handleRefresh" :loading="loading">
        <Icon icon="ep:refresh" class="mr-5px" /> 刷新
      </el-button>
    </div>

    <DeliveryStats :tasks="tasks" :drones="drones" class="mb-4" />

    <DroneTable :drones="drones" :loading="dronesLoading" />

    <el-card>
      <template #header>
        <span class="font-bold">配送任务</span>
      </template>
      
      <TaskFilter 
        v-model:status="filterStatus"
        v-model:drone-no="filterDroneNo"
        v-model:order-no="filterOrderNo"
        class="mb-4"
        @reset="handleResetFilter"
      />

      <TaskTable 
        :tasks="filteredTasks" 
        :loading="tasksLoading"
        @detail="handleShowDetail"
        @assign="handleAssignDrone"
        @flying="handleMarkFlying"
        @arrived="handleMarkArrived"
        @exception="handleMarkException"
        @complete="handleMarkCompleted"
        @reset="handleResetToPending"
      />
    </el-card>

    <TaskDetailDrawer 
      v-model="drawerVisible"
      :task="currentTask"
    />
  </div>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { getMockDeliveryTasks, getMockDrones } from '@/api/delivery/mock'
import type { DeliveryTask, Drone } from './types'
import DeliveryStats from './components/DeliveryStats.vue'
import DroneTable from './components/DroneTable.vue'
import TaskFilter from './components/TaskFilter.vue'
import TaskTable from './components/TaskTable.vue'
import TaskDetailDrawer from './components/TaskDetailDrawer.vue'

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
</script>

<style scoped>
.font-bold {
  font-weight: bold;
}
</style>
