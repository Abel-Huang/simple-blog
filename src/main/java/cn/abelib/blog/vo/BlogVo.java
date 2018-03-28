package cn.abelib.blog.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by abel on 2018/1/27.
 *  Blog的视图对象
 */
public class BlogVo {
    private String title;
    private String summary;
    private String content;
    private Long userId;
    private Long categoryId;
    private String tags;

    public boolean isNotFull(){
        return StringUtils.isNotBlank(title)
                &&  StringUtils.isNotBlank(summary)
                && StringUtils.isNotBlank(content)
                && StringUtils.isNotBlank(tags)
                && userId != null
                && categoryId != null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
