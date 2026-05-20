<template>
  <div class="p-4">
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
      <el-table :data="tasks" v-loading="tasksLoading" stripe>
        <el-table-column label="任务ID" prop="id" width="80" />
        <el-table-column label="订单号" prop="orderNo" width="160" />
        <el-table-column label="取货站点" prop="pickupSite" width="150" />
        <el-table-column label="送达站点" prop="dropoffSite" width="150" />
        <el-table-column label="无人机" prop="droneNo" width="100" />
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
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { getMockDeliveryTasks, getMockDrones, type DeliveryTask, type Drone } from '@/api/delivery/mock'

const loading = ref(false)
const dronesLoading = ref(false)
const tasksLoading = ref(false)

const drones = ref<Drone[]>([])
const tasks = ref<DeliveryTask[]>([])

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

const getDroneStatusType = (status: string): 'success' | 'warning' | 'info' | 'danger' => {
  const statusMap: Record<string, 'success' | 'warning' | 'info' | 'danger'> = {
    idle: 'success',
    assigned: 'warning',
    flying: 'primary' as unknown as 'success',
    charging: 'info',
    maintenance: 'danger'
  }
  return statusMap[status] || 'info'
}

const getTaskStatusType = (status: string): 'success' | 'warning' | 'info' | 'danger' => {
  const statusMap: Record<string, 'success' | 'warning' | 'info' | 'danger'> = {
    pending: 'info',
    assigned: 'warning',
    flying: 'primary' as unknown as 'success',
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
