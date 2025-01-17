package com.ruoyi.zhunong.mapper;

import java.util.List;
import com.ruoyi.zhunong.domain.NbCreditLoan;

/**
 * 信贷信息Mapper接口
 * 
 * @author lzl
 * @date 2025-01-10
 */
public interface NbCreditLoanMapper 
{
    /**
     * 查询信贷信息
     * 
     * @param id 信贷信息主键
     * @return 信贷信息
     */
    public NbCreditLoan selectNbCreditLoanById(Long id);

    /**
     * 查询信贷信息列表
     * 
     * @param nbCreditLoan 信贷信息
     * @return 信贷信息集合
     */
    public List<NbCreditLoan> selectNbCreditLoanList(NbCreditLoan nbCreditLoan);

    /**
     * 新增信贷信息
     * 
     * @param nbCreditLoan 信贷信息
     * @return 结果
     */
    public int insertNbCreditLoan(NbCreditLoan nbCreditLoan);

    /**
     * 修改信贷信息
     * 
     * @param nbCreditLoan 信贷信息
     * @return 结果
     */
    public int updateNbCreditLoan(NbCreditLoan nbCreditLoan);

    /**
     * 删除信贷信息
     * 
     * @param id 信贷信息主键
     * @return 结果
     */
    public int deleteNbCreditLoanById(Long id);

    /**
     * 批量删除信贷信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNbCreditLoanByIds(Long[] ids);
    /**
     * 批量发布信贷信息
     *
     * @param ids 需要发布的数据主键集合
     * @return 结果
     */
    public int batchPublishNbCreditLoan(Long[] ids);

    /**
     * 批量取消发布信贷信息
     *
     * @param ids 需要取消发布的数据主键集合
     * @return 结果
     */
    public int revokeBatchPublishNbCreditLoan(Long[] ids);

    /**
     * 批量推荐信贷信息
     *
     * @param ids 需要推荐的数据主键集合
     * @return 结果
     */
    public int recommendNbCreditLoan(Long[] ids);

    /**
     * 批量取消推荐信贷信息
     *
     * @param ids 需要取消推荐的数据主键集合
     * @return 结果
     */
    public int unRecommendNbCreditLoan(Long[] ids);
}
