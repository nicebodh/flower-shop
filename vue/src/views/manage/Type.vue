<template>
  <div>
    <div class="top-card">
      <el-card>
        <el-input class="search-input"  placeholder="请输入名称" v-model="data.name" :prefix-icon="Search" ></el-input>
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
        <el-table-column prop="name" label="名称" > </el-table-column>

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

<!--    模态窗口-->
    <el-dialog v-model="data.formVisible" title="分类信息" width="40%" destroy-on-close>
      <el-form :model="data.form" ref="formRef" :rules="data.rules"  class="form">
        <el-form-item label="名称" label-width="80px" prop="name" >
          <el-input v-model="data.form.name" />
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
import {Search} from "@element-plus/icons-vue";
import {reactive, ref} from 'vue';
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
const data =reactive({
  name:'',
  tableData:[],
  formVisible:false,
  form:{},
  pageNum:1,
  pageSize:6,
  total:0,
  rules: {
    title: [
      { required: true, message: "请输入标题", trigger: "blur" },
    ],
    content: [
      { required: true,  message: "请输入内容",trigger: "blur" },
    ],
  }
})

const handleAdd=()=>{
   data.form={}
   data.formVisible=true
}

const formRef=ref()

const save=()=>{
  formRef.value.validate((valid)=>{
    if (valid) {
      if (!data.form.id) { add() }
      else{ update() }
    }
  })
}

const add=()=>{
  request.post("/type/add",data.form).then(res=>{
    if (res.code==='0'){
      data.formVisible=false
      load()
      ElMessage.success("保存成功")
    }
  })
}

//查询
const load=()=>{
  request.get('/type/selectPage',{
    params:{
      pageSize:data.pageSize,
      pageNum:data.pageNum,
      name:data.name,
    }
  }).then(res=>{
    if (res.code==='0'){
       console.log(res.data)
      data.tableData=res.data.list
      data.total=res.data.total
    }
  })
}
load()

const reset=()=>{
  data.name=null
  load()
}

const handleEdit=(row)=>{
  data.form=JSON.parse(JSON.stringify(row))
  data.formVisible=true
}

const update=()=>{
  request.put("/type/update",data.form).then(res=>{
    if (res.code==='0'){
      data.formVisible=false
      load()
      ElMessage.success("修改成功")
    }
  })
}

const del = (id) => {
  ElMessageBox.confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
    request.delete('/type/delete/' + id).then(res => {
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
