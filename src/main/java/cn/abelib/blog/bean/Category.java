package cn.abelib.blog.bean;

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
    private String name;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    protected Category(){

    }

    public Category(String name, User user){
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("Category[id=%d, name='%s', user_id='%s']", id, name, user.getId());
    }
}
