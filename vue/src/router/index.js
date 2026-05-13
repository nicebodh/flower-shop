import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path:'/login',meta:{name:'登录'},component:()=>import('../views/Login.vue') },
    { path:'/register',meta:{name:'注册'},component:()=>import('../views/Register.vue') },
    {
      path: '/manage', name: 'manage',component: ()=>import('../views/Manage.vue'),
      redirect: '/manage/home',  // 重定向到首页
      children:[
        { path:'home',meta:{name:'首页'},component:()=>import('../views/manage/Home.vue')},
        { path:'notice',meta:{name:'公告管理'},component:()=>import('../views/manage/Notice.vue')},
        { path:'banner',meta:{name:'轮播图管理'},component:()=>import('../views/manage/Banner.vue')},
        { path:'type',meta:{name:'分类管理'},component:()=>import('../views/manage/Type.vue')},
        { path:'goods',meta:{name:'鲜花管理'},component:()=>import('../views/manage/Goods.vue')},
        { path:'order',meta:{name:'订单管理'},component:()=>import('../views/manage/Order.vue')},
        { path:'comment',meta:{name:'评价管理'},component:()=>import('../views/manage/Comment.vue')},
        { path:'user',meta:{name:'用户信息'},component:()=>import('../views/manage/User.vue')},
        { path:'shopkeeper',meta:{name:'商家信息'},component:()=>import('../views/manage/Shopkeeper.vue')},
        { path:'admin',meta:{name:'管理员信息'},component:()=>import('../views/manage/Admin.vue')},
        { path:'userInfo',meta:{name:'个人信息'},component:()=>import('../views/manage/UserInfo.vue')},
        { path:'password',meta:{name:'修改密码'},component:()=>import('../views/manage/Password.vue')},
      ]
    },
    {
      path: '/front',
      name: 'Front',
      component: () => import('../views/Front.vue'),
      children: [
        { path: 'index', name: 'Index', meta: { name: '系统首页'}, component:() => import('../views/front/Index.vue') },
          { path: 'goodsList', name: 'GoodsList', meta: { name: '鲜花信息'}, component:() => import('../views/front/GoodsList.vue')  },
          { path: 'search', name: 'Search', meta: { name: '鲜花信息'}, component:() => import('../views/front/Search.vue')  },
        { path: 'goodsDetail', name: 'GoodsDetail', meta: { name: '鲜花详情'}, component:() => import('../views/front/GoodsDetail.vue')  },

        { path: 'notice', name: 'Notice', meta: { name: '修改密码'}, component:() => import('../views/front/Notice.vue')  },
        { path: 'center',name: 'Center',component:() => import('../views/front/Center.vue'),redirect: '/front/center/userInfo',
        children: [
          { path: 'cart', name: 'Cart', meta: { name: '购物车' }, component:() => import('../views/front/center/Cart.vue')  },
          { path: 'address', name: 'Address', meta: { name: '购物车' }, component:() => import('../views/front/center/Address.vue')  },

          { path: 'collect', name: 'Collect', meta: { name: '我的收藏' }, component:() => import('../views/front/center/Collect.vue')  },
          { path: 'order', name: 'Order', meta: { name: '我的订单' }, component:() => import('../views/front/center/Order.vue')  },
          { path: 'password', name: 'Password', meta: { name: '修改密码' }, component:() => import('../views/front/center/Password.vue')  },
          { path: 'userInfo', name: 'UserInfo', meta: { name: '个人信息' }, component:() => import('../views/front/center/UserInfo.vue')  },
               ]
        },
              ]
     },
  ],
})

router.beforeEach((to ,from, next) => {
  document.title = '鲜花商城';
  if (to.path === '/') {
    return next("/front/index");
  }
  const user =JSON.parse(localStorage.getItem('userInfo'))
  if (user){
    if (to.path.includes("manage")&&user.role==='user'){
      next('/front/index')
    }
    if (to.path.includes("front")&&user.role!=='user'){
      next('/manage/home')
    }
  }
  else {
    if (to.path !== '/login' && to.path !== '/register' && to.path !== '/front/index') {
      next("/login");
    }
  }
  next();
})


export default router
