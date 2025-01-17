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
import com.ruoyi.zhunong.domain.NbFarmMarket;
import com.ruoyi.zhunong.service.INbFarmMarketService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 农贸市场Controller
 *
 * @author jyn
 * @date 2024-10-11
 */
@Api("农市场接口")
@RestController
@RequestMapping("/zhunong/market")
public class NbFarmMarketController extends BaseController
{
    @Autowired
    private INbFarmMarketService nbFarmMarketService;

    /**
     * 查询农贸市场列表
     */
    @ApiOperation("查询农贸市场列表")
    @PreAuthorize("@ss.hasPermi('zhunong:market:list')")
    @GetMapping("/list")
    public TableDataInfo list(NbFarmMarket nbFarmMarket)
    {
        startPage();
        List<NbFarmMarket> list = nbFarmMarketService.selectNbFarmMarketList(nbFarmMarket);
        return getDataTable(list);
    }

    /**
     * 导出农贸市场列表
     */
    @ApiOperation("导出农贸市场列表")
    @PreAuthorize("@ss.hasPermi('zhunong:market:export')")
    @Log(title = "农贸市场", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NbFarmMarket nbFarmMarket)
    {
        List<NbFarmMarket> list = nbFarmMarketService.selectNbFarmMarketList(nbFarmMarket);
        ExcelUtil<NbFarmMarket> util = new ExcelUtil<NbFarmMarket>(NbFarmMarket.class);
        util.exportExcel(response, list, "农贸市场数据");
    }

    /**
     * 获取农贸市场详细信息
     */
    @ApiOperation("获取农贸市场详细信息")
    @PreAuthorize("@ss.hasPermi('zhunong:market:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nbFarmMarketService.selectNbFarmMarketById(id));
    }

    /**
     * 新增农贸市场
     */
    @ApiOperation("新增农贸市场")
    @PreAuthorize("@ss.hasPermi('zhunong:market:add')")
    @Log(title = "农贸市场", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NbFarmMarket nbFarmMarket)
    {
        return toAjax(nbFarmMarketService.insertNbFarmMarket(nbFarmMarket));
    }

    /**
     * 修改农贸市场
     */
    @ApiOperation(" 修改农贸市场")
    @PreAuthorize("@ss.hasPermi('zhunong:market:edit')")
    @Log(title = "农贸市场", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NbFarmMarket nbFarmMarket)
    {
        return toAjax(nbFarmMarketService.updateNbFarmMarket(nbFarmMarket));
    }

    /**
     * 删除农贸市场
     */
    @ApiOperation("删除农贸市场")
    @PreAuthorize("@ss.hasPermi('zhunong:market:remove')")
    @Log(title = "农贸市场", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nbFarmMarketService.deleteNbFarmMarketByIds(ids));
    }
}
