package com.ruoyi.zhunong.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.zhunong.mapper.NbFarmWorkMapper;
import com.ruoyi.zhunong.domain.NbFarmWork;
import com.ruoyi.zhunong.service.INbFarmWorkService;

/**
 * 农事服务Service业务层处理
 *
 * @author lyx
 * @date 2025-01-11
 */
@Service
public class NbFarmWorkServiceImpl implements INbFarmWorkService
{
    @Autowired
    public NbFarmWorkMapper nbFarmWorkMapper;

    /**
     * 查询农事服务
     *
     * @param id 农事服务主键
     * @return 农事服务
     */
    @Override
    public NbFarmWork selectNbFarmWorkById(Long id)
    {
        return nbFarmWorkMapper.selectNbFarmWorkById(id);
    }

    /**
     * 查询农事服务列表
     *
     * @param nbFarmWork 农事服务
     * @return 农事服务
     */
    @Override
    public List<NbFarmWork> selectNbFarmWorkList(NbFarmWork nbFarmWork)
    {
        return nbFarmWorkMapper.selectNbFarmWorkList(nbFarmWork);
    }

    /**
     * 新增农事服务
     *
     * @param nbFarmWork 农事服务
     * @return 结果
     */
    @Override
    public int insertNbFarmWork(NbFarmWork nbFarmWork)
    {
        if(hasNonNullFields(nbFarmWork)){
            return nbFarmWorkMapper.insertNbFarmWork(nbFarmWork);
        }else{
            System.out.println("所有字段都为空，不执行插入操作");
        }
        return 0;
    }
    /**
     * 修改农事服务
     *
     * @param nbFarmWork 农事服务
     * @return 结果
     */
    @Override
    public int updateNbFarmWork(NbFarmWork nbFarmWork)
    {
        return nbFarmWorkMapper.updateNbFarmWork(nbFarmWork);
    }

    /**
     * 批量删除农事服务
     *
     * @param ids 需要删除的农事服务主键
     * @return 结果
     */
    @Override
    public int deleteNbFarmWorkByIds(Long[] ids)
    {
        return nbFarmWorkMapper.deleteNbFarmWorkByIds(ids);
    }

    /**
     * 删除农事服务信息
     *
     * @param id 农事服务主键
     * @return 结果
     */
    @Override
    public int deleteNbFarmWorkById(Long id)
    {
        return nbFarmWorkMapper.deleteNbFarmWorkById(id);
    }
//  批量上架
    @Override
    public int pushFarmWork(Long[] ids) {
        return nbFarmWorkMapper.pushFarmWork(ids);
    }
//  批量下架
    @Override
    public int unpushFarmWork(Long[] ids) {
        return nbFarmWorkMapper.unpushFarmWork(ids);
    }

    @Override
    public int recommendFarmWork(Long[] ids) {
        return nbFarmWorkMapper.recommendFarmWork(ids);
    }

    @Override
    public int unrecommendFarmWork(Long[] ids) {
        return nbFarmWorkMapper.unrecommendFarmWork(ids);
    }

    private boolean hasNonNullFields(NbFarmWork nbFarmWork) {
        return nbFarmWork.getTitle() != null ||
                nbFarmWork.getImage() != null ||
                nbFarmWork.getPrice() != null ||
                nbFarmWork.getCatgory() != null ||
                nbFarmWork.getResume() != null ||
                nbFarmWork.getPhone() != null ||
                nbFarmWork.getWeChat() != null ||
                nbFarmWork.getProviderName() != null ||
                nbFarmWork.getDescription() != null ||
                nbFarmWork.getPubdate() != null ||
                nbFarmWork.getPubAccountId() != null ||
                nbFarmWork.getPushStatus() != null ||
                nbFarmWork.getRecommend() != null ||
                nbFarmWork.getBrowseNum() != null ||
                nbFarmWork.getCreatedTime() != null ||
                nbFarmWork.getUpdatedTime() != null;
    }
}
