package cn.abelib.blog.util.exception;


/**
 * Created by abel on 2017/11/8.
 *  注册异常类
 */
public class RegisterException extends Exception {
    public static String REGISTER_ERR = "The username has been registered";
    public RegisterException(String message){
        super(message);
    }
}
