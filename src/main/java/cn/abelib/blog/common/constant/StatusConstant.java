package cn.abelib.blog.common.constant;

import cn.abelib.blog.common.result.Meta;

/**
 * Created by abel on 2017/11/8.
 */
public class StatusConstant {
    public static final String CURRENT_USER = "currentUser";

    public interface Role{
        Integer ROLE_CUSTOMER = 0; //普通用户
        Integer ROLE_AUTHER = 1;  // 博客作者
    }

    // 原因短语
    private static final String RESPONSE_SUCCESS = "success";
    private static final String SERVER_ERROR = "服务端错误";
    private static final String ACCOUNT_EMPTY = "账号不存在";
    // 这里会将具体的错误信息附加到后面
    private static final String BIND_ERROR = "参数校验异常: %s";

    private static final String WRONG_PASS_ERRORS = "账号或者密码错误";
    private static final String UPDATE_PASS_ERRORS = "更新用户密码错误";
    private static final String UPDATE_USER_ERRORS = "更新用户信息错误";
    private static final String INSERT_USER_ERRORS = "注册用户失败";
    private static final String EXISTS_ACCOUNT_ERRORS = "账号已存在";
    private static final String USER_NOT_LOGIN_ERROR = "用户未登录";
    private static final String NOT_ADMIN_ERRORS = "用户不是管理员";

    private static final String CATEGORY_AL_EXISTS_ERRORS = "该分类已经存在";
    private static final String CATEGORY_NOT_EXISTS_ERRORS = "该分类不存在";

    private static final String COMMENT_NOT_EXISTS_ERRORS = "该评论不存在";

    //General 5001XX
    public static Meta GENERAL_SUCCESS = new Meta(0, RESPONSE_SUCCESS);
    public static Meta GENERAL_SERVER_ERROR = new Meta(500100, SERVER_ERROR);
    public static Meta PRAM_BIND_ERROR = new Meta(500101, BIND_ERROR);

    //Login 5002xx
    public static Meta ACCOUNT_NOT_EXISTS = new Meta(500200, ACCOUNT_EMPTY);
    public static Meta WRONG_PASS_ERROR = new Meta(500201, WRONG_PASS_ERRORS);
    public static Meta ACCOUNT_ALREADY_EXISTS = new Meta(500202, EXISTS_ACCOUNT_ERRORS);
    public static Meta USER_NOT_LOGIN = new Meta(500203, USER_NOT_LOGIN_ERROR);
    public static Meta UPDATE_PASS_ERROR = new Meta(500204, UPDATE_PASS_ERRORS);
    public static Meta UPDATE_USER_ERROR = new Meta(500205, UPDATE_USER_ERRORS);
    public static Meta NOT_ADMIN_ERROR = new Meta(500206, NOT_ADMIN_ERRORS);
    public static Meta INSERT_USER_ERROR = new Meta(500207, INSERT_USER_ERRORS);

    // Category
    public static Meta CATEGORY_AL_EXISTS = new Meta(500300, CATEGORY_AL_EXISTS_ERRORS);
    public static Meta CATEGORY_NOT_EXISTS = new Meta(500301, CATEGORY_NOT_EXISTS_ERRORS);

    //Comment
    public static Meta COMMENT_NOT_EXISTS = new Meta(500401, COMMENT_NOT_EXISTS_ERRORS);
}
