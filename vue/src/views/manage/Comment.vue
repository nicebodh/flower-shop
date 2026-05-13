<template>
  <div>
    <div class="top-card">
      <el-card>
        <el-input class="search-input"  v-model="data.search.orderNo" placeholder="请输入订单编号" :prefix-icon="Search" ></el-input>
        <el-select v-model="data.search.shopName" placeholder="请选择店铺" class="search-input" v-if="data.user.role=='admin'">
          <el-option :label="item.shopName" :value="item.shopName" v-for="item in data.shopkeeperData"></el-option>
        </el-select>

        <el-button type="primary"   @click="load">搜索</el-button>
        <el-button type="warning"   @click="reset">重置</el-button>
      </el-card>
    </div>
    <el-card class="center-card">

      <el-table :data="data.tableData"
                style="width: 100%"
                :header-cell-style="{'text-align':'center'}"
                :cell-style="{'text-align':'center'}">
        <el-table-column prop="orderNo" label="订单编号" > </el-table-column>
        <el-table-column prop="goodsName" label="鲜花名称" show-overflow-tooltip width="240"> </el-table-column>
        <el-table-column prop="score" label="评分"  >  </el-table-column>
        <el-table-column prop="time" label="时间" ></el-table-column>
        <el-table-column prop="username" label="用户" > </el-table-column>
        <el-table-column prop="shopName" label="商家" v-if="data.user.role=='admin'"> </el-table-column>
        <el-table-column  label="操作" width="160" >
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)" v-if="!scope.row.reply&&data.user.role=='shopkeeper'">回复</el-button>
            <el-button type="primary" @click="handleSee(scope.row)" v-if="scope.row.reply&&data.user.role=='shopkeeper'">查看</el-button>
            <el-button type="primary" @click="handleSee(scope.row)" v-if="data.user.role=='admin'">查看</el-button>
            <el-button type="danger"  @click="del(scope.row.id)"  v-if="data.user.role=='admin'">删除</el-button>
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

    <el-dialog v-model="data.formVisible" title="回复" width="40%" destroy-on-close>
      <el-form :model="data.form"   class="form">
        <el-form-item label="评分" label-width="80px"  >
          <el-rate v-model="data.form.score" disabled/>
        </el-form-item>

        <el-form-item label="内容" label-width="80px" >
          <el-input v-model="data.form.content" type="textarea" :rows="3" readonly/>
        </el-form-item>
        <el-form-item label="图片" label-width="80px" >
          <el-image :src="item"  class="comment-img" :preview-src-list="[item]" :preview-teleported="true" v-for="item in data.form.pic.split(',')">
          </el-image>
        </el-form-item>
        <el-form-item label="回复" label-width="80px" >
          <el-input v-model="data.form.reply" type="textarea" :rows="2" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取消</el-button>
        <el-button type="primary" @click="saveReply">保存</el-button>
      </span>
      </template>
    </el-dialog>

    <el-dialog v-model="data.seeVisible" title="查看评价信息" width="40%" destroy-on-close>
      <el-form :model="data.form"   class="form">
        <el-form-item label="评分" label-width="80px"  >
          <el-rate v-model="data.form.score" disabled/>
        </el-form-item>

        <el-form-item label="内容" label-width="80px" >
          <el-input v-model="data.form.content" type="textarea" :rows="3" readonly/>
        </el-form-item>
        <el-form-item label="图片" label-width="80px" >
          <el-image :src="item"  class="comment-img" :preview-src-list="[item]" :preview-teleported="true" v-for="item in data.form.pic.split(',')">
          </el-image>
        </el-form-item>
        <el-form-item label="回复" label-width="80px" >
          <el-input v-model="data.form.reply" type="textarea" :rows="2" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.seeVisible = false">取消</el-button>
        <el-button type="danger" @click="deleteReply" v-if="data.user.role=='admin'&&data.form.reply">删除回复</el-button>
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
  user: JSON.parse(localStorage.getItem('userInfo') || '{}'),
  search: {},
  tableData:[],
  pageNum:1,
  pageSize:6,
  total:0,
  formVisible:false,
  seeVisible:false,
  shopkeeperData:[],
});
const formRef=ref()

const load=()=>{
  request.get('/comment/selectPage',{
    params:{
      pageNum:data.pageNum,
      pageSize:data.pageSize,
      orderNo:data.search.orderNo,
      shopName:data.search.shopName,
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
  data.search={}
  load()
}



const handleEdit = (row) => {
  data.form=JSON.parse(JSON.stringify(row))
  data.formVisible=true
}

const handleSee = (row) => {
  data.form=JSON.parse(JSON.stringify(row))
  data.seeVisible=true
}

const saveReply = () => {
  request.put("/comment/update", data.form).then(res => {
    if (res.code === '0') {
      data.formVisible = false
      ElMessage.success('回复成功');
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const deleteReply = () => {
  data.form.reply=""
  request.put("/comment/update", data.form).then(res => {
    if (res.code === '0') {
      data.seeVisible = false
      ElMessage.success('删除回复成功');
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const del = (id) => {
  ElMessageBox.confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
    request.delete('/comment/delete/' + id).then(res => {
      if (res.code === '0') {   // 表示操作成功
        ElMessage.success('删除成功')
        load()
      } else {
        ElMessage.error(res.msg)  // 弹出错误的信息
      }
    })
  }).catch(err => {})
}

//查询
const loadShopName=()=>{
  request.get('/shopkeeper/selectAll').then(res=>{
    if (res.code==='0'){
      console.log(res.data)
      data.shopkeeperData=res.data
    }
  })
}
loadShopName()

</script>