package cn.abelib.blog.domain;



import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * Created by abel on 2018/1/3.
 */
@Entity
public class User implements Serializable{
    private static final long serialVersionUID = -1705077149342707141L;
    // 设置id为自增主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    @NotEmpty
    @Size(max = 50)
    @Email(message = "邮箱格式不正确")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotEmpty(message = "账号不能为空")
    @Size(max = 20)
    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 100)
    @Column(length = 100)
    private String password;

    @Column(length = 200)
    private String avatar;


    // 无参的构造函数, 防止直接使用
    protected User(){

    }

    public User(Long id, String name, String email, String username, String password, String avatar){
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s', email='%s', username=%s, password='%s', avatar='%s']", id, name, email, username, password, avatar);
    }
}
