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

    <el-card class="mb-4">
      <template #header>
        <span class="font-bold">无人机状态</span>
      </template>
      <el-table :data="drones" v-loading="dronesLoading" stripe>
        <el-table-column label="无人机编号" prop="droneNo" width="120" />
        <el-table-column label="状态" prop="statusText" width="100">
          <template #default="scope">
            <el-tag :type="getDroneStatusType(scope.row.status)">
              {{ scope.row.statusText }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="电量" prop="battery" width="180">
          <template #default="scope">
            <el-progress 
              :percentage="scope.row.battery" 
              :color="getBatteryColor(scope.row.battery)"
              :stroke-width="12"
            />
          </template>
        </el-table-column>
        <el-table-column label="当前位置" prop="location" />
        <el-table-column label="载荷" prop="payload" width="180" />
        <el-table-column label="更新时间" prop="lastUpdatedAt" width="180" />
      </el-table>
    </el-card>

    <el-card>
      <template #header>
        <span class="font-bold">配送任务</span>
      </template>
      
      <div class="mb-4 flex items-center gap-4">
        <el-select v-model="filterStatus" placeholder="任务状态" clearable style="width: 150px">
          <el-option label="全部" value="" />
          <el-option label="待分配" value="pending" />
          <el-option label="已分配" value="assigned" />
          <el-option label="配送中" value="flying" />
          <el-option label="已到达" value="arrived" />
          <el-option label="已完成" value="completed" />
          <el-option label="异常" value="exception" />
        </el-select>
        <el-input v-model="filterDroneNo" placeholder="无人机编号" clearable style="width: 150px" />
        <el-input v-model="filterOrderNo" placeholder="订单号" clearable style="width: 200px" />
        <el-button @click="handleResetFilter">重置</el-button>
      </div>

      <el-table :data="filteredTasks" v-loading="tasksLoading" stripe>
        <el-table-column label="任务ID" prop="id" width="80" />
        <el-table-column label="订单号" prop="orderNo" width="160" />
        <el-table-column label="取货站点" prop="pickupSite" width="150" />
        <el-table-column label="送达站点" prop="dropoffSite" width="150" />
        <el-table-column label="无人机" prop="droneNo" width="100">
          <template #default="scope">
            <span>{{ scope.row.droneNo || '-' }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" prop="statusText" width="100">
          <template #default="scope">
            <el-tag :type="getTaskStatusType(scope.row.status)">
              {{ scope.row.statusText }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="进度" prop="progress" width="180">
          <template #default="scope">
            <el-progress 
              :percentage="scope.row.progress" 
              :color="getProgressColor(scope.row.progress)"
              :stroke-width="12"
            />
          </template>
        </el-table-column>
        <el-table-column label="预计到达(分钟)" prop="etaMinutes" width="120">
          <template #default="scope">
            <span v-if="scope.row.etaMinutes > 0">{{ scope.row.etaMinutes }} 分钟</span>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" prop="createdAt" width="180" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="scope">
            <el-button link type="primary" @click="handleShowDetail(scope.row)">详情</el-button>
            <el-button 
              v-if="scope.row.status === 'pending'" 
              link 
              type="primary" 
              @click="handleAssignDrone(scope.row)"
            >
              分配无人机
            </el-button>
            <el-button 
              v-if="scope.row.status === 'assigned'" 
              link 
              type="primary" 
              @click="handleMarkFlying(scope.row)"
            >
              标记起飞
            </el-button>
            <el-button 
              v-if="scope.row.status === 'flying'" 
              link 
              type="primary" 
              @click="handleMarkArrived(scope.row)"
            >
              标记到达
            </el-button>
            <el-button 
              v-if="scope.row.status === 'flying'" 
              link 
              type="warning" 
              @click="handleMarkException(scope.row)"
            >
              标记异常
            </el-button>
            <el-button 
              v-if="scope.row.status === 'arrived'" 
              link 
              type="primary" 
              @click="handleMarkCompleted(scope.row)"
            >
              标记完成
            </el-button>
            <el-button 
              v-if="scope.row.status === 'exception'" 
              link 
              type="warning" 
              @click="handleResetToPending(scope.row)"
            >
              重置
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-drawer v-model="drawerVisible" title="任务详情" size="500px">
      <div v-if="currentTask" class="p-4">
        <el-descriptions :column="1" border>
          <el-descriptions-item label="任务ID">{{ currentTask.id }}</el-descriptions-item>
          <el-descriptions-item label="订单号">{{ currentTask.orderNo }}</el-descriptions-item>
          <el-descriptions-item label="取货站点">{{ currentTask.pickupSite }}</el-descriptions-item>
          <el-descriptions-item label="送达站点">{{ currentTask.dropoffSite }}</el-descriptions-item>
          <el-descriptions-item label="无人机编号">
            {{ currentTask.droneNo || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="状态">
            <el-tag :type="getTaskStatusType(currentTask.status)">
              {{ currentTask.statusText }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="进度">
            <el-progress 
              :percentage="currentTask.progress" 
              :color="getProgressColor(currentTask.progress)"
            />
          </el-descriptions-item>
          <el-descriptions-item label="预计到达">
            {{ currentTask.etaMinutes > 0 ? currentTask.etaMinutes + ' 分钟' : '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ currentTask.createdAt }}</el-descriptions-item>
        </el-descriptions>
      </div>
    </el-drawer>
  </div>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { getMockDeliveryTasks, getMockDrones, type DeliveryTask, type Drone } from '@/api/delivery/mock'

type TagType = '' | 'success' | 'warning' | 'info' | 'primary' | 'danger'
type TaskStatus = 'pending' | 'assigned' | 'flying' | 'arrived' | 'completed' | 'exception'

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

const getDroneStatusType = (status: string): TagType => {
  const statusMap: Record<string, TagType> = {
    idle: 'success',
    assigned: 'warning',
    flying: 'primary',
    charging: 'info',
    maintenance: 'danger'
  }
  return statusMap[status] || 'info'
}

const getTaskStatusType = (status: string): TagType => {
  const statusMap: Record<string, TagType> = {
    pending: 'info',
    assigned: 'warning',
    flying: 'primary',
    arrived: 'warning',
    completed: 'success',
    exception: 'danger'
  }
  return statusMap[status] || 'info'
}

const getBatteryColor = (battery: number): string => {
  if (battery >= 80) return '#67c23a'
  if (battery >= 50) return '#e6a23c'
  return '#f56c6c'
}

const getProgressColor = (progress: number): string => {
  if (progress >= 100) return '#67c23a'
  if (progress >= 50) return '#409eff'
  return '#e6a23c'
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
