<template>
<div class="login">
  <div class="login-form">
    <p class="title">用户注册</p>
    <el-form :model="data.form" ref="formRef" :rules="data.rules" >
      <el-form-item prop="name" >
      <el-input class="input"  placeholder="名字" v-model="data.form.name"  prefix-icon="User" ></el-input>
      </el-form-item>
      <el-form-item prop="username" >
        <el-input class="input"  placeholder="用户名" v-model="data.form.username"  prefix-icon="User" ></el-input>
      </el-form-item>
      <el-form-item prop="password" >
        <el-input class="input"  placeholder="密码" v-model="data.form.password" show-password prefix-icon="Lock" ></el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword" >
      <el-input class="input"  placeholder="确认密码" v-model="data.form.confirmPassword" show-password prefix-icon="Lock" ></el-input>
      </el-form-item>
      <el-form-item prop="role" >
        <el-select v-model="data.form.role" placeholder="请选择角色">
          <el-option label="用户" value="user"></el-option>
          <el-option label="商家" value="shopkeeper"></el-option>
        </el-select>
      </el-form-item>
      <el-button class="btn" type="primary" @click="register">注册</el-button>
      <div class="link">
        已有账号？<a class="link-a" href="/login">登录</a>
      </div>
    </el-form>
  </div>
</div>
</template>

<script setup>
import { reactive,ref } from 'vue';
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
const formRef=ref()
const validatePass= (rule, value, callback) => {
// value表示用户输入的确认密码
  if (value !== data.form.password){
    callback(new Error("密码不一致"))
  }else {
    callback()
  }
}
const data = reactive({
  form:{},
  rules: {
    name: [
      { required: true, message: "请输入名字", trigger: "blur" },
    ],
    username: [
      { required: true, message: "请输入用户名", trigger: "blur" },
    ],
    password: [
      { required: true,  message: "请输入密码",trigger: "blur" },
    ],
    confirmPassword: [
      { required: true,  message: "请输入确认密码",trigger: "blur" },
      { validator:validatePass,trigger: "blur" },
    ],
  }
});

const register=()=>{
  formRef.value.validate(valid=>{
    if (valid){
  request.post("/account/register", data.form).then(res => {
    if(res.code === '0') {
      ElMessage.success('注册成功')
      router.push('/login')
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