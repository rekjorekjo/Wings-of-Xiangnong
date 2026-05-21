<template>
  <el-select v-model="selectedId" clearable placeholder="请选择分类" @change="handleChange">
    <el-option v-for="item in categoryList" :key="item.id" :label="item.name" :value="item.id" />
  </el-select>
</template>

<script lang="ts" setup>
defineOptions({ name: 'ProductCategorySelect' })

const props = defineProps<{
  modelValue?: number | string
  parentId?: number
}>()

const emit = defineEmits<{
  'update:modelValue': [value: number | undefined]
}>()

const selectedId = ref<number | undefined>(undefined)

const categoryList = ref<{ id: number; name: string }[]>([
  { id: 1, name: '咖啡' },
  { id: 2, name: '茶饮' },
  { id: 3, name: '轻食' },
  { id: 4, name: '周边' }
])

watch(
  () => props.modelValue,
  (val) => {
    selectedId.value = val !== undefined ? Number(val) : undefined
  },
  { immediate: true }
)

const handleChange = (val: number | undefined) => {
  emit('update:modelValue', val)
}
</script>
