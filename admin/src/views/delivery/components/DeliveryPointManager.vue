<template>
  <el-card>
    <template #header>
      <div class="delivery-point-header">
        <span class="font-bold">配送点管理</span>
        <div>
          <el-button @click="loadPoints" :loading="loading">
            <Icon icon="ep:refresh" class="mr-5px" /> 刷新
          </el-button>
          <el-button type="primary" @click="handleCreate">
            <Icon icon="ep:plus" class="mr-5px" /> 新增配送点
          </el-button>
        </div>
      </div>
    </template>

    <el-alert
      class="mb-4"
      type="warning"
      :closable="false"
      show-icon
      title="请填写 WGS-84 坐标；未由无人机组实地确认的点请保持“未验证”。"
    />

    <el-table :data="points" v-loading="loading" stripe>
      <el-table-column label="编码" prop="code" width="140" />
      <el-table-column label="名称" prop="name" min-width="160" />
      <el-table-column label="地址" prop="address" min-width="220" show-overflow-tooltip />
      <el-table-column label="纬度" prop="latitude" width="130" />
      <el-table-column label="经度" prop="longitude" width="130" />
      <el-table-column label="高度(m)" prop="flightAltitude" width="90" />
      <el-table-column label="状态" width="120">
        <template #default="scope">
          <el-tag v-if="scope.row.enabled" type="success">启用</el-tag>
          <el-tag v-else type="info">停用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="验证" width="120">
        <template #default="scope">
          <el-tag v-if="scope.row.verified" type="success">已验证</el-tag>
          <el-tag v-else type="warning">未验证</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="排序" prop="sort" width="80" />
      <el-table-column label="备注" prop="remark" min-width="180" show-overflow-tooltip />
      <el-table-column label="操作" width="150" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button link type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" :title="editingPoint ? '编辑配送点' : '新增配送点'" width="560px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="编码" prop="code">
          <el-input v-model="form.code" placeholder="例如 T_LIBRARY" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="例如 图书馆草坪降落点" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="WGS-84 纬度" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="WGS-84 经度" />
        </el-form-item>
        <el-form-item label="巡航高度" prop="flightAltitude">
          <el-input v-model="form.flightAltitude">
            <template #append>m</template>
          </el-input>
        </el-form-item>
        <el-form-item label="排序">
          <el-input-number v-model="form.sort" :min="0" />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.enabled" active-text="启用" inactive-text="停用" />
        </el-form-item>
        <el-form-item label="无人机组验证">
          <el-switch v-model="form.verified" active-text="已验证" inactive-text="未验证" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" type="textarea" :rows="3" placeholder="安全半径、障碍物、采集说明等" />
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="handleSubmit">保存</el-button>
      </template>
    </el-dialog>
  </el-card>
</template>

<script setup lang="ts">
import { ElMessage, ElMessageBox, type FormInstance, type FormRules } from 'element-plus'
import {
  createDeliveryPoint,
  deleteDeliveryPoint,
  getDeliveryPoints,
  updateDeliveryPoint,
  type DeliveryPointSaveReq
} from '@/api/delivery'
import type { DeliveryPoint } from '../types'

const loading = ref(false)
const saving = ref(false)
const points = ref<DeliveryPoint[]>([])
const dialogVisible = ref(false)
const editingPoint = ref<DeliveryPoint | null>(null)
const formRef = ref<FormInstance>()

const createEmptyForm = (): DeliveryPointSaveReq => ({
  code: '',
  name: '',
  address: '',
  latitude: '',
  longitude: '',
  flightAltitude: '10',
  enabled: true,
  verified: false,
  sort: 0,
  remark: ''
})

const form = reactive<DeliveryPointSaveReq>(createEmptyForm())

const isNumberInRange = (min: number, max: number) => (_rule: unknown, value: unknown, callback: (error?: Error) => void) => {
  const parsed = Number(value)
  if (!Number.isFinite(parsed) || parsed < min || parsed > max) {
    callback(new Error(`请输入 ${min} 到 ${max} 之间的数字`))
    return
  }
  callback()
}

const rules: FormRules = {
  code: [{ required: true, message: '请输入配送点编码', trigger: 'blur' }],
  name: [{ required: true, message: '请输入配送点名称', trigger: 'blur' }],
  latitude: [{ required: true, validator: isNumberInRange(-90, 90), trigger: 'blur' }],
  longitude: [{ required: true, validator: isNumberInRange(-180, 180), trigger: 'blur' }],
  flightAltitude: [{ required: true, validator: isNumberInRange(1, 500), trigger: 'blur' }]
}

const resetForm = (point?: DeliveryPoint) => {
  Object.assign(form, createEmptyForm())
  if (!point) return
  Object.assign(form, {
    code: point.code,
    name: point.name,
    address: point.address,
    latitude: point.latitude,
    longitude: point.longitude,
    flightAltitude: point.flightAltitude,
    enabled: point.enabled,
    verified: point.verified,
    sort: point.sort,
    remark: point.remark || ''
  })
}

const loadPoints = async () => {
  loading.value = true
  try {
    points.value = await getDeliveryPoints()
  } finally {
    loading.value = false
  }
}

const handleCreate = () => {
  editingPoint.value = null
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (point: DeliveryPoint) => {
  editingPoint.value = point
  resetForm(point)
  dialogVisible.value = true
}

const handleSubmit = async () => {
  await formRef.value?.validate()
  saving.value = true
  try {
    if (editingPoint.value) {
      await updateDeliveryPoint(editingPoint.value.id, form)
      ElMessage.success('配送点已更新')
    } else {
      await createDeliveryPoint(form)
      ElMessage.success('配送点已新增')
    }
    dialogVisible.value = false
    await loadPoints()
  } finally {
    saving.value = false
  }
}

const handleDelete = async (point: DeliveryPoint) => {
  try {
    await ElMessageBox.confirm(`确定删除配送点“${point.name}”？`, '删除确认', {
      type: 'warning'
    })
  } catch {
    return
  }
  await deleteDeliveryPoint(point.id)
  ElMessage.success('配送点已删除')
  await loadPoints()
}

onMounted(() => {
  loadPoints()
})
</script>

<style scoped>
.delivery-point-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.font-bold {
  font-weight: bold;
}
</style>
