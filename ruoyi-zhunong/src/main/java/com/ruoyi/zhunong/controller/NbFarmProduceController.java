package com.ruoyi.zhunong.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.zhunong.domain.NbFarmProduce;
import com.ruoyi.zhunong.service.INbFarmProduceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 农产品Controller
 * 
 * @author wyz
 * @date 2025-01-14
 */
@RestController
@RequestMapping("/zhunong/produce")
public class NbFarmProduceController extends BaseController
{
    @Autowired
    private INbFarmProduceService nbFarmProduceService;

    /**
     * 查询农产品列表
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:list')")
    @GetMapping("/list")
    public TableDataInfo list(NbFarmProduce nbFarmProduce)
    {
        startPage();
        List<NbFarmProduce> list = nbFarmProduceService.selectNbFarmProduceList(nbFarmProduce);
        return getDataTable(list);
    }

    /**
     * 导出农产品列表
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:export')")
    @Log(title = "农产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NbFarmProduce nbFarmProduce)
    {
        List<NbFarmProduce> list = nbFarmProduceService.selectNbFarmProduceList(nbFarmProduce);
        ExcelUtil<NbFarmProduce> util = new ExcelUtil<NbFarmProduce>(NbFarmProduce.class);
        util.exportExcel(response, list, "农产品数据");
    }

    /**
     * 获取农产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nbFarmProduceService.selectNbFarmProduceById(id));
    }

    /**
     * 新增农产品
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:add')")
    @Log(title = "农产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NbFarmProduce nbFarmProduce)
    {
        return toAjax(nbFarmProduceService.insertNbFarmProduce(nbFarmProduce));
    }

    /**
     * 修改农产品
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:edit')")
    @Log(title = "农产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NbFarmProduce nbFarmProduce)
    {
        return toAjax(nbFarmProduceService.updateNbFarmProduce(nbFarmProduce));
    }

    /**
     * 删除农产品
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:remove')")
    @Log(title = "农产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nbFarmProduceService.deleteNbFarmProduceByIds(ids));
    }
    /**
     * 批量上架农产品
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:push')")
    @Log(title = "农产品", businessType = BusinessType.UPDATE)
    @PostMapping("/batch/push")
    public AjaxResult batchPush(@RequestBody Long[] ids) {
        return toAjax(nbFarmProduceService.batchPushNbFarmProduce(ids));
    }

    /**
     * 批量取消上架农产品
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:unpush')")
    @Log(title = "农产品", businessType = BusinessType.UPDATE)
    @PostMapping("/batch/unpush")
    public AjaxResult batchUnpush(@RequestBody Long[] ids) {
        return toAjax(nbFarmProduceService.revokeBatchPushNbFarmProduce(ids));
    }

    /**
     * 批量推荐农产品
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:recommend')")
    @Log(title = "农产品", businessType = BusinessType.UPDATE)
    @PostMapping("/batch/recommend")
    public AjaxResult batchRecommend(@RequestBody Long[] ids) {
        return toAjax(nbFarmProduceService.recommendNbFarmProduce(ids));
    }

    /**
     * 批量取消推荐农产品
     */
    @PreAuthorize("@ss.hasPermi('zhunong:produce:unrecommend')")
    @Log(title = "农产品", businessType = BusinessType.UPDATE)
    @PostMapping("/batch/unrecommend")
    public AjaxResult batchUnrecommend(@RequestBody Long[] ids) {
        return toAjax(nbFarmProduceService.unRecommendNbFarmProduce(ids));
    }


}
