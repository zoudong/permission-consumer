package com.zoudong.consumer.controller;

import com.github.pagehelper.PageInfo;
import com.zoudong.consumer.integration.feign.UserServiceAPI;
import com.zoudong.permission.param.user.query.QuerySysUserParam;
import com.zoudong.permission.result.base.BaseResult;
import com.zoudong.permission.result.user.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.zoudong.permission.result.base.ResultUtil.fillSuccesData;

/**
 * @author zd
 * @description class
 * @date 2018/6/4 17:47
 */
@Slf4j
@RestController
public class SysUserController {
    @Autowired
    private UserServiceAPI userServiceAPI;


    @RequestMapping(value = "/permission/querySysUserByPage", method = RequestMethod.POST)
    public BaseResult<PageInfo<SysUserVO>> test(@Valid @RequestBody QuerySysUserParam querySysUserParam, HttpServletRequest request, HttpServletResponse response)throws Exception {
       /* try {*/
        log.info("开始分页查询全部用户:{}", querySysUserParam);
        BaseResult<PageInfo<SysUserVO>> result=userServiceAPI.test(querySysUserParam);
        log.info("结束分页查询全部用户:{}", result.getData());
        return fillSuccesData(result.getData());
       /* } catch (BusinessException e) {
            log.info("业务异常test:{}", e.getMessage());
            e.printStackTrace();
            return ResultUtil.fillErrorMsg(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            log.info("系统异常test:{}", e.getMessage());
            e.printStackTrace();
            return ResultUtil.error();
        }*/
    }




}
