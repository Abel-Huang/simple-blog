package cn.abelib.blog.pojo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by abel on 2017/10/28.
 * Blog的实体类  博客
 */
@Entity
@Document(indexName = "blog", type = "blog")
public class Blog implements Serializable{
    private static final long serialVersionUID = -859673131481817067L;

    // 主键, 作为文档的id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "标题不能为空")
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String title;

    @NotEmpty(message = "摘要不能为空")
    @Size(min = 2, max = 300)
    @Column(nullable = false)
    private String summary;

    @Basic(fetch = FetchType.LAZY)
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false)
    private String content;

    private Long userId;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private Timestamp createTime;

    // 阅读量
    @Column(name = "read_size")
    private Integer readSize = 0;

    // 评论数
    @Column(name = "comment_size")
    private Integer commentSize = 0;

    // 点赞数
    @Column(name = "vote_size")
    private Integer voteSize = 0;

    // 标签
    @Column(name = "tags", length = 100)
    private String tags;

    private Long categoryId;

    protected Blog(){ // JPA要求

    }

    public Blog(String title, String summary, String content){
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Blog(String title, String summary){
        this.title = title;
        this.summary = summary;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
       return id;
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

    public Blog simpleBlog(){
        return new Blog(this.getTitle(), this.getSummary());
    }
}
