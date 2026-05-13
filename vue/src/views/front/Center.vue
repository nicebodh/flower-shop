<template>
  <div class="personal-center">
    <!--       左侧div-->
    <div class="user-content-left">
      <!-- 左上角用户信息-->
      <div class="center-info-box">
        <img :src="data.user.avatar" class="avatar">
        <div>
          <p class="name">{{data.user.name}}</p>
          <p class="username">用户名 {{data.user.username}}</p>
        </div>
      </div>

      <!--用户信息列表-->
      <div class="setting-box">
        <p class="title">我的信息</p>
        <div class="list">
          <div class="user-list " :class="{ active: activeMenu === 'Cart' }" @click="clickMenu('Cart')" >
            <el-icon  class="icon"><ShoppingCart /></el-icon>
            购物车
          </div>
          <div class="user-list " :class="{ active: activeMenu === 'Collect' }" @click="clickMenu('Collect')" >
            <el-icon  class="icon"><Star /></el-icon>
            我的收藏
          </div>
          <div class="user-list " :class="{ active: activeMenu === 'Order' }" @click="clickMenu('Order')" >
            <el-icon  class="icon"><Tickets /></el-icon>
            我的订单
          </div>
          <div class="user-list " :class="{ active: activeMenu === 'Address' }" @click="clickMenu('Address')" >
            <el-icon  class="icon"><Location /></el-icon>
            我的地址
          </div>

        </div>

      </div>
      <!--用户个人信息-->
      <div class="setting-box">
        <p class="title">账户设置</p>
        <div class="list">
          <div class="user-list " :class="{ active: activeMenu === 'UserInfo' }" @click="clickMenu('UserInfo')" >
            <el-icon  class="icon"><EditPen /></el-icon>
            编辑资料
          </div>
          <div class="user-list" :class="{ active: activeMenu === 'Password' }" @click="clickMenu('Password')">
            <el-icon class="icon"><Lock /></el-icon>
            修改密码
          </div>
        </div>

      </div>

    </div>
    <div class="user-content-right">
      <router-view />
    </div>
  </div>


</template>


<script setup>


import {reactive,onMounted,ref,onBeforeUnmount } from "vue";
import router from "@/router/index.js";
import {Star} from "@element-plus/icons-vue";
import Cart from "@/views/front/center/Cart.vue";

const data = reactive({
  user: JSON.parse(localStorage.getItem('userInfo') || '{}'),
});
const activeMenu = ref('UserInfo')


onMounted(() => {
  const savedMenu = localStorage.getItem('activeUserMenu')
  if (savedMenu) activeMenu.value = savedMenu
})

const clickMenu = (menu) => {
  activeMenu.value = menu
  localStorage.setItem('activeUserMenu', menu)
  router.push({name: menu})
}
onBeforeUnmount(() => {
  // 退出当前页面清空activeUserMenu
  localStorage.removeItem("activeUserMenu");
});
</script>
