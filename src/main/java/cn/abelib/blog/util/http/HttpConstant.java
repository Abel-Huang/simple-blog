package cn.abelib.blog.util.http;

/**
 * Created by abel on 2018/1/8.
 */
public class HttpConstant {
    // 响应正常
    public static final int RESPONSE_OK = 200;
    // 未查询到数据
    public static final int RESPONSE_EMPTY = 404;
    // 请求错误
    public static final int REQUEST_ERR = 400;
    // 服务错误
    public static final int SERVER_ERR = 500;

    public static final String RESPONSE_OK_STR = "success";

    public static final String REQUEST_ERR_STR = "request error";

    public static final String SERVER_ERR_STR = "service error";

    public static final String RESULT_EMPTY_STR = "query no data";

    public static final String FILE_EMPTY_STR = "file not found";
}
