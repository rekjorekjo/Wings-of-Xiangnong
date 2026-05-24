<template>
  <div class="workspace-dispatch">
    <div class="page-header">
      <h1>调度中心</h1>
      <p class="subtitle">无人机配送任务调度与监控。</p>
    </div>

    <div class="modules-grid">
      <el-card shadow="hover" class="module-card" :class="{ 'module-disabled': module.disabled }" v-for="module in modules" :key="module.title">
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
    </div>
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
  background-color: var(--el-bg-color-page);
  min-height: 100%;
}

.page-header {
  margin-bottom: 24px;

  h1 {
    font-size: 24px;
    font-weight: 600;
    margin: 0 0 8px 0;
    color: var(--el-text-color-primary);
  }

  .subtitle {
    font-size: 14px;
    color: var(--el-text-color-secondary);
    margin: 0;
  }
}

.modules-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(320px, 1fr));
  gap: 20px;
  align-items: stretch;
}

.module-card {
  height: 100%;
  min-height: 180px;
  box-sizing: border-box;
  transition: transform 0.18s ease, box-shadow 0.18s ease, border-color 0.18s ease;
  background-color: var(--el-bg-color);

  &:hover {
    transform: translateY(-4px);
    box-shadow: var(--el-box-shadow-light);
    border-color: var(--el-color-primary-light-5);
  }

  :deep(.el-card__body) {
    padding: 20px;
    height: 100%;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
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
  color: var(--el-text-color-primary);
}

.module-desc {
  font-size: 13px;
  color: var(--el-text-color-secondary);
  line-height: 1.5;
  margin-bottom: 16px;
  flex: 1;
}

.module-actions {
  display: flex;
  align-items: center;
}
</style>
