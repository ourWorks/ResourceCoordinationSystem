import request from '@/utils/request'

//一、管理员设置本地组织的存贮信息
export function setLocalOrg(response) {
  localStorage.setItem("org_longitude",response.data.longitude);
  localStorage.setItem("org_latitude",response.data.latitude);
  localStorage.setItem("org_orgName",response.data.orgName);
  // localStorage.setItem("org_orgId",response.data.orgId);
  localStorage.setItem("org_address",response.data.address);
}

//二、判断本市民是否为某个组织的领导
export function isLeader(data) {
  return request({
      url: '/isLeader',
      method: 'post',
      data: data,
    }
  )
}

export function addOrg(data) {
  return request({
      url: '/addOrg',
      method: 'post',
      data: data,
    }
  )
}

export function updateOrg(data) {
  return request({
      url: '/updateOrg',
      method: 'post',
      data: data,
    }
  )
}

export function delOrg(data) {
  return request({
      url: '/delOrg',
      method: 'post',
      data: data,
    }
  )
}

////////////////////////////////////////////////////领导管理/////////////////////////////////////////////
//获取所有领导信息
export function getOrgLeaders(data) {
  return request({
      url: '/getOrgLeaders',
      method: 'post',
      data: data,
    }
  )
}
//新增领导
export function addLeader(data) {
  return request({
      url: '/addLeader',
      method: 'post',
      data: data,
    }
  )
}
//删除领导（市民作为领导退出组织）
export function quitOrgAsLeader(data) {
  return request({
      url: '/quitOrgAsLeader',
      method: 'post',
      data: data,
    }
  )
}
////////////////////////////////////////////////////领导管理/////////////////////////////////////////////

////////////////////////////////////////////////////志愿者管理/////////////////////////////////////////////
//获取所有志愿者信息
export function getOrgVolunteers(data) {
  return request({
      url: '/getOrgVolunteers',
      method: 'post',
      data: data,
    }
  )
}
//新增志愿者
export function addVolunteer(data) {
  return request({
      url: '/addVolunteer',
      method: 'post',
      data: data,
    }
  )
}
//删除志愿者（市民作为志愿者退出组织）
export function quitOrgAsVolunteer(data) {
  return request({
      url: '/quitOrgAsVolunteer',
      method: 'post',
      data: data,
    }
  )
}
////////////////////////////////////////////////////志愿者管理/////////////////////////////////////////////


export function getOrgVolunteersPage(data) {
  return request({
      url: '/getOrgVolunteersPage',
      method: 'post',
      data: data,
    }
  )
}
