<template>
  <div class="content-list">
    <p class="list-title">编辑资料</p>
    <div>
      <div class="edit-info">
        <span class="label">头像</span>
        <el-upload
            action="http://localhost:9090/files/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess">
          <img :src="data.user.avatar" class="avatar">
        </el-upload>
      </div>
      <div class="edit-info">
        <span class="label">名字</span>
        <el-input  v-model="data.user.name" placeholder="名字" class="input"></el-input>
      </div>
      <div class="edit-info">
        <span class="label">电话</span>
        <el-input  v-model="data.user.phone" placeholder="电话" class="input"></el-input>
      </div>
      <div class="edit-info">
        <span class="label">邮箱</span>
        <el-input  v-model="data.user.email" placeholder="邮箱" class="input"></el-input>
      </div>
      <div class="edit-info">
        <span class="label">性别</span>
        <el-select v-model="data.user.sex" placeholder="性别"  class="input">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </div>
      <div class="edit-info">
        <span class="label"></span>
        <el-button  type="primary" @click="update"  >保 存</el-button>
      </div>
    </div>

  </div>
</template>
<script setup>
import request from "@/utils/request.js";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
const data = reactive({
  user: JSON.parse(localStorage.getItem('userInfo') || '{}'),
  form:{},
});
const handleAvatarSuccess=(res)=>{
 data.user.avatar=res.data
}
const update = () => {
  if (!data.user.name){
    ElMessage.warning("名字不能为空")
    return
  }
  if (!data.user.phone){
    ElMessage.warning("手机号不能为空")
    return
  }
  if (data.user.phone){
    let phoneRegex=/^1[3456789]\d{9}$/
    if (!phoneRegex.test(data.user.phone)) {
      ElMessage.warning("手机号格式不正确")
      return
  }
}
    if (!data.user.email){
    ElMessage.warning("邮箱不能为空")
    return
  }
    if (data.user.email){
      let re = /^\S+@\S+\.\S+$/;
      if (!re.test(data.user.email)) {
        ElMessage.warning("邮箱格式不正确")
        return
       }
    }
  if (!data.user.sex){
    ElMessage.warning("性别不能为空")
    return
  }
  request.put('/user/update', data.user).then(res => {
    if (res.code === '0') {
      // 更新浏览器缓存的用户信息
      localStorage.setItem('userInfo', JSON.stringify(data.user))
      alert('修改成功')
      window.location.reload()
    } else {
      ElMessage.error(res.msg)
    }
  })
}
</script>