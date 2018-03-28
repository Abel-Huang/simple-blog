package cn.abelib.blog.pojo;



import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * Created by abel on  2017/11/4.
 * User 实体类  用户
 */
@Entity
public class User implements Serializable{
    private static final long serialVersionUID = 1968059065743692868L;

    // 设置id为自增主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "昵称不能为空")
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20)
    private String nickname;

    @NotEmpty
    @Size(max = 50)
    @Email(message = "邮箱格式不正确")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotEmpty(message = "账号不能为空")
    @Size(max = 20)
    @Column(nullable = false, length = 20, unique = true, name = "username")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 100)
    @Column(length = 100)
    private String password;

    private Long avatarId;


    // 无参的构造函数, 防止直接使用
    protected User(){

    }

    public User(String nickname, String email, String username, String password, Long avatarId){
        this.nickname = nickname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.avatarId = avatarId;
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

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatarId='" + avatarId + '\'' +
                '}';
    }
}
