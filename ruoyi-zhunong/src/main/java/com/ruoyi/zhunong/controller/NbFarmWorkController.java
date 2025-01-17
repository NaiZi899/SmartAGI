package com.ruoyi.zhunong.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.zhunong.domain.NbFarmWork;
import com.ruoyi.zhunong.service.INbFarmWorkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 农事服务Controller
 *
 * @author lyx
 * @date 2025-01-11
 */
@RestController
@RequestMapping("/zhunong/work")
public class NbFarmWorkController extends BaseController
{
    @Autowired
    private INbFarmWorkService nbFarmWorkService;

    /**
     * 查询农事服务列表
     */
    @PreAuthorize("@ss.hasPermi('zhunong:work:list')")
    @GetMapping("/list")
    public TableDataInfo list(NbFarmWork nbFarmWork)
    {
        startPage();
        List<NbFarmWork> list = nbFarmWorkService.selectNbFarmWorkList(nbFarmWork);
        return getDataTable(list);
    }

    /**
     * 导出农事服务列表
     */
    @PreAuthorize("@ss.hasPermi('zhunong:work:export')")
    @Log(title = "农事服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NbFarmWork nbFarmWork)
    {
        List<NbFarmWork> list = nbFarmWorkService.selectNbFarmWorkList(nbFarmWork);
        ExcelUtil<NbFarmWork> util = new ExcelUtil<NbFarmWork>(NbFarmWork.class);
        util.exportExcel(response, list, "农事服务数据");
    }

    /**
     * 获取农事服务详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhunong:work:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nbFarmWorkService.selectNbFarmWorkById(id));
    }

    /**
     * 新增农事服务
     */
    @PreAuthorize("@ss.hasPermi('zhunong:work:add')")
    @Log(title = "农事服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NbFarmWork nbFarmWork)
    {
        return toAjax(nbFarmWorkService.insertNbFarmWork(nbFarmWork));
    }

    /**
     * 修改农事服务
     */
    @PreAuthorize("@ss.hasPermi('zhunong:work:edit')")
    @Log(title = "农事服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NbFarmWork nbFarmWork)
    {
        return toAjax(nbFarmWorkService.updateNbFarmWork(nbFarmWork));
    }

    /**
     * 删除农事服务
     */
    @PreAuthorize("@ss.hasPermi('zhunong:work:remove')")
    @Log(title = "农事服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nbFarmWorkService.deleteNbFarmWorkByIds(ids));
    }
//  批量上架
    @PostMapping("/pushFarmWork")
    public AjaxResult pushFarmWork(@RequestBody Long[] ids)
    {
        return toAjax(nbFarmWorkService.pushFarmWork(ids));
    }
//  批量下架
    @PostMapping("/unpushFarmWork")
    public AjaxResult unpushFarmWork(@RequestBody Long[] ids)
    {
        return toAjax(nbFarmWorkService.unpushFarmWork(ids));
    }

    @PostMapping("/recommendFarmWork")
    public AjaxResult recommendFarmWork(@RequestBody Long[] ids)
    {
        return toAjax(nbFarmWorkService.recommendFarmWork(ids));
    }

    @PostMapping("/unrecommendFarmWork")
    public AjaxResult unrecommendFarmWork(@RequestBody Long[] ids)
    {
        return toAjax(nbFarmWorkService.unrecommendFarmWork(ids));
    }
}
