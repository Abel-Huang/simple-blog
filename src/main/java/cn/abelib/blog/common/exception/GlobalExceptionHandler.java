package cn.abelib.blog.common.exception;

import cn.abelib.blog.common.constant.StatusConstant;
import cn.abelib.blog.common.result.Response;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by abel on 2017/11/7.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Response<String> exceptionHandler(HttpServletRequest request, Exception e){
        if (e instanceof GlobalException){
            GlobalException ge = (GlobalException) e;
            return Response.failed(ge.getMeta());
        }
        else if (e instanceof BindException){
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Response.failed(StatusConstant.PRAM_BIND_ERROR.fillArgs(msg));
        }
        return Response.failed(StatusConstant.GENERAL_SERVER_ERROR);
    }
}
