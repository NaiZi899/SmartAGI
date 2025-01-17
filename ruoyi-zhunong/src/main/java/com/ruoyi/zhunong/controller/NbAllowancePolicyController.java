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
import com.ruoyi.zhunong.domain.NbAllowancePolicy;
import com.ruoyi.zhunong.service.INbAllowancePolicyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 补贴政策Controller
 * 
 * @author zbb
 * @date 2025-01-14
 */
@RestController
@RequestMapping("/zhunong/policy")
public class NbAllowancePolicyController extends BaseController
{
    @Autowired
    private INbAllowancePolicyService nbAllowancePolicyService;

    /**
     * 查询补贴政策列表
     */
    @PreAuthorize("@ss.hasPermi('zhunong:policy:list')")
    @GetMapping("/list")
    public TableDataInfo list(NbAllowancePolicy nbAllowancePolicy)
    {
        startPage();
        List<NbAllowancePolicy> list = nbAllowancePolicyService.selectNbAllowancePolicyList(nbAllowancePolicy);
        return getDataTable(list);
    }

    /**
     * 导出补贴政策列表
     */
    @PreAuthorize("@ss.hasPermi('zhunong:policy:export')")
    @Log(title = "补贴政策", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NbAllowancePolicy nbAllowancePolicy)
    {
        List<NbAllowancePolicy> list = nbAllowancePolicyService.selectNbAllowancePolicyList(nbAllowancePolicy);
        ExcelUtil<NbAllowancePolicy> util = new ExcelUtil<NbAllowancePolicy>(NbAllowancePolicy.class);
        util.exportExcel(response, list, "补贴政策数据");
    }

    /**
     * 获取补贴政策详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhunong:policy:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nbAllowancePolicyService.selectNbAllowancePolicyById(id));
    }

    /**
     * 新增补贴政策
     */
    @PreAuthorize("@ss.hasPermi('zhunong:policy:add')")
    @Log(title = "补贴政策", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NbAllowancePolicy nbAllowancePolicy)
    {
        return toAjax(nbAllowancePolicyService.insertNbAllowancePolicy(nbAllowancePolicy));
    }

    /**
     * 修改补贴政策
     */
    @PreAuthorize("@ss.hasPermi('zhunong:policy:edit')")
    @Log(title = "补贴政策", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NbAllowancePolicy nbAllowancePolicy)
    {
        return toAjax(nbAllowancePolicyService.updateNbAllowancePolicy(nbAllowancePolicy));
    }

    /**
     * 删除补贴政策
     */
    @PreAuthorize("@ss.hasPermi('zhunong:policy:remove')")
    @Log(title = "补贴政策", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nbAllowancePolicyService.deleteNbAllowancePolicyByIds(ids));
    }
}
