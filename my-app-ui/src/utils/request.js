//配置信息request.js，主要是使用axios来
import axios from 'axios'
import {Message, MessageBox} from 'element-ui'
import store from '../store'
// 创建axios实例
const service = axios.create({
  // baseURL: process.env.BASE_API, // api的base_url,不能写错，写成baseUrl都是不生效的

  // 本机使用的地址
  baseURL: "http://127.0.0.1:8080",
  // 局域网使用的地址
  // baseURL: "http://10.218.26.113:8080",
  //内网穿透的临时baseURL,千万别加上：8080
  // baseURL: "https://181adeb1.cpolar.cn",
  timeout: 15000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(config => {
  console.log("进入前端request的拦截器！")
  // if (store.getters.token) {
  if(localStorage.getItem("token")){
    config.headers['Authorization'] = localStorage.getItem("token") // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  if(localStorage.getItem("id")){
    config.headers['id'] = localStorage.getItem("id") // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  // config.headers['Access-Control-Allow-Origin']="*";
  // }
  console.log("到达这里，应该不是获取token出问题")
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
    /**
     * code为非200是抛错 可结合自己业务进行修改
     */
    const res = response.data
    if (res.code !== 200) {
      Message({
        message: res.msg,
        type: 'error',
        duration: 3 * 1000
      })

      // 401:未登录;
      if (res.code === 401) {
        MessageBox.confirm('登录已经过期，您可以继续留在该页面，或者重新登录', '确定登出', {
          confirmButtonText: '重新登录',
          cancelButtonText: '留在此页',
          type: 'warning'
        }).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload()// 为了重新实例化vue-router对象 避免bug
          })
        })
      }
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 3 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
