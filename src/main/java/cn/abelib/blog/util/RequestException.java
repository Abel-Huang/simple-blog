package cn.abelib.blog.util;


/**
 * Created by abel on 2017/11/8.
 *  异常类
 */
public class RequestException extends RuntimeException {
    public RequestException(String message){
        super(message);
    }
}
