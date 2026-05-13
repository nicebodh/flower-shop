<template >
  <div class="detail-content">

    <el-card>
      <!--商品详情信息-->
      <el-row  class="detail-info">
        <el-col :span="10" >
          <img :src="data.goodsData.pic" class="img">
        </el-col>
        <el-col :span="14">
          <div>
            <el-tooltip :content="data.goodsData.name" placement="top-start"  >
              <p  class="name">{{data.goodsData.name}}</p>
            </el-tooltip>
          </div>
          <p class="label"><span class="price-icon">￥</span><span class="price">{{data.goodsData.price}}</span>
            <span class="solid">已售 {{data.goodsData.saleNum}}</span></p>
          <p  class="label"><span class="label-text">配&nbsp &nbsp送</span>
            <span>承诺6小时内发货，晚发必赔 <span class="interval">|</span> 快递: 免运费</span>
          </p>
          <p class="label"><span class="label-text">材&nbsp &nbsp料</span>
            <span>{{ data.goodsData.material }}</span></p>
          <p class="label"><span class="label-text">包&nbsp &nbsp装</span>
            <span>{{ data.goodsData.wrapper }}</span></p>
          <p class="label"><span class="label-text">保质期</span>
            <span>{{data.goodsData.exp}}天</span></p>
          <p class="label"><span class="label-text">数&nbsp &nbsp量</span>
            <el-input-number v-model="num"  :min="1" :max="10" class="input-number"></el-input-number>
          </p>
          <div  class="btn">
            <el-button type="warning" @click="collect()">收藏</el-button>
            <el-button type="primary" @click="addCart(data.goodsData)">加入购物车</el-button></div>
        </el-col>
      </el-row>
    </el-card>
    <!--评论 详情-->
    <el-card class="detail-comment">
      <el-tabs  type="card" v-model="activeName">
        <el-tab-pane label="详细信息" name="first">
          <div v-html="data.goodsData.content"></div>

        </el-tab-pane>
        <el-tab-pane label="用户评价" name="second">
          <div class="comment-info" v-for="item in data.commentData">
            <img :src="item.avatar" class="avatar">
            <div>
              <p class="name">{{item.username}}</p>
              <p class="time">{{item.time}}</p>
              <p>
                <el-rate v-model="item.score" disabled size="small" />
                <span class="text">{{item.content}}</span>
              </p>

              <img :src="pic" class="comment-img" v-for="pic in item.pic.split(',')" v-if="item.pic">
              <p class="reply" v-if="item.reply">商家回复：{{item.reply}}</p>
            </div>
          </div>

          <div class="pagination" v-if="data.commentData.length!=0">
            <el-pagination
                v-model:current-page="data.pageNum"
                :page-size="data.pageSize"
                layout="total, prev, pager, next"
                :total="data.total"
                @current-change="loadComment">
            </el-pagination>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-card>


  </div>
</template>


<script setup>
import {ref,reactive} from "vue";
import {useRoute} from "vue-router";
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
const route =useRoute()
const num = ref(1)
const data =reactive({
  id:route.query.id,
  goodsData:{},
  pageNum:1,
  pageSize:2,
  total:0,
  commentData:[],
})

const loadGoods=()=>{
  request.get('/goods/selectById/' + data.id).then(res => {
    if (res.code === '0') {   // 表示操作成功
        data.goodsData=res.data
    } else {
      ElMessage.error(res.msg)  // 弹出错误的信息
    }
  })
}
loadGoods()

const activeName = ref('first')


const collect = () => {
  let collect={
   goodsId:data.id
           }
      request.post("/collect/add", collect).then(res => {
        if (res.code === '0') {
          data.formVisible = false
          ElMessage.success('收藏成功');
        } else {
          ElMessage.warning(res.msg)
        }
      })
}

const addCart = (goodsData) => {
  if (goodsData.num==0){
    ElMessage.warning('库存不足');
    return
  }
  let collect={
    num:num.value,
    goodsId:goodsData.id,
    shopkeeperId:goodsData.shopkeeperId,
  }
  request.post("/cart/add", collect).then(res => {
    if (res.code === '0') {
      data.formVisible = false
      ElMessage.success('加入购物车成功');
    } else {
      ElMessage.warning(res.msg)
    }
  })
}


const loadComment=()=>{
  request.get('/comment/selectPage',{
    params:{
      pageNum:data.pageNum,
      pageSize:data.pageSize,
      goodsId:data.id,
    }
  }).then(res=>{
    if (res.code=='0'){
      console.log(res.data)
      data.commentData = res.data.list
      data.total=res.data.total
    }
    else {
      ElMessage.error(res.msg)
    }
  })
}

loadComment()
</script>