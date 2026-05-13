<template>
  <div class="front-notice">
    <div class="info" v-for="item in data.noticeData">
      <p class="title">{{item.title}}</p>
      <p class="content">{{item.content}}</p>
      <p class="publisher">{{item.publisher}}<span class="time">发布于 {{item.time}}</span></p>
    </div>
    <div  class="pagination">
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
import { reactive } from 'vue';
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
const data = reactive({
  name: null,
  noticeData:[],
  pageNum:1,
  pageSize:4,
  total:0,
})
const load=()=>{
  request.get('/notice/selectPage',{
    params:{
      pageNum:data.pageNum,
      pageSize:data.pageSize,
    }
  }).then(res=>{
    if (res.code=='0'){
      console.log(res.data)
      data.noticeData = res.data.list
      data.total=res.data.total
    }
    else {
      ElMessage.error(res.msg)
    }
  })
}
load()

</script>
