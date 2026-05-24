<script lang="ts" setup>
import { useAppStore } from '@/store/modules/app'
import { useDesign } from '@/hooks/web/useDesign'

defineOptions({ name: 'ThemeSwitch' })

const { getPrefixCls } = useDesign()

const prefixCls = getPrefixCls('theme-switch')

const appStore = useAppStore()

const isDark = computed({
  get: () => appStore.getIsDark,
  set: (val: boolean) => {
    appStore.setIsDark(val)
  }
})
</script>

<template>
  <div :class="prefixCls" class="theme-switch">
    <span class="theme-label" :class="{ active: !isDark }">浅</span>
    <ElSwitch v-model="isDark" />
    <span class="theme-label" :class="{ active: isDark }">深</span>
  </div>
</template>

<style lang="scss" scoped>
.theme-switch {
  display: flex;
  align-items: center;
  gap: 8px;
}

.theme-label {
  font-size: 13px;
  font-weight: 500;
  color: var(--el-text-color-secondary);
  transition: color 0.2s;

  &.active {
    color: var(--el-color-primary);
  }
}

:deep(.el-switch) {
  --el-switch-on-color: var(--el-color-primary);
  --el-switch-off-color: var(--el-border-color);
}

:deep(.el-switch__core) {
  border: 1px solid var(--el-border-color);
}

:deep(.el-switch.is-checked .el-switch__core) {
  border-color: var(--el-color-primary);
}
</style>
