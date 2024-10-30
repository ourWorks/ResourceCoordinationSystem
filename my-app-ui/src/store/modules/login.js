import request from '@/utils/request'

export function login(phone, password) {
  return request({
    url:'/login',
    method: 'post',
    data: {
      phone,
      password
    }
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

export function getInfo() {
  return request({
    url: '/admin/info',
    method: 'get',
  })
}

export function reg(data) {
  return request({
    url: '/register',
    method: 'post',
    data: data
  })
}

/////////////////////////////////////////////里面的还没有用到/////////////////////////////////////
export function updateCitizen(id, data) {
  return request({
    url: '/update' + id,
    method: 'post',
    data: data
  })
}

export function deleteCitizen(id) {
  return request({
    url: '/delete' + id,
    method: 'post'
  })
}
/////////////////////////////////////////////里面的还没有用到/////////////////////////////////////
