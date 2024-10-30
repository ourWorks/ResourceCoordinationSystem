import request from '@/utils/request'

//设置本地志愿者的存贮信息
export function setLocalVolunteer(response) {
  localStorage.setItem("volunteerId", response.data.volunteerId);
  localStorage.setItem("v_longitude", response.data.longitude);
  localStorage.setItem("v_latitude", response.data.latitude);
  localStorage.setItem("v_orgId", response.data.orgId);
  localStorage.setItem("startTime", response.data.startTime);
  localStorage.setItem("endTime", response.data.endTime);
  localStorage.setItem("ondueState", response.data.ondueState);
}

//判断本市民是否为某个组织的志愿者
export function isVolunteer(data) {
  return request({
      url: '/isVolunteer',
      method: 'post',
      data: data,
    }
  )
}

//成为某个组织的志愿者，需要额外传入id
export function addOrUpdateVolunteer(data) {
  return request({
      url: '/addOrUpdateVolunteer',
      method: 'post',
      data: data
    }
  )
}

//根据orgId获取组织信息
export function orgInfo(data) {
  return request({
      url: '/orgInfo',
      method: 'post',
      data: data
    }
  )
}


//在本地存储市民的医疗资源Medical Resource
export function setLocalVMR(response) {
  localStorage.setItem("v_mask", response.data.mask);
  localStorage.setItem("v_bandage", response.data.bandage);
  localStorage.setItem("v_stretcher", response.data.stretcher);
  localStorage.setItem("v_depressor", response.data.depressor);
  localStorage.setItem("v_antihistamine", response.data.antihistamine);
}

//在本地存储市民的日用资源Medical Resource
export function setLocalVDR(response) {
  localStorage.setItem("v_water", response.data.water);
  localStorage.setItem("v_food", response.data.food);
  localStorage.setItem("v_tent", response.data.tent);
  localStorage.setItem("v_car", response.data.car);
  localStorage.setItem("v_boat", response.data.boat);
}

//获取本市民的医疗资源信息和日用资源信息
export function getMedicalResource() {
  return request({
      url: '/getMedicalResource',
      method: 'post'
    }
  )
}

export function getDailyResource() {
  return request({
      url: '/getDailyResource',
      method: 'post'
    }
  )
}

//获取本市民的医疗资源信息和日用资源信息
export function updateMedicalResource(data) {
  return request({
      url: '/updateMedicalResource',
      method: 'post',
      data: data
    }
  )
}

export function updateDailyResource(data) {
  return request({
      url: '/updateDailyResource',
      method: 'post',
      data: data
    }
  )
}

/////////////////////////////////////////////志愿者模块的选取任务///////////////////////////////////////////////////
//获取所有“待救援”或“已安排”的任务
export function getWaitingOrArrangedTask(data) {
  return request({
      url: '/getWaitingOrArrangedTask',
      method: 'post',
      data: {
        cid: data,
        id: localStorage.getItem("id")
      },
    }
  )
}

/////////////////////////////////////////////志愿者模块的选取任务///////////////////////////////////////////////////

//获取某个志愿者完成的求助表单，任务状态是“已完成”,并且不是自己的
export function myVolunteerTask() {
  return request({
      url: '/myVolunteerTask',
      method: 'post',
      data: {
        volunteerId: localStorage.getItem("volunteerId")
      }
    }
  )
}

//获取某个志愿者完成的求助表单，任务状态是“已完成”,并且不是自己的
export function beginVolunteerTask(data) {
  return request({
      url: '/beginVolunteerTask',
      method: 'post',
      data: data
    }
  )
}

//获取某个志愿者完成的求助表单，任务状态是“已完成”,并且不是自己的
export function changeHelpState(data) {
  return request({
      url: '/changeHelpState',
      method: 'post',
      data: data
    }
  )
}


