import request from '@/utils/request'

// 查询农事服务列表
export function listWork(query) {
  return request({
    url: '/zhunong/work/list',
    method: 'get',
    params: query
  })
}

// 查询农事服务详细
export function getWork(id) {
  return request({
    url: '/zhunong/work/' + id,
    method: 'get'
  })
}

// 新增农事服务
export function addWork(data) {
  return request({
    url: '/zhunong/work',
    method: 'post',
    data: data
  })
}

// 修改农事服务
export function updateWork(data) {
  return request({
    url: '/zhunong/work',
    method: 'put',
    data: data
  })
}

// 删除农事服务
export function delWork(id) {
  return request({
    url: '/zhunong/work/' + id,
    method: 'delete'
  })
}
// export function pushFarmSeek(data) {
//   return request({
//     url:"/zhunong/work/pushFarmSeek",
//     method: "post",
//     data: data,
//     withLoading: true,
//   });
// }
// 上架农事服务
export function pushFarmWork(data) {
  return request({
    url:"/zhunong/work/pushFarmWork",
    method: "post",
    data: data,
    withLoading: true,
  });
}
// // 下架农户求助
// export function unpushFarmSeek(data) {
//   return request({
//     url:"/zhunong/work/unpushFarmSeek",
//     method: "post",
//     data: data,
//     withLoading: true,
//   });
// }
// 下架农事服务
export function unpushFarmWork(data) {
  return request({
    url:"/zhunong/work/unpushFarmWork",
    method: "post",
    data: data,
    withLoading: true,
  });
}
// 推荐农户求助
export function recommendFarmSeek(data) {
  return request({
    url:"/zhunong/work/recommendFarmSeek",
    method: "post",
    data: data,
    withLoading: true,
  });
}
// 推荐农事服务
export function recommendFarmWork(data) {
  return request({
    url:"/zhunong/work/recommendFarmWork",
    method: "post",
    data: data,
    withLoading: true,
  });
}
// 取消推荐农户求助
export function unrecommendFarmSeek(data) {
  return request({url:"/zhunong/work/unrecommendFarmSeek",
    method: "post",
    data: data,
    withLoading: true,
  });
}
// 取消推荐农事服务
export function unrecommendFarmWork(data) {
  return request({url:"/zhunong/work/unrecommendFarmWork",
       method: "post",
    data: data,
    withLoading: true,
  });
}
