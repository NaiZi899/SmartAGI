package com.ruoyi.zhunong.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.zhunong.mapper.NbFarmMarketMapper;
import com.ruoyi.zhunong.domain.NbFarmMarket;
import com.ruoyi.zhunong.service.INbFarmMarketService;

/**
 * 农贸市场Service业务层处理
 * 
 * @author jyn
 * @date 2024-10-11
 */
@Service
public class NbFarmMarketServiceImpl implements INbFarmMarketService 
{
    @Autowired
    private NbFarmMarketMapper nbFarmMarketMapper;

    /**
     * 查询农贸市场
     * 
     * @param id 农贸市场主键
     * @return 农贸市场
     */
    @Override
    public NbFarmMarket selectNbFarmMarketById(Long id)
    {
        return nbFarmMarketMapper.selectNbFarmMarketById(id);
    }

    /**
     * 查询农贸市场列表
     * 
     * @param nbFarmMarket 农贸市场
     * @return 农贸市场
     */
    @Override
    public List<NbFarmMarket> selectNbFarmMarketList(NbFarmMarket nbFarmMarket)
    {
        return nbFarmMarketMapper.selectNbFarmMarketList(nbFarmMarket);
    }

    /**
     * 新增农贸市场
     * 
     * @param nbFarmMarket 农贸市场
     * @return 结果
     */
    @Override
    public int insertNbFarmMarket(NbFarmMarket nbFarmMarket)
    {
        return nbFarmMarketMapper.insertNbFarmMarket(nbFarmMarket);
    }

    /**
     * 修改农贸市场
     * 
     * @param nbFarmMarket 农贸市场
     * @return 结果
     */
    @Override
    public int updateNbFarmMarket(NbFarmMarket nbFarmMarket)
    {
        return nbFarmMarketMapper.updateNbFarmMarket(nbFarmMarket);
    }

    /**
     * 批量删除农贸市场
     * 
     * @param ids 需要删除的农贸市场主键
     * @return 结果
     */
    @Override
    public int deleteNbFarmMarketByIds(Long[] ids)
    {
        return nbFarmMarketMapper.deleteNbFarmMarketByIds(ids);
    }

    /**
     * 删除农贸市场信息
     * 
     * @param id 农贸市场主键
     * @return 结果
     */
    @Override
    public int deleteNbFarmMarketById(Long id)
    {
        return nbFarmMarketMapper.deleteNbFarmMarketById(id);
    }
}
