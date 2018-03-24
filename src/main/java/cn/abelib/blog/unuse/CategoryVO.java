package cn.abelib.blog.unuse;

import cn.abelib.blog.pojo.Category;

import java.io.Serializable;

/**
 * Created by abel on 2018/1/21.
 */
public class CategoryVO implements Serializable {
    private static final long serialVersionUID = 1514800752363238821L;

    private String username;
    private Category category;

    public CategoryVO(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "username='" + username + '\'' +
                ", category=" + category +
                '}';
    }
}
