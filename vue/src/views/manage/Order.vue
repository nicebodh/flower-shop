<template>
  <div>
    <div class="top-card">
      <el-card>
        <el-input class="search-input" v-model="data.search.orderNo" placeholder="请输入订单编号"  ></el-input>
        <el-select v-model="data.search.status" placeholder="请选择订单状态" class="search-input">
          <el-option label="已购买" value="已购买"></el-option>
          <el-option label="已发货" value="已发货"></el-option>
          <el-option label="交易成功" value="交易成功"></el-option>
          <el-option label="已评价" value="已评价"></el-option>
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
        <el-table-column prop="orderNo" label="订单编号" width="180" > </el-table-column>
        <el-table-column label="图片">
          <template v-slot="scope">
            <el-image :src="scope.row.goodsPic"  class="goods-pic" :preview-src-list="[scope.row.goodsPic]" :preview-teleported="true">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="goodsName" label="鲜花名"  width="180" show-overflow-tooltip></el-table-column>
        <el-table-column prop="goodsPrice" label="单价" > </el-table-column>
        <el-table-column prop="num" label="数量">  </el-table-column>
        <el-table-column prop="total" label="总价" ></el-table-column>

        <el-table-column prop="status" label="状态" >
          <template #default="scope">
            <el-tag type="primary" v-if="scope.row.status=='已购买'">{{scope.row.status}}</el-tag>
            <el-tag type="info" v-if="scope.row.status=='已发货'">{{scope.row.status}}</el-tag>
            <el-tag type="success" v-if="scope.row.status=='交易完成'">{{scope.row.status}}</el-tag>
            <el-tag type="success" v-if="scope.row.status=='已评价'">{{scope.row.status}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="username" label="用户" > </el-table-column>
        <el-table-column  label="操作" width="240" >
          <template #default="scope">
            <el-button type="primary" @click="handleSee(scope.row)">查看</el-button>
            <el-button type="warning" @click="handleSend(scope.row)"v-if="scope.row.status=='已购买'" >发货</el-button>
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

    <el-dialog v-model="data.formVisible" title="订单详情" width="40%" destroy-on-close>
      <el-form :model="data.form" ref="formRef" :rules="data.rules"  class="form">
        <el-form-item label="订单编号" label-width="80px"  >
          <el-input v-model="data.form.orderNo" readonly/>
        </el-form-item>
        <el-form-item label="鲜花名称" label-width="80px" >
          <el-input v-model="data.form.goodsName" readonly/>
        </el-form-item>
        <el-form-item label="单价" label-width="80px" >
          <el-input v-model="data.form.goodsPrice" readonly/>
        </el-form-item>
        <el-form-item label="数量" label-width="80px" >
          <el-input v-model="data.form.num" readonly/>
        </el-form-item>
        <el-form-item label="总价" label-width="80px" >
          <el-input v-model="data.form.total" readonly/>
        </el-form-item>
        <el-form-item label="订单状态" label-width="80px" >
          <el-input v-model="data.form.status" readonly/>
        </el-form-item>
        <el-form-item label="下单时间" label-width="80px" >
          <el-input v-model="data.form.time" readonly/>
        </el-form-item>
        <el-form-item label="收货人" label-width="80px" prop="phone">
          <el-input v-model="data.form.addressName" readonly/>
        </el-form-item>
        <el-form-item label="手机号" label-width="80px" prop="phone">
          <el-input v-model="data.form.addressPhone" readonly/>
        </el-form-item>
        <el-form-item label="收货地址" label-width="80px" prop="phone">
          <el-input v-model="data.form.address" readonly/>
        </el-form-item>
      </el-form>
    </el-dialog>  </div>
</template>
<script setup>
import { reactive,ref } from 'vue';
import {Search} from "@element-plus/icons-vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
const data = reactive({
  search: {},
  tableData:[],
  pageNum:1,
  pageSize:6,
  total:0,
  formVisible:false,
  form:{},

});
const formRef=ref()

const load=()=>{
  request.get('/order/selectPage',{
    params:{
      pageNum:data.pageNum,
      pageSize:data.pageSize,
      orderNo:data.search.orderNo,
      status:data.search.status,
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




const handleSee = (row) => {
  data.form=JSON.parse(JSON.stringify(row))
  data.formVisible=true
}


const handleSend = (row) => {
  request.put("/order/send", row).then(res => {
    if (res.code === '0') {
      ElMessage.success('发货成功');
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const del = (id) => {
  ElMessageBox.confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
    request.delete('/order/delete/' + id).then(res => {
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