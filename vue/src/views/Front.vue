<template>
  <div>
    <!--头部-->
    <div class="header">
      <div class="header-title">
        <img src="@/assets/img/logo.png" class="logo">
        <a class="name" href="/front/index">鲜花商城</a>
      </div>

        <div class="header-nav">
          <el-menu  :default-active="router.currentRoute.value.path" mode="horizontal" router background-color="#fff">
						<el-menu-item index="/front/index" class="el-menu-nav" ><el-icon class="icon"><House /></el-icon>首页</el-menu-item>
            <el-menu-item index="/front/goodsList" class="el-menu-nav" ><el-icon class="icon"><InfoFilled /></el-icon>鲜花信息</el-menu-item>
						<el-menu-item index="/front/notice" class="el-menu-nav"><el-icon class="icon"><BellFilled /></el-icon>公告信息</el-menu-item>
          </el-menu>
        </div>
      <div class="header-right">
        <el-input class="search-input" v-model="data.name" placeholder="请输入鲜花名称"  ></el-input>
        <el-button type="primary" @click="navToGoodsList" class="search-btn">搜索</el-button>
        <div v-if="!data.user.username">
          <el-button @click="router.push('/login')">登录</el-button>
          <el-button @click="router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown>
            <div class="info">
              <img :src="data.user.avatar" class="avatar">
              <span class="name">{{data.user.name}}</span>
              <el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
            </div>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="router.push('/front/center')">个人中心</el-dropdown-item>
                <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>
    </div>
    <!--主体-->
    <div class="main-body">
      <router-view/>
    </div>
    <!--底部-->
    <div class="footer">
        <div class="footer-info">
          <el-row  :gutter="20" class="row">
            <el-col :span="8">
              <div >
              <p class="title">鲜花商城</p>
              <p class="text">copyright © All rights reserved </p>
              </div>
            </el-col>
            <el-col :span="8">
              <div>
<!--              <p class="text"><el-icon><Timer /></el-icon><span> 周一至周五：9:00-18:00</span></p>-->
              <p class="text"><el-icon><Phone /></el-icon> <span> 888888</span></p>
              <p class="text"><el-icon><Location /></el-icon><span> 首义学院</span></p>
              </div>
            </el-col>
            <el-col :span="8" >
              <div>
              <p class="add">有问题请及时联系我们</p>
              <p class="text">鲜花商城 竭诚为您服务</p>
              </div>
            </el-col>
          </el-row>
        </div>

      </div>
  </div>

</template>

<script setup>
import "@/assets/css/front.css";
import { reactive } from 'vue';
import router from "@/router/index.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {GoodsFilled, Shop} from "@element-plus/icons-vue";
const data = reactive({
  user: JSON.parse(localStorage.getItem('userInfo') || '{}'),
  name:''
});
const logout = () => {
  ElMessageBox.confirm('您确定退出登录吗？', '退出', {type: "warning"}).then(response => {
    localStorage.removeItem('userInfo')
    localStorage.removeItem('token')
    router.push('/login')
  })
}

const navToGoodsList=()=>{
  if (data.name==''){
    ElMessage.warning("请输入搜索内容")
    return
  }
  router.push({path:'/front/search',query:{name:data.name}})
  data.name=null
}


</script>

