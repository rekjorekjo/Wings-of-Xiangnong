<template>
  <div class="workspace-dispatch">
    <div class="page-header">
      <h1>调度中心</h1>
      <p class="subtitle">无人机配送任务调度与监控。</p>
    </div>

    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :lg="8" :xl="6" v-for="module in modules" :key="module.title">
        <el-card shadow="hover" class="module-card" :class="{ 'module-disabled': module.disabled }">
          <div class="module-header">
            <div class="module-icon" :style="{ backgroundColor: module.disabled ? '#C0C4CC' : module.color }">
              <Icon :icon="module.icon" :size="24" />
            </div>
            <div class="module-title">{{ module.title }}</div>
          </div>
          <div class="module-desc">{{ module.desc }}</div>
          <div class="module-actions">
            <el-button 
              type="primary" 
              link 
              :disabled="module.disabled"
              @click="navigateByCandidates(module.paths)"
            >
              {{ module.disabled ? '建设中' : module.mainAction }}
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Icon } from '@/components/Icon'

const router = useRouter()

const modules = [
  {
    title: '配送任务',
    desc: '查看和管理无人机配送任务',
    icon: 'ep:van',
    color: '#409EFF',
    mainAction: '任务列表',
    paths: ['/delivery/tasks'],
    disabled: false
  },
  {
    title: '调度看板',
    desc: '实时查看配送调度状态和任务分布',
    icon: 'ep:data-board',
    color: '#67C23A',
    mainAction: '查看看板',
    paths: [],
    disabled: true
  },
  {
    title: '无人机状态',
    desc: '查看无人机在线状态、电量和位置',
    icon: 'ep:plane',
    color: '#E6A23C',
    mainAction: '查看状态',
    paths: [],
    disabled: true
  },
  {
    title: '异常告警',
    desc: '配送异常、低电量、超时等告警信息',
    icon: 'ep:warning',
    color: '#F56C6C',
    mainAction: '查看告警',
    paths: [],
    disabled: true
  },
  {
    title: '任务轨迹',
    desc: '查看配送任务的飞行轨迹和位置',
    icon: 'ep:map-location',
    color: '#9B59B6',
    mainAction: '查看轨迹',
    paths: [],
    disabled: true
  },
  {
    title: '站点航线',
    desc: '管理配送站点和航线配置',
    icon: 'ep:guide',
    color: '#3498DB',
    mainAction: '站点航线',
    paths: [],
    disabled: true
  }
]

const navigateByCandidates = (paths: string[]) => {
  if (!paths || paths.length === 0) {
    ElMessage.warning('该功能暂未开放')
    return
  }
  const target = paths.find((path) => router.resolve(path).matched.length > 0)
  if (target) {
    router.push(target)
  } else {
    ElMessage.warning('该功能暂未开放')
  }
}
</script>

<style lang="scss" scoped>
.workspace-dispatch {
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

.module-card {
  margin-bottom: 20px;
  transition: all 0.3s;
  
  &:hover:not(.module-disabled) {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  }
  
  :deep(.el-card__body) {
    padding: 20px;
  }
}

.module-header {
  display: flex;
  align-items: center;
  margin-bottom: 12px;
}

.module-icon {
  width: 40px;
  height: 40px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  margin-right: 12px;
  flex-shrink: 0;
}

.module-title {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.module-desc {
  font-size: 13px;
  color: #909399;
  line-height: 1.5;
  margin-bottom: 16px;
  min-height: 40px;
}

.module-actions {
  display: flex;
  align-items: center;
}
</style>
