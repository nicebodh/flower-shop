<template>
  <div class="order-div">
    <el-input class="search-input" v-model="data.search.orderNo" placeholder="请输入订单编号"  ></el-input>
    <el-select v-model="data.search.status" placeholder="请选择订单状态" class="search-input">
      <el-option label="已购买" value="已购买"></el-option>
      <el-option label="已发货" value="已发货"></el-option>
      <el-option label="交易成功" value="交易成功"></el-option>
      <el-option label="已评价" value="已评价"></el-option>
    </el-select>
    <el-button type="primary" @click="load">搜 索</el-button>
    <el-button type="warning" @click="reset">重 置</el-button>
    <div class="user-order" v-for="item in data.orderData">
      <div class="user-order-top">
        <div>
        <span class="name">
          <span class="orderNum">订单号: {{item.orderNo}}</span>
          <el-icon class="icon"><Shop /></el-icon> {{item.shopName}}</span>  </div>
        <span class="state">{{ item.status }}</span>
      </div>
      <div class="user-order-center">
        <img :src="item.goodsPic" class="img">
        <div>
          <p class="name">{{item.goodsName}} <span class="num">x {{ item.num }}</span></p>
          <p class="text"> {{item.addressName}} {{item.addressPhone}} {{item.address}}</p>
          <div class="btn-and-price">
            <span class="price">￥<span class="number">{{item.total}}</span></span>
            <el-button type="warning" round size="small"  class="btn" v-if="item.status=='已发货'" @click="confirm(item)">确认收货</el-button>
           <el-button type="success" round size="small" v-if="item.status=='交易成功'" class="btn" @click="commentBtn(item)">评价</el-button>
            <el-button type="primary" round size="small" class="btn" @click="detailOrder(item)">订单详情</el-button>
          </div>
        </div>
      </div>
    </div>
    <div class="pagination">
      <el-pagination
          v-model:current-page="data.pageNum"
          :page-size="data.pageSize"
          layout="total, prev, pager, next"
          :total="data.total"
          @current-change="load">
      </el-pagination>
    </div>

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
        <el-form-item label="店铺" label-width="80px" >
          <el-input v-model="data.form.shopName" readonly/>
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
    </el-dialog>

    <el-dialog v-model="data.commentVisible" title="评价" width="40%" destroy-on-close>
      <el-form :model="data.commentForm" ref="formRef" :rules="data.rules"  class="form">
        <el-form-item label="评分" label-width="80px" prop="score" >
          <el-rate v-model="data.commentForm.score" />
        </el-form-item>
        <el-form-item label="内容" label-width="80px"  prop="content">
          <el-input v-model="data.commentForm.content" type="textarea" :rows="3" />
        </el-form-item>
        <el-form-item label="图片" label-width="80px" >
          <el-upload
              v-model:file-list="data.fileList"
              :limit="4"
              action="http://localhost:9090/files/upload"
              list-type="picture-card"
              :on-exceed="handleExceed"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :on-success="handleSuccess">
            <el-icon><Plus /></el-icon>
          </el-upload>
          <el-dialog v-model="picVisible" >
            <img w-full :src="dialogImageUrl" alt="Preview Image" style="width: 100%" />
          </el-dialog>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.commentVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>
<script setup>
import {reactive,ref} from "vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
const data = reactive({
  search: {},
  orderData:[],
  pageNum:1,
  pageSize:3,
  total:0,
  formVisible:false,
  commentVisible:false,
  form:{},
  commentForm:{},
  fileList:[],
  rules: {
    score: [
      { required: true, message: "请评分", trigger: "change" },
    ],
    content: [
      { required: true,  message: "请输入内容",trigger: "blur" },
    ],
  }
})

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
      data.orderData = res.data.list
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

const detailOrder = (item) => {
  data.formVisible=true
  data.form=JSON.parse(JSON.stringify(item))
}


const confirm = (item) => {
  ElMessageBox.confirm('是否确认收货？', '确认', {type: "warning"}).then(response => {
    request.put("/order/confirm", item).then(res => {
      if (res.code === '0') {
        ElMessage.success('已确认收货');
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  })
}

const commentBtn = (item) => {
  data.commentVisible=true
  data.commentForm.orderNo=item.orderNo
  data.commentForm.goodsId=item.goodsId
  data.commentForm.shopkeeperId=item.shopkeeperId
}

const score = ref(0)

//多图上传部分
//上传图片
const handleSuccess = (response, file) => {
  console.log(response.data)
  file.url = response.data
}
//移除图片
const handleRemove = (file) => {
  const index = data.fileList.findIndex(item => item.uid === file.uid)
  if (index !== -1) {
    data.fileList.splice(index, 1)
  }
}
//放大查看图片
const dialogImageUrl = ref('')
const picVisible = ref(false)
const handlePictureCardPreview = (file) => {
  dialogImageUrl.value = file.url
  picVisible.value = true
}
//限制上传图片数
const handleExceed = () => {
  ElMessage.warning('最多上传4张图片')
}
const saveImageUrls = () => {
  const urls = data.fileList.map(file => file.url)
  data.commentForm.pic=urls.toString()
}


const save=()=>{
  formRef.value.validate((valid)=>{
    if (valid) {
      saveImageUrls()
      request.post("/comment/add", data.commentForm).then(res => {
        if (res.code === '0') {
          data.commentVisible = false
          ElMessage.success('评论成功');
          load()
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}
</script>