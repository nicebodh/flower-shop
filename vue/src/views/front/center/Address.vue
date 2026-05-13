<template>
  <div class="user-table">
    <p class="top-address">地址信息</p>
    <div class="add-btn"><el-button type="primary" @click="handleAdd" icon="Plus" class="float-right"></el-button> </div>
    <el-table :data="data.tableData"
              style="width: 100%"
              :header-cell-style="{'text-align':'center'}"
              :cell-style="{'text-align':'center'}">
      <el-table-column prop="name" label="名字" > </el-table-column>
      <el-table-column prop="phone" label="手机号" > </el-table-column>
      <el-table-column prop="address" label="地址" >
      </el-table-column>
      <el-table-column  label="操作" width="160" >
        <template #default="scope">
          <el-button type="primary"  @click="handleEdit(scope.row)" icon="Edit"></el-button>
          <el-button type="danger"  @click="del(scope.row.id)" icon="Delete"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="data.formVisible" title="地址信息" width="40%" destroy-on-close>
      <el-form :model="data.form" ref="formRef" :rules="data.rules"  class="form">
        <el-form-item label="姓名" label-width="80px" prop="name" >
          <el-input v-model="data.form.name" />
        </el-form-item>
        <el-form-item label="手机号" label-width="80px"  prop="phone">
          <el-input v-model="data.form.phone" />
        </el-form-item>
        <el-form-item label="地址" label-width="80px"  prop="address">
          <el-input v-model="data.form.address"/>
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
import {reactive, ref} from "vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";

const data = reactive({
  tableData:[],
  formVisible:false,
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
  request.get('/address/selectAll').then(res=>{
    if (res.code=='0'){
      data.tableData = res.data
    }
    else {
      ElMessage.error(res.msg)
    }
  })
}

load()


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
  request.post("/address/add", data.form).then(res => {
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
  request.put("/address/update", data.form).then(res => {
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
    request.delete('/address/delete/' + id).then(res => {
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