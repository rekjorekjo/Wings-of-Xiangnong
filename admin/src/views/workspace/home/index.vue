<template>
  <div class="workspace-home">
    <div class="page-header">
      <h1>香农之翼运营与调度工作台</h1>
      <p class="subtitle">东南大学九龙湖校区 DC 香农咖啡无人机配送 MVP</p>
    </div>

    <el-row :gutter="20" class="stats-row">
      <el-col :xs="12" :sm="8" :lg="4" v-for="stat in stats" :key="stat.title">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-icon" :style="{ backgroundColor: stat.color }">
            <Icon :icon="stat.icon" :size="24" />
          </div>
          <div class="stat-content">
            <div class="stat-value">{{ stat.value }}</div>
            <div class="stat-title">{{ stat.title }}</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <div class="section-title">快捷入口</div>
    <el-row :gutter="20" class="shortcuts-row">
      <el-col :xs="12" :sm="8" :lg="6" v-for="shortcut in shortcuts" :key="shortcut.title">
        <el-card shadow="hover" class="shortcut-card" @click="navigateByCandidates(shortcut.paths)">
          <div class="shortcut-icon">
            <Icon :icon="shortcut.icon" :size="32" />
          </div>
          <div class="shortcut-title">{{ shortcut.title }}</div>
          <div class="shortcut-desc">{{ shortcut.desc }}</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Icon } from '@/components/Icon'
import * as OrderApi from '@/api/business/orders'

const router = useRouter()

const stats = ref([
  { title: '今日订单数', value: '-', icon: 'ep:document', color: '#409EFF' },
  { title: '今日销售额', value: '-', icon: 'ep:money', color: '#67C23A' },
  { title: '配送中任务', value: '-', icon: 'ep:position', color: '#E6A23C' },
  { title: '可用无人机', value: '-', icon: 'ep:plane', color: '#909399' },
  { title: '异常任务', value: '-', icon: 'ep:warning', color: '#F56C6C' },
  { title: '平均配送时长', value: '-', icon: 'ep:timer', color: '#9B59B6' }
])

const shortcuts = ref([
  { title: '运营中心', desc: '商品、订单、用户管理', icon: 'ep:shop', paths: ['/workspace/operations'] },
  { title: '调度中心', desc: '配送任务、无人机状态', icon: 'ep:position', paths: ['/workspace/dispatch'] },
  { title: '订单管理', desc: '查看和处理订单', icon: 'ep:document', paths: ['/business/orders', '/mall/order/store-order'] },
  { title: '配送任务', desc: '查看配送任务列表', icon: 'ep:van', paths: ['/delivery/tasks'] }
])

const loadStats = async () => {
  try {
    const data = await OrderApi.getOrderStats()
    stats.value[0].value = data.todayCount || 0
    stats.value[1].value = `¥${data.todayPrice || 0}`
  } catch (e) {
    console.error('Failed to load stats', e)
  }
}

const navigateByCandidates = (paths: string[]) => {
  const target = paths.find((path) => router.resolve(path).matched.length > 0)
  if (target) {
    router.push(target)
  } else {
    ElMessage.warning('该功能暂未开放')
  }
}

onMounted(() => {
  loadStats()
})
</script>

<style lang="scss" scoped>
.workspace-home {
  padding: 20px;
}

.page-header {
  margin-bottom: 24px;
  
  h1 {
    font-size: 24px;
    font-weight: 600;
    margin: 0 0 8px 0;
    color: #303133;
  }
  
  .subtitle {
    font-size: 14px;
    color: #909399;
    margin: 0;
  }
}

.stats-row {
  margin-bottom: 24px;
}

.stat-card {
  display: flex;
  align-items: center;
  padding: 16px;
  cursor: default;
  
  :deep(.el-card__body) {
    display: flex;
    align-items: center;
    width: 100%;
    padding: 16px;
  }
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  margin-right: 12px;
  flex-shrink: 0;
}

.stat-content {
  flex: 1;
  min-width: 0;
}

.stat-value {
  font-size: 24px;
  font-weight: 600;
  color: #303133;
  line-height: 1.2;
}

.stat-title {
  font-size: 13px;
  color: #909399;
  margin-top: 4px;
}

.section-title {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
  margin-bottom: 16px;
}

.shortcuts-row {
  margin-bottom: 24px;
}

.shortcut-card {
  cursor: pointer;
  transition: all 0.3s;
  text-align: center;
  padding: 24px 16px;
  
  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }
  
  :deep(.el-card__body) {
    padding: 24px 16px;
  }
}

.shortcut-icon {
  color: #409EFF;
  margin-bottom: 12px;
}

.shortcut-title {
  font-size: 16px;
  font-weight: 500;
  color: #303133;
  margin-bottom: 8px;
}

.shortcut-desc {
  font-size: 13px;
  color: #909399;
}
</style>
