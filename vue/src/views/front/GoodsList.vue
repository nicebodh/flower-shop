<template>
  <div class="goods-content">
    <el-card>
      <el-select v-model="data.typeId" placeholder="请选择分类" class="search-input" @change="loadSelected">
        <el-option :label="item.name" :value="item.id"   v-for="item in data.typeData"></el-option>
      </el-select>
      <el-button type="warning"  @click="reset">重 置</el-button>
    </el-card>

    <div class="goods-list">
      <el-row :gutter="30">
        <el-col :span="6" class="item" v-for="item in data.goodsData" @click="navToDetail(item.id)">
          <el-card >
            <img :src="item.pic" class="img">
            <div>
                <p  class="name">{{item.name}}</p>
              <p class="info"><span class="price-icon">￥</span>
                <span class="price">{{item.price}}</span>
                <span class="pay">已售 {{item.saleNum}}</span></p>
              <a class="shopName" >{{item.shopName}}</a>
            </div>

          </el-card>
        </el-col>
      </el-row>
    </div>
    <div class="pagination">
      <el-pagination
          background
          v-model:current-page="data.pageNum"
          :page-size="data.pageSize"
          layout="total, prev, pager, next"
          :total="data.total"
          @current-change="loadGoods">
      </el-pagination>
    </div>
  </div>

</template>
<script setup>
import {reactive} from "vue";
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {useRoute} from "vue-router";

const route =useRoute()
const data =reactive({
  typeId:'',
  pageNum: 1,
  pageSize:8,
  total: 0,
  typeData: [],
  goodsData:[],
})
// 清除当前页面URL的所有参数
const clear=()=> {
  const cleanUrl = window.location.protocol + "//" + window.location.host + window.location.pathname;
  window.history.replaceState(null, '', cleanUrl);
}
//查询
const loadGoods=()=>{
  if (route.query.typeId)
  {
    data.typeId=Number(route.query.typeId)
    clear()
  }
  request.get('/goods/selectPage',{
    params:{
      pageSize:data.pageSize,
      pageNum:data.pageNum,
      typeId:data.typeId,
      status:'上架',
    }
  }).then(res=>{
    if (res.code==='0'){
      data.goodsData=res.data.list
      data.total=res.data.total
      route.query.typeId=null
    }
  })
}
loadGoods()

const loadType=()=>{


  request.get('/type/selectAll').then(res=>{
    if (res.code==='0'){
      data.typeData=res.data
    }
  })
}
loadType()

const loadSelected=()=>{
   loadGoods()
}

const reset=()=>{
  data.typeId=null
  loadGoods()
}

const navToDetail=(id)=>{
  router.push({path:'/front/goodsDetail',query:{id:id}})
}
</script>