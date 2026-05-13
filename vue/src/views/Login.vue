<template>
<div class="login">
  <div class="login-form">
    <p class="title">鲜花商城</p>
    <el-form :model="data.form" ref="formRef" :rules="data.rules" >
      <el-form-item prop="username" >
        <el-input class="input"  placeholder="用户名" v-model="data.form.username"  prefix-icon="User" ></el-input>
      </el-form-item>
      <el-form-item prop="password" >
        <el-input class="input"  placeholder="密码" v-model="data.form.password" show-password prefix-icon="Lock" ></el-input>
      </el-form-item>
      <el-form-item prop="role" >
        <el-select v-model="data.form.role" placeholder="请选择角色">
          <el-option label="用户" value="user"></el-option>
          <el-option label="商家" value="shopkeeper"></el-option>
          <el-option label="管理员" value="admin"></el-option>
        </el-select>
      </el-form-item>
      <el-button class="btn" type="primary" @click="login">登录</el-button>
      <div class="link">
        没有账号？<a class="link-a" href="/register">注册</a>
      </div>
    </el-form>
  </div>
</div>
</template>

<script setup>
import { reactive,ref } from 'vue';
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
const data = reactive({
  form:{},
  rules: {
    username: [
      { required: true, message: "请输入用户名", trigger: "blur" }
    ],
    password: [
      { required: true,  message: "请输入密码",trigger: "blur" }
    ],
    role: [
      { required: true,  message: "请选择角色",trigger: "change" }
    ],
  }
});
const formRef=ref()
const login = () => {
  formRef.value.validate(valid=>{
    if (valid){
      request.post("/account/login", data.form).then(res => {
        if (res.code === '0') {
          let user = res.data.user;
          let token = res.data.token;
          localStorage.setItem("token", token);
          localStorage.setItem("userInfo", JSON.stringify(user))
          if (user.role == 'user') {
            location.href = '/front/index'
          } else {
            location.href = '/manage/home'
          }
          ElMessage.success('登录成功')
        } else {
          ElMessage.warning(res.msg)
        }
      })
    }
  })
}
</script>

<style>
@import "@/assets/css/manage.css";
</style>