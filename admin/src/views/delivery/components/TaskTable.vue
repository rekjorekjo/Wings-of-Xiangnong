<template>
  <el-table :data="tasks" v-loading="loading" stripe>
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
    <el-table-column label="操作" width="240" fixed="right">
      <template #default="scope">
        <el-button link type="primary" @click="emit('detail', scope.row)">详情</el-button>
        <el-button link type="primary" @click="emit('waypoints', scope.row)">航点</el-button>
        <el-button 
          v-if="scope.row.status === 'pending'" 
          link 
          type="primary" 
          @click="emit('assign', scope.row)"
        >
          分配无人机
        </el-button>
        <el-button 
          v-if="scope.row.status === 'assigned'" 
          link 
          type="primary" 
          @click="emit('flying', scope.row)"
        >
          标记起飞
        </el-button>
        <el-button 
          v-if="scope.row.status === 'flying'" 
          link 
          type="primary" 
          @click="emit('arrived', scope.row)"
        >
          标记到达
        </el-button>
        <el-button 
          v-if="scope.row.status === 'flying'" 
          link 
          type="warning" 
          @click="emit('exception', scope.row)"
        >
          标记异常
        </el-button>
        <el-button 
          v-if="scope.row.status === 'arrived'" 
          link 
          type="primary" 
          @click="emit('complete', scope.row)"
        >
          标记完成
        </el-button>
        <el-button 
          v-if="scope.row.status === 'exception'" 
          link 
          type="warning" 
          @click="emit('reset', scope.row)"
        >
          重置
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup lang="ts">
import type { DeliveryTask } from '../types'
import { getTaskStatusType, getProgressColor } from '../utils'

defineProps<{
  tasks: DeliveryTask[]
  loading: boolean
}>()

const emit = defineEmits<{
  detail: [task: DeliveryTask]
  waypoints: [task: DeliveryTask]
  assign: [task: DeliveryTask]
  flying: [task: DeliveryTask]
  arrived: [task: DeliveryTask]
  exception: [task: DeliveryTask]
  complete: [task: DeliveryTask]
  reset: [task: DeliveryTask]
}>()
</script>
