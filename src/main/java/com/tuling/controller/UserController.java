package com.tuling.controller;

import com.tuling.modal.User;
import com.tuling.service.UserService;
import com.tuling.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 * Restful
 */
@Controller
@RequestMapping("/api/user")
@Api(tags = "用户",description = "详情")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ApiOperation(value = "登录",notes = "登录接口啊啊")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名"),@ApiImplicitParam(name = "password",value = "用户密码")
    })
    public String login(String name,String password, RedirectAttributes redirectAttributes){
        User user = new User("图灵","abcthm3b10");
        String username = user.getName();
        //模拟登录
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassWord());
        Subject currentUser  = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        }catch(UnknownAccountException uae){
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch(LockedAccountException lae){
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.error("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(currentUser .isAuthenticated()){
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            return "forward:/index";
        }else{
            token.clear();
            return "登录失败";
        }
    }

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "已退出";
    }


    /** restful api 增删改查*/
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true,dataTypeClass = User.class)
    @ResponseBody
    public String createUser(User user){
        user = new User();
        user.setCreateTime(new Date());
        user.setName("图灵");
        user.setPassWord(MD5Util.getMD5("abcthm3b10"));
        user.setState("未登录");
        userService.createUser(user);
        return "已创建";
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    @RequestMapping(method = RequestMethod.PUT)
    public String updateUser(User user){
        user.setLastLoginTime(new Date());
        userService.updateUser(user);
        return "已更新";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    public String delUser(@PathVariable("id")Integer id){
        userService.delUser(id);
        return "已删除";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    public User getUserById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        return user;
    }

    @ApiOperation(value = "用户列表")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserList(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
