<template>
  <div class="main-content">
    <div class="carousel">
      <el-carousel :interval="5000" arrow="always" height="350px" >

        <el-carousel-item v-for="item in data.imgData" :key="item">
          <a @click="detailGoods(item.goodsId)">
          <img :src="item.pic" class="index-img">
          </a>
        </el-carousel-item>

      </el-carousel>
    </div>
    <!--    首页图标-->
    <div class="index-service">
      <el-row >
        <el-col :span="6">
          <div class="service-content" >
            <el-icon class="left-icon"><Shop /></el-icon>
            <div>
              <p class="headline">优质平台</p>
              <p class="subtitle">质量靠谱 用心服务每一位顾客</p>
            </div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="service-content" >
            <el-icon class="left-icon"><Camera /></el-icon>
            <div>
              <p class="headline">实拍展示</p>
              <p class="subtitle">精选优质鲜花 包装精美</p>
            </div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="service-content" >
            <el-icon class="left-icon"><Timer /></el-icon>
            <div >
              <p class="headline">5小时内配送</p>
              <p class="subtitle">全国连锁花店 专人送达</p>
            </div>
          </div>

        </el-col>
        <el-col :span="6">
          <div class="service-content" >
            <el-icon class="left-icon"><Service /></el-icon>
            <div >
              <p class="headline">及时服务</p>
              <p class="subtitle">贴心服务 快速及时的售后保障</p>
            </div>
          </div>
        </el-col>

      </el-row>
    </div>

     <!--    首页分类-->
    <div class="index-type">
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="long-div bless-img" >
            <p class="title">祝贺鲜花</p>
            <p class="subtitle">庆祝时刻 鲜花相伴</p>
            <el-button round size="small" type="primary" @click="searchByTypeId(1)">查看 </el-button>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="short-div love-img">
            <p class="title">恋人情侣</p>
            <p class="subtitle">有幸遇到 不负相识</p>
            <el-button round size="small" type="primary"@click="searchByTypeId(2)">查看 </el-button>
          </div>
          <div class="short-div birthday-img">
            <p class="title">生日祝福</p>
            <p class="subtitle">新的一岁 鲜花相伴</p>
            <el-button round size="small" type="primary" @click="searchByTypeId(3)">查看 </el-button>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="long-div open-img" >
            <p class="title">开业花篮</p>
            <p class="subtitle">公司开业 鲜花来庆祝</p>
            <el-button round size="small" type="primary" @click="searchByTypeId(7)">查看 </el-button>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="short-div meeting-img">
            <p class="title">会议桌花</p>
            <p class="subtitle">商务会议 学术交流</p>
            <el-button round size="small" type="primary" @click="searchByTypeId(6)">查看 </el-button>
          </div>
          <div class="short-div patient-img">
            <p class="title">探病慰问</p>
            <p class="subtitle">看望病人 最佳选择</p>
            <el-button round size="small" type="primary" @click="searchByTypeId(5)">查看 </el-button>
          </div>
        </el-col>


      </el-row>
    </div>

    <!--    首页推荐-->
    <div class="index-recommend">
      <p class="title">热销推荐</p>
      <div class="goods-recommend">
        <el-row :gutter="40">
          <el-col :span="6" v-for="item in data.goodsData">
            <div class="info" >
                <img :src="item.pic" class="img">
                <p @click="navToDetail(item.id)"> <a class="name">{{item.name}}</a></p>
                <el-tag round type="primary">{{item.typeName}}</el-tag>
                <p  class="price">￥ {{item.price}}</p>
                <p  class="sale">已售 {{item.saleNum}}</p>
            </div>
          </el-col>

        </el-row>

      </div>
    </div>
  </div>


</template>
<script setup>
import { reactive } from 'vue';
import request from "@/utils/request.js";
import router from "@/router/index.js";
const data=reactive({
  imgData:[],
  goodsData:[],
})

const loadBanner=()=>{
  request.get('/banner/selectAll').then(res=>{
    if (res.code==='0'){
      data.imgData=res.data
    }
  })
}
loadBanner()

const detailGoods=(godsId)=>{
  router.push({path:'/front/goodsDetail',query:{id:godsId}})
}

const searchByTypeId=(typeId)=>{
  router.push({path:'/front/goodsList',query:{typeId:typeId}})
}

const loadHotGoods=()=>{
  request.get('/goods/selectHot').then(res=>{
    if (res.code==='0'){
      console.log(res.data)
      data.goodsData=res.data.slice(0, 4)
    }
  })
}
loadHotGoods()

const navToDetail=(id)=>{
  router.push({path:'/front/goodsDetail',query:{id:id}})
}
</script>