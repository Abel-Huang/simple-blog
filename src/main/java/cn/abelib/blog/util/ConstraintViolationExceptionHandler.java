package cn.abelib.blog.util;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abel on 2018/1/7.
 */
public class ConstraintViolationExceptionHandler {
    public static String getMessage(ConstraintViolationException e){
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()){
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList.toArray(), ";");
        return messages;
    }
}
