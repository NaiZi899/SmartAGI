package com.ruoyi.zhunong.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.zhunong.mapper.NbFarmProduceMapper;
import com.ruoyi.zhunong.domain.NbFarmProduce;
import com.ruoyi.zhunong.service.INbFarmProduceService;

/**
 * 农产品Service业务层处理
 * 
 * @author wyz
 * @date 2025-01-14
 */
@Service
public  class NbFarmProduceServiceImpl implements INbFarmProduceService
{
    @Autowired
    private NbFarmProduceMapper nbFarmProduceMapper;

    /**
     * 查询农产品
     * 
     * @param id 农产品主键
     * @return 农产品
     */
    @Override
    public NbFarmProduce selectNbFarmProduceById(Long id)
    {
        return nbFarmProduceMapper.selectNbFarmProduceById(id);
    }

    /**
     * 查询农产品列表
     * 
     * @param nbFarmProduce 农产品
     * @return 农产品
     */
    @Override
    public List<NbFarmProduce> selectNbFarmProduceList(NbFarmProduce nbFarmProduce)
    {
        return nbFarmProduceMapper.selectNbFarmProduceList(nbFarmProduce);
    }

    /**
     * 新增农产品
     * 
     * @param nbFarmProduce 农产品
     * @return 结果
     */
    @Override
    public int insertNbFarmProduce(NbFarmProduce nbFarmProduce)
    {
        return nbFarmProduceMapper.insertNbFarmProduce(nbFarmProduce);
    }

    /**
     * 修改农产品
     * 
     * @param nbFarmProduce 农产品
     * @return 结果
     */
    @Override
    public int updateNbFarmProduce(NbFarmProduce nbFarmProduce)
    {
        return nbFarmProduceMapper.updateNbFarmProduce(nbFarmProduce);
    }

    @Override
    public int deleteNbCreditLoanByIds(Long[] ids) {
        return 0;
    }

    /**
     * 批量删除农产品
     * 
     * @param ids 需要删除的农产品主键
     * @return 结果
     */
    @Override
    public int deleteNbFarmProduceByIds(Long[] ids)
    {
        return nbFarmProduceMapper.deleteNbFarmProduceByIds(ids);
    }

    /**
     * 删除农产品信息
     * 
     * @param id 农产品主键
     * @return 结果
     */
    @Override
    public int deleteNbFarmProduceById(Long id)
    {
        return nbFarmProduceMapper.deleteNbFarmProduceById(id);
    }

    /**
     * 批量上架农产品
     *
     * @param ids 需要上架的数据主键集合
     * @return 结果
     */
    @Override
    public int batchPushNbFarmProduce(Long[] ids) {
        return nbFarmProduceMapper.batchPushNbFarmProduce(ids);
    }

    /**
     * 批量取消上架农产品
     *
     * @param ids 需要取消上架的数据主键集合
     * @return 结果
     */
    @Override
    public int revokeBatchPushNbFarmProduce(Long[] ids) {
        return nbFarmProduceMapper.revokeBatchPushNbFarmProduce(ids);
    }

    /**
     * 批量推荐信贷信息
     *
     * @param data 需要推荐的数据主键集合
     * @return 结果
     */
    @Override
    public int recommendNbFarmProduce(Long[] data) {
        return nbFarmProduceMapper.recommendNbFarmProduce(data);
    }

    /**
     * 批量取消推荐信贷信息
     *
     * @param ids 需要取消推荐的数据主键集合
     * @return 结果
     */
    @Override
    public int unRecommendNbFarmProduce(Long[] ids) {
        return nbFarmProduceMapper.unRecommendNbFarmProduce(ids);
    }


}
