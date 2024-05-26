package com.lion.coursesarrange.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.dto.user.UserLoginRequest;
import com.lion.coursesarrange.model.enums.CodeEnum;
import com.lion.coursesarrange.model.pojo.User;
import com.lion.coursesarrange.model.result.BaseResult;
import com.lion.coursesarrange.model.result.BusException;
import com.lion.coursesarrange.service.UserService;
import com.lion.coursesarrange.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseResult<String> login(@RequestBody UserLoginRequest userLoginRequest){
//        todo 验证码功能
        if (!"1234".equalsIgnoreCase(userLoginRequest.getCheckCode())){
            throw new BusException(CodeEnum.CHECK_CODE_ERROR);
        }
        String token = userService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        return BaseResult.ok(token);
    }

    @PostMapping("/register")
    public BaseResult register(@RequestBody User user){
        userService.register(user);
        return BaseResult.ok();
    }

    /**
     *批量禁用
     * @param ids
     * @return
     */
    @PostMapping("/disable")
    public BaseResult disableByIds(@RequestBody int[] ids){
        userService.disableByIds(ids);
        return BaseResult.ok();
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/update")
    public BaseResult update(@RequestBody User user){
        userService.update(user);
        return BaseResult.ok();
    }

    /**
     * 条件查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/selectByCondition")
    public BaseResult<Page<User>> selectByCondition(@RequestBody  User user,
                                                    @RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "5") Integer size){
        Page<User> userPage = userService.selectByCondition(user, page, size);
        return BaseResult.ok(userPage);
    }

    /**
     * 登录状态获取
     * @param token
     * @return
     */
    @GetMapping("/getLoginUser")
    public BaseResult<User> checkLogin(@RequestBody String token){
        Integer id = JWTUtil.getId(token);
        User user = userService.selectById(id);
        return BaseResult.ok(user);
    }

}
