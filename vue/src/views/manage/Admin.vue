<template>
  <div>

    <div class="top-card">
      <el-card>
        <el-input class="search-input"  v-model="data.name" placeholder="请输入名字" :prefix-icon="Search"></el-input>
        <el-button type="primary"   @click="load">搜索</el-button>
        <el-button type="warning"  @click="reset">重置</el-button>
      </el-card>
    </div>

    <el-card class="center-card">
      <div class="add-btn"><el-button type="primary" @click="handleAdd">新增</el-button> </div>
      <el-table :data="data.tableData"
                style="width: 100%"
                :header-cell-style="{'text-align':'center'}"
                :cell-style="{'text-align':'center'}">
        <el-table-column label="头像">
          <template v-slot="scope">
            <el-image :src="scope.row.avatar"  class="table-img" :preview-src-list="[scope.row.avatar]" :preview-teleported="true"></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="name" label="名字"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column label="操作" width="160">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger"  @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            v-model:current-page="data.pageNum"
            :page-size="data.pageSize"
            layout="total, prev, pager, next"
            :total="data.total"
            @current-change="load">
        </el-pagination>
      </div>

    </el-card>

    <el-dialog v-model="data.formVisible" title="管理员信息" width="40%" destroy-on-close>
      <el-form :model="data.form" ref="formRef" :rules="data.rules"  class="form">
        <el-form-item label="名字" label-width="80px" prop="name">
          <el-input v-model="data.form.name"/>
        </el-form-item>
        <el-form-item label="用户名" label-width="80px" prop="username">
          <el-input v-model="data.form.username"/>
        </el-form-item>
        <el-form-item label="密码" label-width="80px" prop="password">
          <el-input v-model="data.form.password"/>
        </el-form-item>
        <el-form-item label="邮箱" label-width="80px" prop="email">
          <el-input v-model="data.form.email"/>
        </el-form-item>
        <el-form-item label="电话" label-width="80px" prop="phone">
          <el-input v-model="data.form.phone"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          保存
        </el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>
<script setup>
import { reactive,ref } from 'vue';
import {Search} from "@element-plus/icons-vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
const data = reactive({
  name: null,
  tableData:[],
  pageNum:1,
  pageSize:6,
  total:6,
  formVisible:false,
  form:{},
  rules: {
    name: [
      { required: true, message: "请输入名字", trigger: "blur" },
    ],
    username: [
      { required: true,  message: "请输入用户名",trigger: "blur" },
    ],
    password: [
      { required: true,  message: "请输入密码",trigger: "blur" },
    ],
  }
});
const formRef=ref()
const load=()=>{
  request.get('/admin/selectPage',{
    params:{
      pageNum:data.pageNum,
      pageSize:data.pageSize,
      name:data.name
    }
  }).then(res=>{
    if (res.code=='0'){
      console.log(res.data)
      data.tableData = res.data.list
      data.total=res.data.total
    }
    else {
      ElMessage.error(res.msg)
    }
  })
}
load()

const reset=()=>{
  data.name=null
  load()
}

const handleAdd=()=>{
  data.formVisible=true
  data.form={}
}

const handleEdit = (row) => {
  data.formVisible=true
  data.form=JSON.parse(JSON.stringify(row))
}

const save=()=>{
  formRef.value.validate((valid)=>{
    if (valid) {
      if (!data.form.id)
      { add() }
      else{ update() }
    }
  })
}
const add = () => {
  request.post("/admin/add", data.form).then(res => {
    if (res.code === '0') {
      data.formVisible = false
      ElMessage.success('保存成功');
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const update = () => {
  request.put("/admin/update", data.form).then(res => {
    if (res.code === '0') {
      data.formVisible = false
      ElMessage.success('修改成功');
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const del = (id) => {
if (id==1){
  ElMessage.warning('系统管理员不可删除')
  return
}
  ElMessageBox.confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
    request.delete('/admin/delete/' + id).then(res => {
      if (res.code === '0') {   // 表示操作成功
        ElMessage.success('删除成功')
        load()
      } else {
        ElMessage.error(res.msg)  // 弹出错误的信息
      }
    })
  }).catch(err => {})
}
</script>