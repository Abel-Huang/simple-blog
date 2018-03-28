package cn.abelib.blog.pojo;


import java.io.Serializable;

/**
 * Created by abel on 2018/1/21.
 *  用于表示头像的实体类
 */
public class Avatar implements Serializable {
    private static final long serialVersionUID = -6365654321351414028L;

    private Long id;
    private Long userId;
    private String fileId;

    protected Avatar(){

    }

    public Avatar(Long userId, String fileId) {
        this.userId = userId;
        this.fileId = fileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", userId=" + userId +
                ", fileId='" + fileId + '\'' +
                '}';
    }
}
