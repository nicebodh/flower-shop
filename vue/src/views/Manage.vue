<template>
 <div>
<!--顶部-->
 <div class="manage-header">
<!--   顶部左侧系统名称-->
   <div class="header-left">
     <img src="@/assets/img/logo.png" class="logo">
     <span class="title"> 鲜花商城</span>
 </div>
   <!--   顶部中间-->
   <div  class="header-center">
     首页 / {{router.currentRoute.value.meta.name }}

   </div>
   <!--   顶部头像用户名-->
   <div class="header-right">
     <el-dropdown>
      <div class="info">
         <img :src="data.user.avatar" class="avatar">
     <span class="name">{{data.user.name}}</span>
        <el-icon class="el-icon--right" color="#fff">
          <arrow-down />
        </el-icon>
    </div>
       <template #dropdown>
         <el-dropdown-menu>
           <el-dropdown-item @click="router.push('/manage/userInfo')">个人中心</el-dropdown-item>
           <el-dropdown-item @click="router.push('/manage/password')">修改密码</el-dropdown-item>
           <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
         </el-dropdown-menu>
       </template>
     </el-dropdown>
   </div>
 </div>
   <!--下方-->
   <div class="container">
     <!--下方左侧菜单-->
     <Aside></Aside>
     <!--下方右侧内容-->
     <div class="content">
       <RouterView />
     </div>
   </div>
   <AiChat />
 </div>

</template>

<script setup>
import Aside from "@/components/Aside.vue";
import router from "@/router/index.js";
import {reactive} from "vue";
import {ElMessageBox} from "element-plus";
import AiChat from "@/components/AiChat.vue";

const data = reactive({
  user: JSON.parse(localStorage.getItem('userInfo') || '{}'),
});

const logout = () => {
  ElMessageBox.confirm('您确定退出登录吗？', '退出', {type: "warning"}).then(response => {
    localStorage.removeItem('userInfo')
    localStorage.removeItem('token')
    router.push('/login')
  })
}
</script>

<style>
@import "@/assets/css/manage.css";

</style>