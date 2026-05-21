<template>
  <el-card class="mb-4">
    <template #header>
      <span class="font-bold">无人机状态</span>
    </template>
    <el-table :data="drones" v-loading="loading" stripe>
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
</template>

<script setup lang="ts">
import type { Drone } from '../types'
import { getDroneStatusType, getBatteryColor } from '../utils'

defineProps<{
  drones: Drone[]
  loading: boolean
}>()
</script>

<style scoped>
.font-bold {
  font-weight: bold;
}
</style>
