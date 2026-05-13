<template>
  <div class="user-table">
    <div class="top-cart">
      <span>商品({{data.tableData.length}})</span>
      <el-select  placeholder="请选择收货地址" v-model="data.addressId" class="select-address">
        <el-option :label="item.name + ' ' +item.phone+' ' +item.address " :value="item.id" v-for="item in data.addressData"></el-option>
      </el-select>
    </div>

    <el-table :data="data.tableData"
              style="width: 100%"
              @selection-change="selectionChange"
              :header-cell-style="{'text-align':'center'}"
              :cell-style="{'text-align':'center'}">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="goodsName" label="鲜花名称" width="210" show-overflow-tooltip> </el-table-column>
      <el-table-column prop="goodsPrice" label="单价" > </el-table-column>
      <el-table-column prop="num" label="数量" width="150">
        <template #default="scope">
          <el-input-number v-model="scope.row.num"  :min="1" :max="scope.row.goodsNum" style="width: 110px" @change="updateNum(scope.row)"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column prop="shopName" label="店铺名称" ></el-table-column>
      <el-table-column  label="操作" width="160" >
        <template #default="scope">
          <el-button type="danger"  @click="del(scope.row.id)" icon="Delete"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="cart-total">
      <span class="text">合计 ￥<span class="num">{{data.total}}</span></span>
      <el-button type="primary" round size="small" @click="save">结算</el-button>
    </div>
  </div>

</template>
<script setup>
import {reactive} from "vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";

const data = reactive({
  tableData:[],
  total:'0.00',
  addressData:[],
  addressId:'',
  checkedData:[],
});


const load=()=>{
  request.get('/cart/selectAll').then(res=>{
    if (res.code=='0'){
      data.tableData = res.data
    }
    else {
      ElMessage.error(res.msg)
    }
  })
}

load()

const updateNum=(cart)=>{
  request.put('/cart/update',cart).then(res=>{
    if (res.code=='0'){
      ElMessage.success("更新成功")
      load()
    }
    else {
      ElMessage.error(res.msg)
    }
  })
}
const selectionChange=(val)=>{
  data.checkedData=val
  data.total=0
  val.forEach((item)=>{
    data.total= item.goodsPrice*item.num+data.total
  })
  data.total=data.total.toFixed(2)
}

const loadAddress=()=>{
  request.get('/address/selectAll').then(res=>{
    if (res.code=='0'){
      data.addressData = res.data
    }
    else {
      ElMessage.error(res.msg)
    }
  })
}

const save = () => {
  if (!data.addressId){
    ElMessage.warning("请选择收货地址")
    return
  }
if ( data.checkedData.length==0){
  ElMessage.warning("请选择鲜花")
  return
}
  let order={
  cartList:data.checkedData,
  addressId:data.addressId,
  }
  request.post("/order/add", order).then(res => {
    if (res.code === '0') {
      ElMessage.success('购买成功');
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}
loadAddress()


const del = (id) => {
  ElMessageBox.confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
    request.delete('/cart/delete/' + id).then(res => {
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