package com.tuling.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.tuling.common.utils.PageUitls;
import com.tuling.common.validator.Assert;
import com.tuling.common.validator.ValidatorUtil;
import com.tuling.common.validator.group.AddGroup;
import com.tuling.modal.User;
import com.tuling.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 * Restful
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户",description = "用户相关api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ApiOperation(value = "登录",notes = "登录接口啊啊")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名",paramType = "form"),
            @ApiImplicitParam(name = "password",value = "用户密码",paramType = "form")
    })
    public ResponseEntity<String> login(String name,String password, RedirectAttributes redirectAttributes){
        Assert.isBlank(name,"用户名不能为空");
        Assert.isBlank(password,"密码不能为空");
        //模拟登录
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        Subject currentUser  = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            return ResponseEntity.ok(name+"登录成功!");
        }catch(Exception e){
            return new ResponseEntity<String>("用户名或密码错误", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public ResponseEntity<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseEntity.ok("已退出");
    }


    /** restful api 增删改查*/
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user",dataTypeClass = User.class)
    public ResponseEntity<String> createUser(User user){
        ValidatorUtil.validateEntity(user, AddGroup.class);//校验用户实体字段，
        userService.createUser(user);
        return ResponseEntity.ok("已创建");
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody User user){
        user.setLastLoginTime(new Date());
        userService.updateUser(user);
        return ResponseEntity.ok("已更新");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int",paramType = "path")
    public ResponseEntity<String> delUser(@PathVariable("id")Integer id){
        userService.delUser(id);
        return ResponseEntity.ok("已删除");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int",paramType = "path")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "用户列表")
    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currPage",value = "当前页",paramType = "query"),
            @ApiImplicitParam(name = "sort",value = "排序 ASC 或 DESC",required = false,example = "asc/desc",paramType = "query"),
            @ApiImplicitParam(name = "orderBy",value = "排序字段",required = false,example = "createdTime",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示条数",required = false,example = "createdTime",paramType = "query"),
            //@ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "long",paramType = "query"),如果时间类型则可以打开
            //@ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "filter",value = "通用表过滤器。发送JSON键/值对，如<code>{“key”:“value”}</code>。", paramType = "query",dataTypeClass = JSON.class)

    })
    public ResponseEntity<PageUitls<User>> getUserList(@RequestParam @ApiParam(hidden = true) Map<String,String> params){
        Page<User> page =  userService.getUsers(params);
        return ResponseEntity.ok(new PageUitls<User>(page));
    }
}
