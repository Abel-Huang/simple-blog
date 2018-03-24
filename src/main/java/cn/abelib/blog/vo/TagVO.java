package cn.abelib.blog.vo;

import java.io.Serializable;

/**
 * Created by abel on 2018/1/28.
 *  Tag的视图对象
 */
public class TagVO implements Serializable {
    private static final long serialVersionUID = -7900816828183997932L;

    private String name;
    private Long count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public TagVO(String name, Long count){
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "TagVO{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
