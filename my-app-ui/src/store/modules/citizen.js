import { login, logout,reg, getInfo } from './login'
const citizen = {
  state: {
    token: localStorage.getItem("token"),
    name: '',
    roles: []
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    }
  },

  actions: {
    // 登录
    Login({ commit }, citizenInfo) {
      console.log("触发patch，进入名为Login的Action");
      const phone = citizenInfo.phone.trim()
      return new Promise((resolve, reject) => {
        login(phone, citizenInfo.password).then(response => {
          console.log("进入名为Login的Action，进入发送login的post请求后的response");
          // const data = response.data
          //这里不能用const tokenStr = data.token，因为这里直接用data就是指上面定义的data{phone,password},而不是respose中的data，一旦出错那么下面的执行都会出错
          // const tokenStr = response.data.token
          // const tokenStr = response.msg
          // setToken(tokenStr)
          console.log("response是：",response);
          console.log("response.data是:",response.data);
          console.log("msg是：",response.msg);
          console.log("response.data.id是:",response.data.id);
          console.log("response.data.token是:",response.data.token);
          localStorage.setItem("id",response.data.id);
          localStorage.setItem("citizenName",response.data.name);
          localStorage.setItem("token",response.data.token);
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登录
    Reg({ commit }, citizen) {
      console.log("触发patch，进入名为Reg的Action");
      // const phone = citizenInfo.phone.trim()
      return new Promise((resolve, reject) => {
        reg(citizen).then(response => {
          console.log("进入名为Reg的Action，进入发送reg的post请求后的response");
          console.log("response是：",response);
          console.log("response.data是:",response.data);
          console.log("msg是：",response.msg);
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    LogOut({ commit, state}) {
      console.log("进入citizen.js的一个action，名为LogOut!");
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          localStorage.clear();
          console.log("前端的token成功移除！")
          resolve()``
        }).catch(error => {
          reject(error)
        })
      })
    }

  }
}

export default citizen
