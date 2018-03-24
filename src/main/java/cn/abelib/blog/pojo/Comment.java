package cn.abelib.blog.pojo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by abel on 2017/11/18.
 * Comment的实体类  评论
 */
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = 5055761594833008098L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "评论字段不能为空")
    @Size(min = 2, max = 500)
    @Column(nullable = false)
    private String content;

    private Long userId;

    private Long blogId;

    @Column(nullable = false, name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    protected Comment(){

    }

    public Comment(Long userId, Long blogId, String content){
        this.blogId = blogId;
        this.userId = userId;
        this.content = content;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", blogId=" + blogId +
                ", createTime=" + createTime +
                '}';
    }
}
