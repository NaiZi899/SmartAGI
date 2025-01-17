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
import com.ruoyi.zhunong.domain.NbKnowledgeCourse;
import com.ruoyi.zhunong.service.INbKnowledgeCourseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 知识课程Controller
 * 
 * @author yfx
 * @date 2025-01-14
 */
@RestController
@RequestMapping("/zhunong/course")
public class NbKnowledgeCourseController extends BaseController
{
    @Autowired
    private INbKnowledgeCourseService nbKnowledgeCourseService;

    /**
     * 查询知识课程列表
     */
    @PreAuthorize("@ss.hasPermi('zhunong:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(NbKnowledgeCourse nbKnowledgeCourse)
    {
        startPage();
        List<NbKnowledgeCourse> list = nbKnowledgeCourseService.selectNbKnowledgeCourseList(nbKnowledgeCourse);
        return getDataTable(list);
    }

    /**
     * 导出知识课程列表
     */
    @PreAuthorize("@ss.hasPermi('zhunong:course:export')")
    @Log(title = "知识课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NbKnowledgeCourse nbKnowledgeCourse)
    {
        List<NbKnowledgeCourse> list = nbKnowledgeCourseService.selectNbKnowledgeCourseList(nbKnowledgeCourse);
        ExcelUtil<NbKnowledgeCourse> util = new ExcelUtil<NbKnowledgeCourse>(NbKnowledgeCourse.class);
        util.exportExcel(response, list, "知识课程数据");
    }

    /**
     * 获取知识课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('zhunong:course:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nbKnowledgeCourseService.selectNbKnowledgeCourseById(id));
    }

    /**
     * 新增知识课程
     */
    @PreAuthorize("@ss.hasPermi('zhunong:course:add')")
    @Log(title = "知识课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NbKnowledgeCourse nbKnowledgeCourse)
    {
        return toAjax(nbKnowledgeCourseService.insertNbKnowledgeCourse(nbKnowledgeCourse));
    }

    /**
     * 修改知识课程
     */
    @PreAuthorize("@ss.hasPermi('zhunong:course:edit')")
    @Log(title = "知识课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NbKnowledgeCourse nbKnowledgeCourse)
    {
        return toAjax(nbKnowledgeCourseService.updateNbKnowledgeCourse(nbKnowledgeCourse));
    }

    /**
     * 删除知识课程
     */
    @PreAuthorize("@ss.hasPermi('zhunong:course:remove')")
    @Log(title = "知识课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nbKnowledgeCourseService.deleteNbKnowledgeCourseByIds(ids));
    }
}
