<template>
<div>
  <div class="top-card">
<el-card>
  <el-input class="search-input"  v-model="data.title" placeholder="请输入标题" :prefix-icon="Search" ></el-input>
  <el-button type="primary"   @click="load">搜索</el-button>
  <el-button type="warning"   @click="reset">重置</el-button>
</el-card>
  </div>
  <el-card class="center-card">
    <div class="add-btn"><el-button type="primary" @click="handleAdd">新增</el-button> </div>
    <el-table :data="data.tableData"
              style="width: 100%"
              :header-cell-style="{'text-align':'center'}"
              :cell-style="{'text-align':'center'}">
      <el-table-column prop="title" label="标题" > </el-table-column>
      <el-table-column prop="content" label="内容" show-overflow-tooltip >  </el-table-column>
      <el-table-column prop="time" label="时间" ></el-table-column>
      <el-table-column prop="publisher" label="发布人" > </el-table-column>
      <el-table-column  label="操作" width="160" >
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

  <el-dialog v-model="data.formVisible" title="公告信息" width="40%" destroy-on-close>
    <el-form :model="data.form" ref="formRef" :rules="data.rules"  class="form">
      <el-form-item label="标题" label-width="80px" prop="title" >
        <el-input v-model="data.form.title" />
      </el-form-item>
      <el-form-item label="内容" label-width="80px"  prop="content">
        <el-input v-model="data.form.content" type="textarea" :rows="3" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
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
  title: null,
  tableData:[],
  pageNum:1,
  pageSize:6,
  total:0,
  formVisible:false,
  form:{},
  rules: {
    title: [
      { required: true, message: "请输入标题", trigger: "blur" },
    ],
    content: [
      { required: true,  message: "请输入内容",trigger: "blur" },
    ],
  }
});
const formRef=ref()

const load=()=>{
  request.get('/notice/selectPage',{
    params:{
      pageNum:data.pageNum,
      pageSize:data.pageSize,
      title:data.title,
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
  data.title=null
  load()
}

const handleAdd=()=>{
  data.form={}
  data.formVisible=true
}

const save=()=>{
  formRef.value.validate((valid)=>{
    if (valid) {
      if (!data.form.id) { add() }
      else{ update() }
    }
  })
}

const handleEdit = (row) => {
  data.form=JSON.parse(JSON.stringify(row))
  data.formVisible=true
}

const add = () => {
  request.post("/notice/add", data.form).then(res => {
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
  request.put("/notice/update", data.form).then(res => {
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
  ElMessageBox.confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
    request.delete('/notice/delete/' + id).then(res => {
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