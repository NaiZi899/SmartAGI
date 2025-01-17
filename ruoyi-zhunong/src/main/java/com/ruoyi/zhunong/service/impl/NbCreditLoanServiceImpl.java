package com.ruoyi.zhunong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.zhunong.mapper.NbCreditLoanMapper;
import com.ruoyi.zhunong.domain.NbCreditLoan;
import com.ruoyi.zhunong.service.INbCreditLoanService;

/**
 * 信贷信息Service业务层处理
 *
 * @author lzl
 * @date 2025-01-10
 */
@Service
public class NbCreditLoanServiceImpl implements INbCreditLoanService {
    @Autowired
    private NbCreditLoanMapper nbCreditLoanMapper;

    /**
     * 查询信贷信息
     *
     * @param id 信贷信息主键
     * @return 信贷信息
     */
    @Override
    public NbCreditLoan selectNbCreditLoanById(Long id) {
        return nbCreditLoanMapper.selectNbCreditLoanById(id);
    }

    /**
     * 查询信贷信息列表
     *
     * @param nbCreditLoan 信贷信息
     * @return 信贷信息
     */
    @Override
    public List<NbCreditLoan> selectNbCreditLoanList(NbCreditLoan nbCreditLoan) {
        return nbCreditLoanMapper.selectNbCreditLoanList(nbCreditLoan);
    }

    /**
     * 新增信贷信息
     *
     * @param nbCreditLoan 信贷信息
     * @return 结果
     */
    @Override
    public int insertNbCreditLoan(NbCreditLoan nbCreditLoan) {
        return nbCreditLoanMapper.insertNbCreditLoan(nbCreditLoan);
    }

    /**
     * 修改信贷信息
     *
     * @param nbCreditLoan 信贷信息
     * @return 结果
     */
    @Override
    public int updateNbCreditLoan(NbCreditLoan nbCreditLoan) {
        return nbCreditLoanMapper.updateNbCreditLoan(nbCreditLoan);
    }

    /**
     * 批量删除信贷信息
     *
     * @param ids 需要删除的信贷信息主键
     * @return 结果
     */
    @Override
    public int deleteNbCreditLoanByIds(Long[] ids) {

        return nbCreditLoanMapper.deleteNbCreditLoanByIds(ids);
    }

    /**
     * 删除信贷信息信息
     *
     * @param id 信贷信息主键
     * @return 结果
     */
    @Override
    public int deleteNbCreditLoanById(Long id) {
        return nbCreditLoanMapper.deleteNbCreditLoanById(id);
    }

    /**
     * 批量发布信贷信息
     *
     * @param ids 需要发布的数据主键集合
     * @return 结果
     */
    @Override
    public int batchPublishNbCreditLoan(Long[] ids) {
        return nbCreditLoanMapper.batchPublishNbCreditLoan(ids);
    }

    /**
     * 批量取消发布信贷信息
     *
     * @param ids 需要取消发布的数据主键集合
     * @return 结果
     */
    @Override
    public int revokeBatchPublishNbCreditLoan(Long[] ids) {
        return nbCreditLoanMapper.revokeBatchPublishNbCreditLoan(ids);
    }

    /**
     * 批量推荐信贷信息
     *
     * @param data 需要推荐的数据主键集合
     * @return 结果
     */
    @Override
    public int recommendNbCreditLoan(Long[] data) {
        return nbCreditLoanMapper.recommendNbCreditLoan(data);
    }

    /**
     * 批量取消推荐信贷信息
     *
     * @param ids 需要取消推荐的数据主键集合
     * @return 结果
     */
    @Override
    public int unRecommendNbCreditLoan(Long[] ids) {
        return nbCreditLoanMapper.unRecommendNbCreditLoan(ids);
    }

    @Override
    public int deletenbCreditLoanById(Long id) {
        return 0;
    }
}
