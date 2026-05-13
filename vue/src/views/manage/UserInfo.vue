<template>
  <div>
    <el-card class="userinfo-card">
      <el-form :model="data.user" label-width="100px" ref="formRef" :rules="data.rules">
        <div >
          <el-upload
              action="http://localhost:9090/files/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess">
            <img v-if="data.user.avatar" :src="data.user.avatar" class="avatar" />
          </el-upload>
        </div>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="data.user.username" placeholder="用户名" disabled></el-input>
        </el-form-item>
        <el-form-item label="名字" prop="name" >
          <el-input v-model="data.user.name" placeholder="名字"></el-input>
        </el-form-item>
        <el-form-item label="店铺名" prop="shopName" v-if="data.user.role=='shopkeeper'">
          <el-input v-model="data.user.shopName" placeholder="店铺名"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="data.user.phone" placeholder="电话"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="data.user.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <div class="userInfo-btn">
          <el-button type="primary"  @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import request from "@/utils/request.js";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
const data = reactive({
  user: JSON.parse(localStorage.getItem('userInfo') || '{}'),
  rules: {
    name: [
      { required: true, message: "请输入名字", trigger: "blur" },
    ],
    shopName: [
      { required: true, message: "请输入店铺名", trigger: "blur" },
    ],
    phone: [
      { required: true,  message: "请输入电话",trigger: "blur" },
      { pattern:/^1[3456789]\d{9}$/,message: "请输入正确的手机号格式",}
    ],
    email: [
      { required: true,  message: "请输入邮箱",trigger: "blur" },
      { pattern:/^\S+@\S+\.\S+$/,message: "请输入正确的邮箱格式",}
    ],
  }
});
const handleAvatarSuccess=(res)=>{
  data.user.avatar=res.data
}
const formRef=ref()
const update = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      let url = ''
        if (data.user.role==='admin'){
    url='/admin/update'
  }
        else {
          url='/shopkeeper/update'
        }
      request.put(url, data.user).then(res => {
        if (res.code === '0') {
          // 更新浏览器缓存的用户信息
          localStorage.setItem('userInfo', JSON.stringify(data.user))
          window.location.reload()
          alert('修改成功')
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}
</script>

