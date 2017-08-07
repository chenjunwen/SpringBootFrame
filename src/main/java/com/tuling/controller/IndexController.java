package com.tuling.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/8/3.
 */
@Controller
@Api(tags = "首页",description ="App相关操作接口定义类")
public class IndexController {
    @ApiOperation(value = "")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "swagger/index.html";
    }
}
