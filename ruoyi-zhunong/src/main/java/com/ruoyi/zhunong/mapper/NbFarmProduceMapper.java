package com.ruoyi.zhunong.mapper;

import java.util.List;
import com.ruoyi.zhunong.domain.NbFarmProduce;

/**
 * 农产品Mapper接口
 * 
 * @author wyz
 * @date 2025-01-14
 */
public interface NbFarmProduceMapper 
{
    /**
     * 查询农产品
     * 
     * @param id 农产品主键
     * @return 农产品
     */
    public NbFarmProduce selectNbFarmProduceById(Long id);

    /**
     * 查询农产品列表
     * 
     * @param nbFarmProduce 农产品
     * @return 农产品集合
     */
    public List<NbFarmProduce> selectNbFarmProduceList(NbFarmProduce nbFarmProduce);

    /**
     * 新增农产品
     * 
     * @param nbFarmProduce 农产品
     * @return 结果
     */
    public int insertNbFarmProduce(NbFarmProduce nbFarmProduce);

    /**
     * 修改农产品
     * 
     * @param nbFarmProduce 农产品
     * @return 结果
     */
    public int updateNbFarmProduce(NbFarmProduce nbFarmProduce);

    /**
     * 删除农产品
     * 
     * @param id 农产品主键
     * @return 结果
     */
    public int deleteNbFarmProduceById(Long id);

    /**
     * 批量删除农产品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNbFarmProduceByIds(Long[] ids);

    /**
     * 批量上架农产品
     *
     * @param ids 需要上架的数据主键集合
     * @return 结果
     */
    public int batchPushNbFarmProduce(Long[] ids);

    /**
     * 批量取消上架农产品
     *
     * @param ids 需要取消上架的数据主键集合
     * @return 结果
     */
    public int revokeBatchPushNbFarmProduce(Long[] ids);

    /**
     * 批量推荐信贷信息
     *
     * @param ids 需要推荐的数据主键集合
     * @return 结果
     */
    public int recommendNbFarmProduce(Long[] ids);

    /**
     * 批量取消推荐信贷信息
     *
     * @param ids 需要取消推荐的数据主键集合
     * @return 结果
     */
    public int unRecommendNbFarmProduce(Long[] ids);
}
