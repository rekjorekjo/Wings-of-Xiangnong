<template>
  <div class="mb-4 flex items-center gap-4">
    <el-select :model-value="status" placeholder="任务状态" clearable style="width: 150px" @update:model-value="emit('update:status', $event)">
      <el-option label="全部" value="" />
      <el-option label="待分配" value="pending" />
      <el-option label="已分配" value="assigned" />
      <el-option label="配送中" value="flying" />
      <el-option label="已到达" value="arrived" />
      <el-option label="已完成" value="completed" />
      <el-option label="异常" value="exception" />
    </el-select>
    <el-input 
      :model-value="droneNo" 
      placeholder="无人机编号" 
      clearable 
      style="width: 150px" 
      @update:model-value="emit('update:droneNo', $event)"
    />
    <el-input 
      :model-value="orderNo" 
      placeholder="订单号" 
      clearable 
      style="width: 200px" 
      @update:model-value="emit('update:orderNo', $event)"
    />
    <el-button @click="emit('reset')">重置</el-button>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  status: string
  droneNo: string
  orderNo: string
}>()

const emit = defineEmits<{
  'update:status': [value: string]
  'update:droneNo': [value: string]
  'update:orderNo': [value: string]
  'reset': []
}>()
</script>
