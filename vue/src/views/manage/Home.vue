<template>
  <el-card class="home-top">
    <el-row>
      <el-col :span="8" >
        <p> <el-icon class="icon" ><Tickets /></el-icon><span class="title">本月订单量</span></p>
        <p class="num">{{data.numData[0]}}</p>
        <p class="before">上月订单量 {{data.numData[1]}}</p>
      </el-col>
      <el-col :span="8" >
        <p> <el-icon class="icon"><Money /></el-icon><span class="title">本月营业额</span></p>
        <p class="num">￥{{data.revenueData[0]}}</p>
        <p class="before">上月营业额 ￥{{data.revenueData[1]}}</p>
      </el-col>
      <el-col :span="8" >
        <p> <el-icon class="icon"><User /></el-icon><span class="title">本月新增用户</span></p>
        <p class="num">{{data.userData[0]}}</p>
        <p class="before">上月新增用户 {{data.userData[1]}}</p>
      </el-col>
    </el-row>
  </el-card>
  <el-row class="home-bottom" :gutter="20">
    <el-col :span="12">
      <el-card >
        <div id="bar" class="echarts-div"></div>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card >
        <div id="pie" class="echarts-div"></div>
      </el-card>


    </el-col>
  </el-row>


</template>
<script setup>
import * as echarts from 'echarts';
import { reactive,onMounted } from 'vue';
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
const data = reactive({
  name: null,
  noticeData:[],
  pageNum:1,
  pageSize:5,
  total:0,
  numData:[],
  revenueData:[],
  userData:[],
})
const load=()=> {
/*  request.get('/notice/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
    }
  }).then(res => {
    if (res.code == '0') {
      console.log(res.data)
      data.noticeData = res.data.list
      data.total = res.data.total
    } else {
      ElMessage.error(res.msg)
    }
  })*/
     /*获取订单*/
    request.get('/order/selectMonthOrderNum').then(res => {
      if (res.code == '0') {

        data.numData = res.data
      } else {
        ElMessage.error(res.msg)
      }
    })
  /*获取营业额*/
  request.get('/order/selectMonthOrderRevenue').then(res => {
    if (res.code == '0') {

      data.revenueData = res.data
    } else {
      ElMessage.error(res.msg)
    }
  })
  /*获取用户*/
  request.get('/user/selectMonthUserNum').then(res => {
    if (res.code == '0') {
      data.userData = res.data
    } else {
      ElMessage.error(res.msg)
    }
  })
}
load()

/*加载鲜花销量排行*/
const loadRank=(saleData)=>{
  var chartDom = document.getElementById('bar');
  var myChart = echarts.init(chartDom);
  var option;

  option = {
    title: {
      text: '热销商品排行榜'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    legend: {},
    xAxis: {
      type: 'value',
      boundaryGap: [0, 0.01],
      minInterval:1,
    },
    yAxis: {
      type: 'category',
      data: saleData.map(item=>item.name).reverse(),
      stackOrder: 'seriesDesc',
    },
    series: [
      {
        name: '',
        type: 'bar',
        stackOrder: 'seriesDesc',
        data: saleData.map(item=>item.saleNum).reverse()
      }
    ]
  };

  option && myChart.setOption(option);
}
onMounted(()=>{
  loadEchartsData()

})

const loadEchartsData=()=>{
  /*获取销量数据*/
  request.get('/goods/selectRank').then(res => {
    if (res.code == '0') {
      loadRank(res.data)
    } else {
      ElMessage.error(res.msg)
    }
  })

  /*获取分类销量数据*/
  request.get('/order/selectType').then(res => {
    if (res.code == '0') {
      console.log(res.data)
      loadType(res.data)
    } else {
      ElMessage.error(res.msg)
    }
  })


}

const loadType=(typeData)=>{

  var chartDom = document.getElementById('pie');
  var myChart = echarts.init(chartDom);
  var option;

  option = {
    title: {
      text: '销售分类占比',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: '',
        type: 'pie',
        radius: '50%',
        data: typeData.map(item=>({
          value:item.saleNum,
          name:item.typeName,
        })),
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };

  option && myChart.setOption(option);



}



</script>



