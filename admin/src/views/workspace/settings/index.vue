<template>
  <div class="workspace-settings">
    <div class="page-header">
      <h1>系统设置</h1>
      <p class="subtitle">管理系统账号、权限、字典、参数和日志。</p>
    </div>

    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :lg="8" :xl="6" v-for="module in modules" :key="module.title">
        <el-card shadow="hover" class="module-card">
          <div class="module-header">
            <div class="module-icon" :style="{ backgroundColor: module.color }">
              <Icon :icon="module.icon" :size="24" />
            </div>
            <div class="module-title">{{ module.title }}</div>
          </div>
          <div class="module-desc">{{ module.desc }}</div>
          <div class="module-actions">
            <template v-for="(action, index) in module.actions" :key="action.label">
              <el-button
                v-if="action.type === 'navigate'"
                type="primary"
                link
                @click="navigateByCandidates(action.paths)"
              >
                {{ action.label }}
              </el-button>
              <el-button
                v-else-if="action.type === 'logout'"
                type="danger"
                link
                @click="handleLogout"
              >
                {{ action.label }}
              </el-button>
              <el-divider v-if="index < module.actions.length - 1" direction="vertical" />
            </template>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Icon } from '@/components/Icon'
import { useUserStore } from '@/store/modules/user'
import { useTagsViewStore } from '@/store/modules/tagsView'

const router = useRouter()
const userStore = useUserStore()
const tagsViewStore = useTagsViewStore()

const modules = [
  {
    title: '账号权限',
    desc: '管理后台用户、角色、部门和岗位',
    icon: 'ep:user-filled',
    color: '#409EFF',
    actions: [
      { label: '用户管理', type: 'navigate', paths: ['/system/user'] },
      { label: '角色管理', type: 'navigate', paths: ['/system/role'] },
      { label: '部门管理', type: 'navigate', paths: ['/system/dept'] },
      { label: '岗位管理', type: 'navigate', paths: ['/system/post'] }
    ]
  },
  {
    title: '系统菜单',
    desc: '管理系统菜单和权限配置',
    icon: 'ep:menu',
    color: '#67C23A',
    actions: [{ label: '菜单管理', type: 'navigate', paths: ['/system/menu'] }]
  },
  {
    title: '字典配置',
    desc: '管理系统字典类型和字典数据',
    icon: 'ep:collection',
    color: '#E6A23C',
    actions: [
      { label: '字典类型', type: 'navigate', paths: ['/system/dict'] },
      { label: '字典数据', type: 'navigate', paths: ['/system/dict'] }
    ]
  },
  {
    title: '参数配置',
    desc: '管理系统参数配置',
    icon: 'ep:setting',
    color: '#F56C6C',
    actions: [{ label: '参数管理', type: 'navigate', paths: ['/infra/config'] }]
  },
  {
    title: '文件管理',
    desc: '管理文件列表和文件配置',
    icon: 'ep:folder',
    color: '#909399',
    actions: [
      { label: '文件列表', type: 'navigate', paths: ['/infra/file'] },
      { label: '文件配置', type: 'navigate', paths: ['/infra/file-config'] }
    ]
  },
  {
    title: '通知公告',
    desc: '管理公告、站内信模板和消息',
    icon: 'ep:message',
    color: '#9B59B6',
    actions: [
      { label: '公告管理', type: 'navigate', paths: ['/system/notice'] },
      { label: '站内信模板', type: 'navigate', paths: ['/system/notify-template'] },
      { label: '站内信消息', type: 'navigate', paths: ['/system/notify-message'] }
    ]
  },
  {
    title: '日志记录',
    desc: '查看登录日志和操作日志',
    icon: 'ep:document',
    color: '#3498DB',
    actions: [
      { label: '登录日志', type: 'navigate', paths: ['/system/loginlog'] },
      { label: '操作日志', type: 'navigate', paths: ['/system/operatelog'] }
    ]
  },
  {
    title: '账户操作',
    desc: '管理当前登录账号和退出系统',
    icon: 'ep:user',
    color: '#17A2B8',
    actions: [
      { label: '个人中心', type: 'navigate', paths: ['/user/profile'] },
      { label: '退出系统', type: 'logout' }
    ]
  }
]

const navigateByCandidates = (paths: string[]) => {
  const target = paths.find((path) => router.resolve(path).matched.length > 0)
  if (target) {
    router.push(target)
  } else {
    ElMessage.warning('该功能暂未开放')
  }
}

const handleLogout = async () => {
  try {
    await ElMessageBox.confirm('确定要退出系统吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await userStore.loginOut()
    tagsViewStore.delAllViews()
    router.replace('/login?redirect=/index')
  } catch {}
}
</script>

<style lang="scss" scoped>
.workspace-settings {
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

  &:hover {
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
  flex-wrap: wrap;
  gap: 4px;
}
</style>
