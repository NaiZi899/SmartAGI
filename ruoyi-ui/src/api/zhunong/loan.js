import request from '@/utils/request'

// 查询信贷信息列表
export function listLoan(query) {
  return request({
    url: '/zhunong/loan/list',
    method: 'get',
    params: query
  })
}

// 查询信贷信息详细
export function getLoan(id) {
  return request({
    url: '/zhunong/loan/' + id,
    method: 'get'
  })
}

// 新增信贷信息
export function addLoan(data) {
  return request({
    url: '/zhunong/loan/add',
    method: 'post',
    data: data
  })
}

// 修改信贷信息
export function updateLoan(data) {
  return request({
    url: '/zhunong/loan',
    method: 'put',
    data: data
  })
}

// 删除信贷信息
export function delLoan(id) {
  return request({
    url: '/zhunong/loan/' + id,
    method: 'delete'
  })
}
// 批量发布信贷信息
export function batchPublishNbCreditLoan(ids) {
  return request({
    url: '/zhunong/loan/batch/publish',
    method: 'post',
    data:ids,
    withLoading:true
  })
}
// 批量取消发布信贷信息
export function revokeBatchPublishNbCreditLoan(ids) {
  return request({
    url: '/zhunong/loan/batch/unpublish',
    method: 'post',
    data:ids,
    withLoading:true
  })
}
// 批量推荐信贷信息
export function recommendNbCreditLoan(ids) {
  return request({
    url: '/zhunong/loan/batch/recommend',
    method: 'post',
    data:ids,
    withLoading:true
  })
}
// 批量取消推荐信贷信息  
export function unRecommendNbCreditLoan(ids) {  
  return request({  
    url: '/zhunong/loan/batch/unrecommend',  
    method: 'post',  
    data: ids,  
    withLoading: true  
  })  
}