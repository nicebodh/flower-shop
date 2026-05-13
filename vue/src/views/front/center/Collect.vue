<template>
  <div class="user-collect">
    <el-row :gutter="15">
      <el-col :span="8" v-for="item in data.collectData">
        <el-card class="collect-article">
          <img :src="item.pic" class="img" @click="navToDetail(item.goodsId)">
          <el-tooltip :content="item.goodsName" placement="top-start">
            <p  class="name">{{item.goodsName}}</p>
          </el-tooltip>
          <div class="info">
            <span class="username" v-if="item.status=='上架'">{{item.shopName}}</span>
            <span v-else class="warn-down">鲜花已下架</span>
            <el-button type="danger" class="btn" @click="del(item.id)" size="small">删除</el-button></div>
        </el-card>
      </el-col>
    </el-row>
    <div class="pagination" v-if="data.total!=0">
      <el-pagination
          v-model:current-page="data.pageNum"
          :page-size="data.pageSize"
          layout="total, prev, pager, next"
          :total="data.total"
          @current-change="load">
      </el-pagination>
    </div>
  </div>
</template>
<script setup>
import {reactive} from "vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
import router from "@/router/index.js";

const data = reactive({
  collectData:[],
  pageNum:1,
  pageSize:3,
  total:0,
})


const load=()=>{
  request.get('/collect/selectPage',{
    params:{
      pageNum:data.pageNum,
      pageSize:data.pageSize,
    }
  }).then(res=>{
    if (res.code=='0'){
      console.log(res.data)
      data.collectData = res.data.list
      data.total=res.data.total
    }
    else {
      ElMessage.error(res.msg)
    }
  })
}
load()


const del = (id) => {
  ElMessageBox.confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
    request.delete('/collect/delete/' + id).then(res => {
      if (res.code === '0') {   // 表示操作成功
        ElMessage.success('移出收藏成功')
        load()
      } else {
        ElMessage.error(res.msg)  // 弹出错误的信息
      }
    })
  })
}

const navToDetail=(id)=>{
  router.push({path:'/front/goodsDetail',query:{id:id}})
}
</script>