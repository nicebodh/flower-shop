<template>
  <div>
    <div class="top-card">
      <el-card>
        <el-input class="search-input"  placeholder="请输入鲜花名称" v-model="data.search.name" :prefix-icon="Search" ></el-input>
        <el-select v-model="data.search.typeId" placeholder="请选择分类" class="search-input">
          <el-option :label="item.name" :value="item.id" v-for="item in data.typeData"></el-option>
        </el-select>

        <el-button type="primary"   @click="load">搜索</el-button>
        <el-button type="warning"   @click="reset">重置</el-button>
      </el-card>
    </div>

    <el-card class="center-card">
      <div class="add-btn"><el-button type="primary" @click="handleAdd" v-if="data.user.role=='shopkeeper'">新增</el-button> </div>
      <el-table :data="data.tableData"
                style="width: 100%"
                :header-cell-style="{'text-align':'center'}"
                :cell-style="{'text-align':'center'}">
        <el-table-column prop="name" label="名称" > </el-table-column>
        <el-table-column label="图片">
          <template v-slot="scope">
            <el-image :src="scope.row.pic"  class="goods-pic" :preview-src-list="[scope.row.pic]" :preview-teleported="true">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="typeName" label="分类" > </el-table-column>
        <el-table-column prop="price" label="价格" > </el-table-column>
        <el-table-column prop="num" label="库存" > </el-table-column>
        <el-table-column prop="status" label="状态" >
          <template #default="scope">
            <el-tag type="primary" v-if="scope.row.status=='上架'">上架</el-tag>
            <el-tag type="warning" v-else>下架</el-tag>
          </template>

        </el-table-column>
        <el-table-column  label="操作" width="240" >
          <template #default="scope">
            <el-button type="success" @click="updateStatus(scope.row)" v-if="scope.row.status=='上架'&&data.user.role=='shopkeeper'">下架</el-button>
            <el-button type="warning" @click="updateStatus(scope.row)"  v-if="scope.row.status=='下架'&&data.user.role=='shopkeeper'">上架</el-button>
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
    <el-dialog v-model="data.formVisible" title="鲜花信息" width="45%" destroy-on-close>
      <el-form :model="data.form" ref="formRef" :rules="data.rules"  class="form">
        <el-form-item label="名称" label-width="80px" prop="name" >
          <el-input v-model="data.form.name" />
        </el-form-item>
        <el-form-item label="图片" label-width="80px"   prop="pic">
          <el-upload
              :show-file-list="false"
              class="avatar-uploader"
              action="http://localhost:9090/files/upload"
              :on-success="handleAvatarSuccess">
            <img v-if="data.form.pic" :src="data.form.pic" class="avatar">
            <el-icon v-else class="avatar-uploader-icon"><Plus/></el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="价格" label-width="80px" prop="price" >
          <el-input v-model="data.form.price" />
        </el-form-item>

        <el-form-item label="库存数量" label-width="80px" prop="num" >
          <el-input v-model="data.form.num" />
        </el-form-item>
        <el-form-item label="分类" label-width="80px" prop="typeId" >
          <el-select v-model="data.form.typeId" placeholder="请选择分类">
            <el-option :label="item.name" :value="item.id" v-for="item in data.typeData"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="材料" label-width="80px" prop="material" >
          <el-input v-model="data.form.material" />
        </el-form-item>
        <el-form-item label="包装" label-width="80px" prop="wrapper" >
          <el-input v-model="data.form.wrapper" />
        </el-form-item>
        <el-form-item label="保质期" label-width="80px" prop="exp" >
          <el-input v-model="data.form.exp" />
        </el-form-item>
        <el-form-item label="内容" label-width="80px" >
          <div style="border: 1px solid #e7e7e7;width: 100%">
            <Toolbar
                style="border-bottom: 1px solid #ccc"
                :editor="editorRef"
                :mode="'default'"/>
            <Editor
                style="height: 300px; overflow-y: hidden"
                v-model="data.form.content"
                :defaultConfig="editorConfig"
                :mode="'default'"
                @onCreated="handleCreated"/>
          </div>
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
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import {Search} from "@element-plus/icons-vue";
import {reactive,onBeforeUnmount,ref,shallowRef } from 'vue'
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
const data =reactive({
  user: JSON.parse(localStorage.getItem('userInfo') || '{}'),
  search:{},
  tableData:[],
  formVisible:false,
  form:{},
  pageNum:1,
  pageSize:6,
  total:0,
  typeData:[],
  rules: {
    name: [
      { required: true, message: "请输入商品名称", trigger: "blur" },
    ],
    pic: [
      { required: true,  message: "请上传图片",trigger: "blur" },
    ],
    price: [
      { required: true, message: "请输入价格", trigger: "blur" },
    ],
    num: [
      { required: true,  message: "请输入库存数量",trigger: "blur" },
    ],
    typeId: [
      { required: true, message: "请选择分类", trigger: "change" },
    ],
    wrapper: [
      { required: true,  message: "请输入包装",trigger: "blur" },
    ],
    material: [
      { required: true,  message: "请输入材料",trigger: "blur" },
    ],
    exp: [
      { required: true,  message: "请输入保质期",trigger: "blur" },
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
  request.post("/goods/add",data.form).then(res=>{
    if (res.code==='0'){
      data.formVisible=false
      load()
      ElMessage.success("保存成功")
    }
  })
}

//查询
const load=()=>{
  request.get('/goods/selectPage',{
    params:{
      pageSize:data.pageSize,
      pageNum:data.pageNum,
      name:data.search.name,
      typeId:data.search.typeId,
    }
  }).then(res=>{
    if (res.code==='0'){
      data.tableData=res.data.list
      data.total=res.data.total
    }
  })
}
load()

const reset=()=>{
  data.search={}
  load()
}

const handleEdit=(row)=>{
  data.form=JSON.parse(JSON.stringify(row))
  data.formVisible=true
}

const update=()=>{
  request.put("/goods/update",data.form).then(res=>{
    if (res.code==='0'){
      data.formVisible=false
      load()
      ElMessage.success("修改成功")
    }
  })
}

const del = (id) => {
  ElMessageBox.confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
    request.delete('/goods/delete/' + id).then(res => {
      if (res.code === '0') {   // 表示操作成功
        ElMessage.success('删除成功')
        load()
      } else {
        ElMessage.error(res.msg)  // 弹出错误的信息
      }
    })
  }).catch(err => {})
}

const handleAvatarSuccess=(res)=>{
  data.form.pic=res.data
}

//分类查询
const loadType=()=>{
  request.get('/type/selectAll').then(res=>{
    if (res.code==='0'){
      console.log(res.data)
      data.typeData=res.data
    }
  })
}
loadType()

const editorRef=shallowRef() //编辑器实例，必须用shallowRef
const mode = 'default'
const editorConfig = {MENU_CONF: {}}
//图片上传配置
editorConfig.MENU_CONF['uploadImage'] ={
  server:'http://localhost:9090/files/wang/upload',//服务端图片上传接口
  fieldName:'file'
}
//服务端图片上传接口参数
//组件销毁时，也及时销毁编辑器，否则可能会造成内存泄漏
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})
const handleCreated = (editor) => {
  editorRef.value = editor
}

const updateStatus=(row)=>{
  request.put("/goods/updateStatus",row).then(res=>{
    if (res.code==='0'){
      load()
      ElMessage.success("状态更新成功")
    }
  })
}
</script>
