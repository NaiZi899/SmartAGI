package com.ruoyi.zhunong.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.ruoyi.zhunong.domain.NbFarmWork;

/**
 * 农事服务Mapper接口
 *
 * @author lyx
 * @date 2025-01-11
 */
public interface NbFarmWorkMapper
{
    /**
     * 查询农事服务
     *
     * @param id 农事服务主键
     * @return 农事服务
     */
    public NbFarmWork selectNbFarmWorkById(Long id);

    /**
     * 查询农事服务列表
     *
     * @param nbFarmWork 农事服务
     * @return 农事服务集合
     */
    public List<NbFarmWork> selectNbFarmWorkList(NbFarmWork nbFarmWork);

    /**
     * 新增农事服务
     *
     * @param nbFarmWork 农事服务
     * @return 结果
     */
    public int insertNbFarmWork(NbFarmWork nbFarmWork);

    /**
     * 修改农事服务
     *
     * @param nbFarmWork 农事服务
     * @return 结果
     */
    public int updateNbFarmWork(NbFarmWork nbFarmWork);

    /**
     * 删除农事服务
     *
     * @param id 农事服务主键
     * @return 结果
     */
    public int deleteNbFarmWorkById(Long id);

    /**
     * 批量删除农事服务
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNbFarmWorkByIds(Long[] ids);

//    批量上架
    public int pushFarmWork(Long[] ids);
//    批量下架
    public int unpushFarmWork(Long[] ids);
//    批量推荐
    public int recommendFarmWork(Long[] ids);
     //    批量取消推荐
    public int unrecommendFarmWork(Long[] ids);
}
