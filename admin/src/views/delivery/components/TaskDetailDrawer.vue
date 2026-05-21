<template>
  <el-drawer :model-value="modelValue" title="任务详情" size="500px" @update:model-value="emit('update:modelValue', $event)">
    <div v-if="task" class="p-4">
      <el-descriptions :column="1" border>
        <el-descriptions-item label="任务ID">{{ task.id }}</el-descriptions-item>
        <el-descriptions-item label="订单号">{{ task.orderNo }}</el-descriptions-item>
        <el-descriptions-item label="取货站点">{{ task.pickupSite }}</el-descriptions-item>
        <el-descriptions-item label="送达站点">{{ task.dropoffSite }}</el-descriptions-item>
        <el-descriptions-item label="无人机编号">
          {{ task.droneNo || '-' }}
        </el-descriptions-item>
        <el-descriptions-item label="状态">
          <el-tag :type="getTaskStatusType(task.status)">
            {{ task.statusText }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="进度">
          <el-progress 
            :percentage="task.progress" 
            :color="getProgressColor(task.progress)"
          />
        </el-descriptions-item>
        <el-descriptions-item label="预计到达">
          {{ task.etaMinutes > 0 ? task.etaMinutes + ' 分钟' : '-' }}
        </el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ task.createdAt }}</el-descriptions-item>
      </el-descriptions>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import type { DeliveryTask } from '../types'
import { getTaskStatusType, getProgressColor } from '../utils'

defineProps<{
  modelValue: boolean
  task: DeliveryTask | null
}>()

const emit = defineEmits<{
  'update:modelValue': [value: boolean]
}>()
</script>
