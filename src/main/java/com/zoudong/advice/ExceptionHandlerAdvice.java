package com.zoudong.advice;

import com.zoudong.permission.exception.BusinessException;
import com.zoudong.permission.result.base.ResultUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
    @ExceptionHandler(value = Exception.class)
    public Object ExceptionHandler(HttpServletRequest request,
                                                Exception e) throws Exception {

        if (e instanceof FeignException) {
            FeignException feignException = (FeignException) e;
            if(feignException.status()==401) {
                return ResultUtil.fillErrorMsg(String.valueOf(feignException.status()), "认证失败");
            }
            if(feignException.status()==500) {
                return ResultUtil.fillErrorMsg(String.valueOf(feignException.status()), "内部错误");
            }
        }



        if (e instanceof UnauthenticatedException) {
            return ResultUtil.fillErrorMsg("token_error", "token错误");
        }

        if (e instanceof UnauthorizedException) {
            return ResultUtil.fillErrorMsg("permission_error", "用户无权限");
        }

        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            log.info("业务异常:{}", e.getMessage());
            e.printStackTrace();
            return ResultUtil.fillErrorMsg(businessException.getErrorCode(), businessException.getMessage());
        }

        //POST请求参数异常处理
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
                log.info("参数异常:" + error.getField() + error.getDefaultMessage());
                return ResultUtil.fillErrorMsg("param_error", error.getDefaultMessage());
            }
            return ResultUtil.error();
        }
        //GET请求参数异常处理
        if (e instanceof MissingServletRequestParameterException) {
            return ResultUtil.fillErrorMsg("param_error",
                    "参数异常:" + ((MissingServletRequestParameterException) e).getParameterName() + "不能为空！");
        }
        e.printStackTrace();
        return ResultUtil.error();
    }
}
