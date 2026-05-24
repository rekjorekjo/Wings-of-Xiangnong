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
  <ElSwitch
    v-model="isDark"
    :class="prefixCls"
    inline-prompt
    active-text="深"
    inactive-text="浅"
    :width="56"
  />
</template>

<style lang="scss" scoped>
:deep(.el-switch) {
  --el-switch-on-color: var(--el-color-primary);
  --el-switch-off-color: #e5e7eb;
}

:deep(.el-switch__core) {
  border: 1px solid var(--el-border-color);
}

:deep(.el-switch.is-checked .el-switch__core) {
  border-color: var(--el-color-primary);
}

:deep(.el-switch:not(.is-checked) .el-switch__inner),
:deep(.el-switch:not(.is-checked) .el-switch__inner span) {
  color: #000 !important;
  font-size: 12px;
  font-weight: 600;
}

:deep(.el-switch.is-checked .el-switch__inner),
:deep(.el-switch.is-checked .el-switch__inner span) {
  color: #fff !important;
  font-size: 12px;
  font-weight: 600;
}
</style>
