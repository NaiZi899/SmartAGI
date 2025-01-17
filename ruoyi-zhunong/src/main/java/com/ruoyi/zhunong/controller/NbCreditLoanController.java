package com.ruoyi.zhunong.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.zhunong.domain.NbCreditLoan;
import com.ruoyi.zhunong.service.INbCreditLoanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 信贷信息Controller
 *
 * @author lzl
 * @date 2025-01-10
 */
//@Api注解为这个控制器类提供了 Swagger 文档的标题
@Api("信贷信息接口")
@RestController
//控制器类的基础请求路径
@RequestMapping("/zhunong/loan")
public class NbCreditLoanController extends BaseController {
    @Autowired
    private INbCreditLoanService nbCreditLoanService;

    /**
     * 查询信贷信息列表
     */
    @ApiOperation("查询信贷信息列表")
    //访问权限
    @PreAuthorize("@ss.hasPermi('zhunong:loan:list')")
    @GetMapping("/list")
    public TableDataInfo list(NbCreditLoan nbCreditLoan) {
        startPage();
        List<NbCreditLoan> list = nbCreditLoanService.selectNbCreditLoanList(nbCreditLoan);
        return getDataTable(list);
    }

    /**
     * 导出信贷信息列表
     */
    @ApiOperation("导出信贷信息列表")
    @PreAuthorize("@ss.hasPermi('zhunong:loan:export')")
    @Log(title = "信贷信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NbCreditLoan nbCreditLoan) {
        List<NbCreditLoan> list = nbCreditLoanService.selectNbCreditLoanList(nbCreditLoan);
        ExcelUtil<NbCreditLoan> util = new ExcelUtil<NbCreditLoan>(NbCreditLoan.class);
        util.exportExcel(response, list, "信贷信息数据");
    }

    /**
     * 获取信贷信息详细信息
     */
    @ApiOperation("获取信贷信息详细信息")
    @PreAuthorize("@ss.hasPermi('zhunong:loan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(nbCreditLoanService.selectNbCreditLoanById(id));
    }

    /**
     * 新增信贷信息
     */
    @ApiOperation("新增信贷信息")
    @PreAuthorize("@ss.hasPermi('zhunong:loan:add')")
    @Log(title = "信贷信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody NbCreditLoan nbCreditLoan) {
        return toAjax(nbCreditLoanService.insertNbCreditLoan(nbCreditLoan));
    }

    /**
     * 修改信贷信息
     */
    @ApiOperation("修改信贷信息")
    @PreAuthorize("@ss.hasPermi('zhunong:loan:edit')")
    @Log(title = "信贷信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NbCreditLoan nbCreditLoan) {
        return toAjax(nbCreditLoanService.updateNbCreditLoan(nbCreditLoan));
    }

    /**
     * 删除信贷信息
     */
    @ApiOperation("删除信贷信息")
    @PreAuthorize("@ss.hasPermi('zhunong:loan:remove')")
    @Log(title = "信贷信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(nbCreditLoanService.deleteNbCreditLoanByIds(ids));
    }

    /**
     * 批量发布信贷信息
     */
    @ApiOperation("批量发布信贷信息")
    @PreAuthorize("@ss.hasPermi('zhunong:loan:publish')")
    @Log(title = "信贷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/batch/publish")
    public AjaxResult batchPublish(@RequestBody Long[] ids) {
        return toAjax(nbCreditLoanService.batchPublishNbCreditLoan(ids));
    }

    /**
     * 批量取消发布信贷信息
     */
    @ApiOperation("批量取消发布信贷信息")
    @PreAuthorize("@ss.hasPermi('zhunong:loan:unpublish')")
    @Log(title = "信贷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/batch/unpublish")
    public AjaxResult batchUnpublish(@RequestBody Long[] ids) {
        return toAjax(nbCreditLoanService.revokeBatchPublishNbCreditLoan(ids));
    }

    /**
     * 批量推荐信贷信息
     */
    @ApiOperation("批量推荐信贷信息")
    @PreAuthorize("@ss.hasPermi('zhunong:loan:recommend')")
    @Log(title = "信贷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/batch/recommend")
    public AjaxResult batchRecommend(@RequestBody Long[] ids) {
        return toAjax(nbCreditLoanService.recommendNbCreditLoan(ids));
    }

    /**
     * 批量取消推荐信贷信息
     */
    @ApiOperation("批量取消推荐信贷信息")
    @PreAuthorize("@ss.hasPermi('zhunong:loan:unrecommend')")
    @Log(title = "信贷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/batch/unrecommend")
    public AjaxResult batchUnrecommend(@RequestBody Long[] ids) {
        return toAjax(nbCreditLoanService.unRecommendNbCreditLoan(ids));
    }
}
