package cn.abelib.blog.util.http;

/**
 * Created by abel on 2018/1/7.
 * API响应类, 响应的API都以这种格式返回
 */
public class Response {
    // 元信息
    private Meta meta;
    // 响应体
    private Object body;

    public Response(Meta meta){
        this.meta = meta;
    }

    public Response(Meta meta, Object body){
        this.meta = meta;
        this.body = body;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
