<template>
  <div class="p-4">
    <div class="mb-4">
      <div class="text-xl font-bold mb-1">配送任务</div>
      <div class="text-gray-500 text-sm">查看无人机状态和配送任务进度</div>
    </div>

    <div class="mb-4">
      <el-button type="primary" @click="handleRefresh" :loading="loading">
        <Icon icon="ep:refresh" class="mr-5px" /> 刷新
      </el-button>
    </div>

    <DeliveryStats :tasks="tasks" :drones="drones" class="mb-4" />

    <DroneTable :drones="drones" :loading="dronesLoading" />

    <el-card>
      <template #header>
        <span class="font-bold">配送任务</span>
      </template>
      
      <TaskFilter 
        v-model:status="filterStatus"
        v-model:drone-no="filterDroneNo"
        v-model:order-no="filterOrderNo"
        class="mb-4"
        @reset="handleResetFilter"
      />

      <TaskTable 
        :tasks="filteredTasks" 
        :loading="tasksLoading"
        @detail="handleShowDetail"
        @waypoints="handleOpenWaypointDialog"
        @assign="handleAssignDrone"
        @flying="handleMarkFlying"
        @arrived="handleMarkArrived"
        @exception="handleMarkException"
        @complete="handleMarkCompleted"
        @reset="handleResetToPending"
      />
    </el-card>

    <TaskDetailDrawer 
      v-model="drawerVisible"
      :task="currentTask"
    />

    <el-dialog v-model="waypointDialogVisible" title="生成航点文件" width="520px">
      <el-alert
        class="mb-4"
        type="warning"
        :closable="false"
        show-icon
        title="请填写 WGS-84 坐标；高德、腾讯、百度坐标不能直接用于飞控航点。"
      />

      <el-form label-width="110px">
        <el-form-item label="订单号">
          <el-input :model-value="waypointTargetTask?.orderNo || '-'" disabled />
        </el-form-item>
        <el-form-item label="用户纬度">
          <el-input v-model="waypointForm.userLatitude" />
        </el-form-item>
        <el-form-item label="用户经度">
          <el-input v-model="waypointForm.userLongitude" />
        </el-form-item>
        <el-form-item label="推荐配送点">
          <div class="waypoint-recommend">
            <el-button :loading="waypointRecommendLoading" @click="handleRecommendDeliveryPoint">
              推荐最近点
            </el-button>
            <span v-if="recommendedDeliveryPoint" class="waypoint-recommend__text">
              {{ recommendedDeliveryPoint.name }}
              <template v-if="recommendedDeliveryPoint.distanceMeters != null">
                ，距用户 {{ recommendedDeliveryPoint.distanceMeters }}m
              </template>
              <template v-if="!recommendedDeliveryPoint.verified">
                ，待验证
              </template>
            </span>
          </div>
        </el-form-item>
        <el-form-item label="起点纬度">
          <el-input v-model="waypointForm.startLatitude" />
        </el-form-item>
        <el-form-item label="起点经度">
          <el-input v-model="waypointForm.startLongitude" />
        </el-form-item>
        <el-form-item label="目标纬度">
          <el-input v-model="waypointForm.destinationLatitude" />
        </el-form-item>
        <el-form-item label="目标经度">
          <el-input v-model="waypointForm.destinationLongitude" />
        </el-form-item>
        <el-form-item label="起点海拔">
          <el-input v-model="waypointForm.homeAltitude">
            <template #append>m</template>
          </el-input>
        </el-form-item>
        <el-form-item label="巡航高度">
          <el-input v-model="waypointForm.flightAltitude">
            <template #append>m</template>
          </el-input>
        </el-form-item>
        <el-form-item label="文件预览">
          <el-input
            :model-value="waypointPreviewContent"
            type="textarea"
            :rows="8"
            readonly
            placeholder="点击预览后显示 QGC WPL 110 航点文件内容"
          />
        </el-form-item>
      </el-form>

      <el-table v-if="deliveryPoints.length" :data="deliveryPoints" size="small" class="mb-4">
        <el-table-column label="配送点" prop="name" min-width="150" />
        <el-table-column label="距离" width="90">
          <template #default="scope">
            <span v-if="scope.row.distanceMeters != null">{{ scope.row.distanceMeters }}m</span>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="90">
          <template #default="scope">
            <el-tag v-if="scope.row.verified" type="success">已验证</el-tag>
            <el-tag v-else type="warning">待验证</el-tag>
          </template>
        </el-table-column>
      </el-table>

      <template #footer>
        <el-button @click="waypointDialogVisible = false">取消</el-button>
        <el-button :loading="waypointPreviewLoading" @click="handlePreviewWaypoints">
          预览
        </el-button>
        <el-button type="primary" :loading="waypointDownloading" @click="handleDownloadWaypoints">
          下载 .waypoints
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import DeliveryStats from './components/DeliveryStats.vue'
import DroneTable from './components/DroneTable.vue'
import TaskFilter from './components/TaskFilter.vue'
import TaskTable from './components/TaskTable.vue'
import TaskDetailDrawer from './components/TaskDetailDrawer.vue'
import { useDeliveryDashboard } from './composables/useDeliveryDashboard'

const {
  loading,
  dronesLoading,
  tasksLoading,
  drones,
  tasks,
  filterStatus,
  filterDroneNo,
  filterOrderNo,
  drawerVisible,
  currentTask,
  waypointDialogVisible,
  waypointTargetTask,
  waypointDownloading,
  waypointPreviewLoading,
  waypointRecommendLoading,
  waypointPreviewContent,
  deliveryPoints,
  recommendedDeliveryPoint,
  waypointForm,
  filteredTasks,
  handleRefresh,
  handleResetFilter,
  handleShowDetail,
  handleOpenWaypointDialog,
  handleRecommendDeliveryPoint,
  handlePreviewWaypoints,
  handleDownloadWaypoints,
  handleAssignDrone,
  handleMarkFlying,
  handleMarkArrived,
  handleMarkCompleted,
  handleMarkException,
  handleResetToPending
} = useDeliveryDashboard()
</script>

<style scoped>
.font-bold {
  font-weight: bold;
}

.waypoint-recommend {
  display: flex;
  align-items: center;
  gap: 12px;
  min-height: 32px;
}

.waypoint-recommend__text {
  color: #606266;
}
</style>
