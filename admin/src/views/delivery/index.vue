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
import DeliveryStats from './components/DeliveryStats.vue'
import DroneTable from './components/DroneTable.vue'
import TaskFilter from './components/TaskFilter.vue'
import TaskTable from './components/TaskTable.vue'
import TaskDetailDrawer from './components/TaskDetailDrawer.vue'
import { useDeliveryDashboard } from './composables/useDeliveryDashboard'

const {
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
} = useDeliveryDashboard()
</script>

<style scoped>
.font-bold {
  font-weight: bold;
}
</style>
