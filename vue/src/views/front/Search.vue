<template>
  <div class="goods-content">


    <div class="goods-list">
      <el-row :gutter="30">
        <el-col :span="6" class="item" v-for="item in data.goodsData">
          <el-card >
            <img :src="item.pic" class="img" @click="navToDetail(item.id)">
            <div>
              <el-tooltip :content="item.name" placement="top-start" >
                <p  class="name">{{item.name}}</p>
              </el-tooltip>
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
import {reactive,watch} from "vue";
import request from "@/utils/request.js";
import {useRoute} from "vue-router";
import router from "@/router/index.js";
const route =useRoute()

const data =reactive({
  name:route.query.name,
  pageNum: 1,
  pageSize: 6,
  total: 0,
  goodsData:[],
})

//查询
const loadGoods=()=>{
  request.get('/goods/selectPage',{
    params:{
      pageSize:data.pageSize,
      pageNum:data.pageNum,
      name:data.name,
      status:'上架',
    }
  }).then(res=>{
    if (res.code==='0'){
      data.goodsData=res.data.list
      data.total=res.data.total
    }
  })
}
loadGoods()

watch(() => route.query.name,(newValue, oldValue) => {
      data.name=route.query.name
      loadGoods()
});

const navToDetail=(id)=>{
  router.push({path:'/front/goodsDetail',query:{id:id}})
}
</script>