import request from '@/utils/request'

/////////////////////////////////////////////发起救助模块/////////////////////////////////////
export function handleHelp(data) {
  return request({
      url: '/call',
      method: 'post',
      data: data,
      //不知道为什么刷新前全局的拦截不生效，所以先进行这个设置吧，为了校验身份
      //   headers: {
      //     Authorization: localStorage.getItem("token"),
      //     id: localStorage.getItem("id")
      //   }
    }
  )
}

export function showHelp(data) {
  return request({
      url: '/call/helpList',
      method: 'post',
      data: data,
      //不知道为什么刷新前全局的拦截不生效，所以先进行这个设置吧，为了校验身份
      //   headers: {
      //     Authorization: localStorage.getItem("token"),
      //     id: localStorage.getItem("id")
      //   }
    }
  )
}

export function editHelp(data) {
  return request({
      url: '/call/editHelp',
      method: 'post',
      data: {cid: data},
    }
  )
}

export function updateHelp(data) {
  return request({
      url: '/call/updateHelp',
      method: 'post',
      data: data,
    }
  )
}

export function delHelp(data) {
  return request({
      url: '/call/delHelp',
      method: 'post',
      data: {cid: data},
    }
  )
}
/////////////////////////////////////////////发起救助模块/////////////////////////////////////

