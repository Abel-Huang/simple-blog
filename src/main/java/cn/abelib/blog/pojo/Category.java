package cn.abelib.blog.pojo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by abel on 2017/11/18.
 * Category的实体类  分类
 */
@Entity
public class Category implements Serializable{
    private static final long serialVersionUID = 4818763024316534110L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "分类名称字段不能为空")
    @Size(min = 2, max = 30)
    @Column(nullable = false)
    private String content;

    private Long userId;

    protected Category(){

    }

    public Category(String content, Long userId){
        this.content = content;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                '}';
    }
}
