package cn.abelib.blog.util.http;

import java.util.List;

/**
 * Created by abel on 2017/11/9.
 * Response工具类
 */
public class ResponseUtil {
    /**
     *  校验工具
     * @param object
     * @param meta
     * @return
     */
    public static Response validator(Object object, Meta meta){
        Response response;
        if (object == null){
            meta.setCode(HttpConstant.RESPONSE_EMPTY);
            meta.setMessage(HttpConstant.RESULT_EMPTY_STR);
            response = new Response(meta);
        }
        else
            response = new Response(meta, object);
        return response;
    }

    public static Response validator(List<?> list, Meta meta){
        Response response;
        if (list == null || list.size() < 1){
            meta.setCode(HttpConstant.RESPONSE_EMPTY);
            meta.setMessage(HttpConstant.RESULT_EMPTY_STR);
            response = new Response(meta);
        }
        else
            response = new Response(meta, list);
        return response;
    }
}
