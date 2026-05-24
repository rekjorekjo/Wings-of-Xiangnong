<script lang="ts" setup>
import avatarImg from '@/assets/imgs/avatar.gif'
import { useDesign } from '@/hooks/web/useDesign'
import { useUserStore } from '@/store/modules/user'

defineOptions({ name: 'UserInfo' })

const { push } = useRouter()

const userStore = useUserStore()

const { getPrefixCls } = useDesign()

const prefixCls = getPrefixCls('user-info')

const avatar = computed(() => userStore.user.avatar ?? avatarImg)
const userName = computed(() => userStore.user.nickname ?? 'Admin')

const toProfile = () => {
  push('/user/profile')
}
</script>

<template>
  <div
    class="custom-hover flex items-center cursor-pointer"
    :class="prefixCls"
    @click="toProfile"
  >
    <ElAvatar :src="avatar" alt="" class="w-[calc(var(--logo-height)-25px)] rounded-[50%]" />
    <span class="pl-[5px] text-14px text-[var(--top-header-text-color)] <lg:hidden">
      {{ userName }}
    </span>
  </div>
</template>
