<template>
  <div class="menu">
    <el-menu router :default-openeds="['1']" :default-active="router.currentRoute.value.path" class="border-menu">
      <el-menu-item index="/manage/home">
        <el-icon><house /></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-menu-item index="/manage/notice" v-if="data.user.role=='admin'">
        <el-icon><ChatDotSquare /></el-icon>
        <span>公告管理</span>
      </el-menu-item>
      <el-menu-item index="/manage/banner" v-if="data.user.role=='admin'">
        <el-icon><PictureFilled /></el-icon>
        <span>轮播图管理</span>
      </el-menu-item>
      <el-menu-item index="/manage/type" v-if="data.user.role=='admin'">
        <el-icon><Menu /></el-icon>
        <span>分类管理</span>
      </el-menu-item>
      <el-menu-item index="/manage/goods" >
        <el-icon><InfoFilled /></el-icon>
        <span>鲜花管理</span>
      </el-menu-item>
      <el-menu-item index="/manage/order" v-if="data.user.role=='shopkeeper'">
        <el-icon><Tickets /></el-icon>
        <span>订单管理</span>
      </el-menu-item>
      <el-menu-item index="/manage/comment">
        <el-icon><ChatLineSquare /></el-icon>
        <span>评价管理</span>
      </el-menu-item>
      <el-sub-menu index="1" v-if="data.user.role=='admin'">
        <template #title>
          <el-icon><user /></el-icon>
          <span>用户管理</span>
        </template>
        <el-menu-item index="/manage/user">用户信息</el-menu-item>
        <el-menu-item index="/manage/shopkeeper">商家信息</el-menu-item>
        <el-menu-item index="/manage/admin">管理员信息</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script setup>
import router from "@/router/index.js";
import {reactive} from "vue";
const data = reactive({
  user: JSON.parse(localStorage.getItem('userInfo') || '{}'),
})
</script>

