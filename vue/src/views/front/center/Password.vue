<template>
<!--  <div class="content-list">
    <p class="list-title">修改密码</p>
    <el-form :model="data.form" label-width="80px" class="form-input" :rules="data.rules" ref="formRef">

      <div class="edit-info" >
        <span class="label">原密码</span>
        <el-input  v-model="data.form.password" placeholder="原密码" class="input" prop="password"></el-input>
      </div>
      <div class="edit-info">
        <span class="label">新密码</span>
        <el-input  v-model="data.form.newPassword" placeholder="新密码" class="input"></el-input>
      </div>
      <div class="edit-info">
        <span class="label">确认密码</span>
        <el-input  v-model="data.form.confirmPassword" placeholder="确认密码" class="input"></el-input>
      </div>

      <div class="edit-info">
        <span class="label"></span>
        <el-button  type="primary" @click="updatePassword">保存</el-button>
      </div>
    </el-form>

  </div>-->
  <div class="div-password">
      <el-form :model="data.form" label-width="80px" class="form-input" :rules="data.rules" ref="formRef">
        <el-form-item label="原密码" prop="password">
          <el-input  v-model="data.form.password" placeholder="原密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input  v-model="data.form.newPassword" placeholder="新密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input  v-model="data.form.confirmPassword" placeholder="确认密码" show-password></el-input>
        </el-form-item>
        <div  class="password-btn">
          <el-button  type="primary" @click="updatePassword">保存</el-button>
        </div>
      </el-form>
  </div>
</template>

<script setup>
import request from "@/utils/request.js";
import {reactive,ref} from "vue";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
// 检查俩次密码是否一致



const data = reactive({
  form:{},
  user: JSON.parse(localStorage.getItem('userInfo') || '{}'),
  rules: {
    password: [
      { required: true,  message: "请输入密码",trigger: "blur" },
      { validator: (rule,value, callback) => {
          if (value !== data.user.password) {
            callback(new Error("原密码输入不正确"));
          } else {
            callback();
          }
        }
      }
    ],
    newPassword: [
      { required: true, message: "请输入新密码", trigger: "blur" },
      { validator: (rule,value, callback) => {
          if (value == data.user.password) {
            callback(new Error("新密码不能与原密码相同"));
          } else {
            callback();
          }
        }
      }
    ],
    confirmPassword: [
      { required: true,trigger: "blur",message: "请输入确认密码",},
      { validator: (rule,value, callback) => {
         if (value !== data.form.newPassword) {
            callback(new Error("输入的密码不一致"));
          } else {
            callback();
          }
        }
      }
    ],
  }
})
const formRef=ref()
const updatePassword = () => {
  formRef.value.validate(valid=>{
    if (valid){
      data.user.password=data.form.newPassword
      request.put('/account/updatePassword', data.user).then(res => {
        if (res.code === '0') {
          // 成功更新
          ElMessage.warning('修改密码成功,请重新登录')
         router.push('/login')
        } else {
          ElMessage.error(res.msg)
        }
      })}

  })
}
</script>
