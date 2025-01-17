import request from '@/utils/request'

// 查询农产品列表
export function listProduce(query) {
  return request({
    url: '/zhunong/produce/list',
    method: 'get',
    params: query
  })
}

// 查询农产品详细
export function getProduce(id) {
  return request({
    url: '/zhunong/produce/' + id,
    method: 'get'
  })
}

// 新增农产品
export function addProduce(data) {
  return request({
    url: '/zhunong/produce',
    method: 'post',
    data: data
  })
}

// 修改农产品
export function updateProduce(data) {
  return request({
    url: '/zhunong/produce',
    method: 'put',
    data: data
  })
}

// 删除农产品
export function delProduce(ids) {
  return request({
    url: '/zhunong/produce/' + ids.join(','),
    method: 'delete'
  });
}
// 批量上架农产品
export function batchPushNbFarmProduce(ids) {
  return request({
    url: '/zhunong/produce/batch/push',
    method: 'post',
    data:ids,
    withLoading:true
  })
}
// 批量取消上架农产品
export function revokeBatchPushNbFarmProduce(ids) {
  return request({
    url: '/zhunong/produce/batch/unpush',
    method: 'post',
    data:ids,
    withLoading:true
  })
}
// 批量推荐农产品
export function recommendNbFarmProduce(ids) {
  return request({
    url: '/zhunong/produce/batch/recommend',
    method: 'post',
    data:ids,
    withLoading:true
  })
}
// 批量取消推荐农产品
export function unRecommendNbFarmProduce(ids) {
  return request({
    url: '/zhunong/produce/batch/unrecommend',
    method: 'post',
    data: ids,
    withLoading: true
  })
}
