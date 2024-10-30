import Vue from 'vue'
import Router from 'vue-router'

//这里的千万不能注释掉
Vue.use(Router)

const router =  new Router({
  //设置这个mode为history能去掉访问路径中的#
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: () => import('@/components/Login'),
      meta: {title: '登录'},
      // hidden: false
    },
    {
      path: '/reg',
      name: 'Reg',
      component: () => import('@/components/Reg'),
      meta: {title: '注册'},
      // hidden: false
    },
    //如果map放在home外面,那么是不会有导航栏的
    // {
    //   path: '/map',
    //   name: 'map',
    //   component: () => import('@/components/Map'),
    //   meta: {title: '地图'},
    // },
    {
      path: '/room',
      name: 'Room',
      component: () => import('@/components/Room'),
      children: [
        //一、求助模块
        {
          path: '/home',
          name: 'Home',
          component: () => import('@/components/menu/Home'),
          meta: {title: '主页'},
        },
        {
          //如果把path的home改动为help,那么就是一个干净的画面,不会有导航栏那些东西
          path: '/home/help/call_help_a',
          name: 'call_help_a',
          component: () => import('@/components/home/help/call_help_a'),
          meta: {title: '求助a'},
        },
        {
          path: '/home/help/call_help_b',
          name: 'call_help_b',
          component: () => import('@/components/home/help/call_help_b'),
          meta: {title: '求助b'},
        },
        {
          path: '/home/help/my_call_help',
          name: 'my_call_help',
          component: () => import('@/components/home/help/my_call_help'),
          meta: {title: '我的求助'},
        },
        {
          path: '/home/help/call_help_edit',
          name: 'call_help_edit',
          component: () => import('@/components/home/help/call_help_edit'),
          meta: {title: '求助编辑'},
        },
        //二、群众模块
        {
          path: '/home/mass/org_supervise',
          name: 'org_supervise',
          component: () => import('@/components/home/mass/org_supervise'),
          meta: {title: '组织监督'},
        },
        //三、志愿模块
        {
          //！！！！！！！！！！！这个是没有默认按钮的，直接拦截跳转！！！！！！！！！
          path: '/home/volunteer/add_volunteer_a',
          name: 'add_volunteer_a',
          component: () => import('@/components/home/volunteer/add_volunteer_a'),
        },
        {
          //！！！！！！！！！！！这个是没有默认按钮的，直接拦截跳转！！！！！！！！！
          path: '/home/volunteer/add_volunteer_b',
          name: 'add_volunteer_b',
          component: () => import('@/components/home/volunteer/add_volunteer_b'),
        },
        {
          path: '/home/volunteer/volunteer_resource',
          name: 'volunteer_resource',
          component: () => import('@/components/home/volunteer/volunteer_resource'),
          meta: {title: '公布资源'},
        },
        {
          path: '/home/volunteer/volunteer_info',
          name: 'volunteer_info',
          component: () => import('@/components/home/volunteer/volunteer_info'),
          meta: {title: '志愿资料'},
        },
        {
          path: '/home/volunteer/volunteer_select',
          name: 'volunteer_select',
          component: () => import('@/components/home/volunteer/volunteer_select'),
          meta: {title: '选取任务'},
        },
        {
          path: '/home/volunteer/volunteer_task',
          name: 'volunteer_task',
          component: () => import('@/components/home/volunteer/volunteer_task'),
          meta: {title: '我的任务'},
        },
        {
          path: '/home/volunteer/volunteer_findPosition',
          name: 'volunteer_findPosition',
          component: () => import('@/components/home/volunteer/volunteer_findPosition'),
          meta: {title: '展示位置'},
        },
        //四、组织模块
        {
          //！！！！！！！！！！！这个是没有默认按钮的，直接拦截跳转！！！！！！！！！
          path: '/home/leader/add_org_a',
          name: 'add_org_a',
          component: () => import('@/components/home/leader/add_org_a'),
          meta: {title: '创建组织a'},
        },
        {
          //！！！！！！！！！！！这个是没有默认按钮的，直接拦截跳转！！！！！！！！！
          path: '/home/leader/add_org_b',
          name: 'add_org_b',
          component: () => import('@/components/home/leader/add_org_b'),
          meta: {title: '创建组织b'},
        },
        {
          path: '/home/leader/org_info',
          name: 'org_info',
          component: () => import('@/components/home/leader/org_info'),
          meta: {title: '组织资料'},
        },
        {
          path: '/home/leader/manage_leader',
          name: 'manage_leader',
          component: () => import('@/components/home/leader/manage_leader'),
          meta: {title: '领导管理'},
        },
        {
          path: '/home/leader/manage_volunteer',
          name: 'manage_volunteer',
          component: () => import('@/components/home/leader/manage_volunteer'),
          meta: {title: '成员管理'},
        },
        //五、排班模块
        {
          path: '/home/arrange/resource_display',
          name: 'resource_display',
          component: () => import('@/components/home/arrange/resource_display'),
          meta: {title: '资源展示'},
        },
        {
          path: '/home/arrange/arrange_resource',
          name: 'arrange_resource',
          component: () => import('@/components/home/arrange/arrange_resource'),
        },
      ]
    }
  ]
})

//登录权限控制，未登录跳转到login
const whiteList = ['/','/reg'] // 不重定向白名单: 登录页面或者注册页面
router.beforeEach((to, from, next) => {
  //cookie中有token
  if (localStorage.getItem("token")) {
    //登录了就跳转
    if (to.path === '/') {
      next({ path: '/home' })
    } else {
        next()
    }
  }
  //cookie中没有token
  else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next('/')
    }
  }
})

export default router;
