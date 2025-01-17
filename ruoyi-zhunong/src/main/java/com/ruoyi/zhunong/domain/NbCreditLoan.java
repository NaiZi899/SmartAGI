package com.ruoyi.zhunong.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 信贷信息对象 nb_credit_loan
 *
 * @author lzl
 * @date 2025-01-10
 */
@Data
public class NbCreditLoan extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 文章标题
     */
    @Excel(name = "文章标题")
    private String title;

    /**
     * 作者/来源
     */
    @Excel(name = "作者/来源")
    private String author;

    /**
     * 文章摘要
     */
    @Excel(name = "文章摘要")
    private String resume;

    /**
     * 正文内容
     */
    @Excel(name = "正文内容")
    private String content;

    /**
     * 图片文件id
     */
    @Excel(name = "图片文件id")
    private String image;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /**
     * 发布状态：1.已发布；2.未发布
     */
    @Excel(name = "发布状态：1.已发布；2.未发布")
    private Long publishStatus;

    /**
     * 是否推荐：1.推荐；2.不推荐
     */
    @Excel(name = "是否推荐：1.推荐；2.不推荐")
    private Long recommend;

    /**
     * 浏览数量
     */
    @Excel(name = "浏览数量")
    private Long browseNum;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("author", getAuthor())
                .append("resume", getResume())
                .append("content", getContent())
                .append("image", getImage())
                .append("publishTime", getPublishTime())
                .append("publishStatus", getPublishStatus())
                .append("recommend", getRecommend())
                .append("browseNum", getBrowseNum())
                .append("createdTime", getCreatedTime())
                .append("updatedTime", getUpdatedTime())
                .toString();
    }
}
