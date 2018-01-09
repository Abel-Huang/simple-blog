package cn.abelib.blog.util.http;




/**
 * Created by abel on 2018/1/3.
 * 元信息类, 用于构建Response
 */
public class Meta{
    // 状态码
    private int code;
    // 原因短语
    private String message;

    public Meta(int code){
        this.code = code;
    }

    public Meta(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}