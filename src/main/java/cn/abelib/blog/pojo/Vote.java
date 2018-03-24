package cn.abelib.blog.pojo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by abel on  2017/11/18
 * Vote的实体类  点赞
 */
@Entity
public class Vote implements Serializable{
    private static final long serialVersionUID = -6365789820629456422L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long blogId;

    private Integer vote;

    @Column(nullable = false, name = "createtime")
    @CreationTimestamp
    private Timestamp createTime;

    protected Vote(){

    }

    public Vote(Long userId, Long blogId){
        this.blogId = blogId;
        this.userId = userId;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}
