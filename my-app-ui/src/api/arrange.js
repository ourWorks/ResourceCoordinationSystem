import request from '@/utils/request'

//获取未删除市民、未删除志愿者、未删除领导的数量
export function getMembersNum() {
  return request({
      url: '/getMembersNum',
      method: 'post',
      // data: data,
    }
  )
}

//获取所有“待救援”的求助表单的需求数组
export function getTotalDemandNum(data) {
  return request({
      url: '/getTotalDemandNum',
      method: 'post',
      data: data,
    }
  )
}

//获取组织的物资概况数组
export function getOrgResourceNum(data) {
  return request({
      url: '/getOrgResourceNum',
      method: 'post',
      data: data,
    }
  )
}

//获取排班信息
export function showArrange(data) {
  return request({
      url: '/showArrange',
      method: 'post',
      data: data,
    }
  )
}

//开始排班
export function beginArrange(data) {
  return request({
      url: '/beginArrange',
      method: 'post',
      data: data,
    }
  )
}


