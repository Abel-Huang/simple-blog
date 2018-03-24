package cn.abelib.blog.common.exception;


import cn.abelib.blog.common.result.Meta;

/**
 * Created by abel on 2017/11/8.
 *  注册异常类
 */
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 4682381314363710782L;

    private Meta meta;
    public GlobalException(Meta meta) {
        super(meta.toString());
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }
}
