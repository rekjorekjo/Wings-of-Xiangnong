<template>
  <el-row :gutter="16">
    <el-col :span="4">
      <el-card shadow="hover">
        <div class="text-gray-500 text-sm mb-2">今日配送任务数</div>
        <div class="text-2xl font-bold">{{ stats.todayTasks }}</div>
      </el-card>
    </el-col>
    <el-col :span="4">
      <el-card shadow="hover">
        <div class="text-gray-500 text-sm mb-2">配送中任务数</div>
        <div class="text-2xl font-bold text-blue-500">{{ stats.flyingTasks }}</div>
      </el-card>
    </el-col>
    <el-col :span="4">
      <el-card shadow="hover" :class="{ 'is-danger': stats.exceptionTasks > 0 }">
        <div class="text-gray-500 text-sm mb-2">异常任务数</div>
        <div class="text-2xl font-bold" :class="stats.exceptionTasks > 0 ? 'text-red-500' : ''">
          {{ stats.exceptionTasks }}
        </div>
      </el-card>
    </el-col>
    <el-col :span="4">
      <el-card shadow="hover">
        <div class="text-gray-500 text-sm mb-2">可用无人机数</div>
        <div class="text-2xl font-bold text-green-500">{{ stats.availableDrones }}</div>
      </el-card>
    </el-col>
    <el-col :span="4">
      <el-card shadow="hover">
        <div class="text-gray-500 text-sm mb-2">平均电量</div>
        <div class="text-2xl font-bold">{{ stats.avgBattery }}%</div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import type { DeliveryTask, Drone } from '../types'

const props = defineProps<{
  tasks: DeliveryTask[]
  drones: Drone[]
}>()

const stats = computed(() => {
  const todayTasks = props.tasks.length
  const flyingTasks = props.tasks.filter(t => t.status === 'flying').length
  const exceptionTasks = props.tasks.filter(t => t.status === 'exception').length
  const availableDrones = props.drones.filter(d => d.status === 'idle').length
  const avgBattery = props.drones.length > 0
    ? Math.round(props.drones.reduce((sum, d) => sum + d.battery, 0) / props.drones.length)
    : 0
  
  return {
    todayTasks,
    flyingTasks,
    exceptionTasks,
    availableDrones,
    avgBattery
  }
})
</script>

<style scoped>
.font-bold {
  font-weight: bold;
}
.is-danger {
  border: 1px solid #f56c6c;
}
</style>
