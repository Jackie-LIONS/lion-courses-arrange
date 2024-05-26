package com.lion.coursesarrange.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lion.coursesarrange.model.dto.teacher.TeacherLoginRequest;
import com.lion.coursesarrange.model.dto.user.UserLoginRequest;
import com.lion.coursesarrange.model.enums.CodeEnum;
import com.lion.coursesarrange.model.pojo.Teacher;
import com.lion.coursesarrange.model.pojo.User;
import com.lion.coursesarrange.model.result.BaseResult;
import com.lion.coursesarrange.model.result.BusException;
import com.lion.coursesarrange.service.TeacherService;
import com.lion.coursesarrange.utils.CheckCodeUtil;
import com.lion.coursesarrange.utils.JWTUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("/login")
    public BaseResult<String> login(@RequestBody TeacherLoginRequest teacherLoginRequest, HttpServletRequest request){
        // 获取输入的验证码
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCodeGen");

        if (!checkCode.equalsIgnoreCase(teacherLoginRequest.getCheckCode())){
            throw new BusException(CodeEnum.CHECK_CODE_ERROR);
        }
        String token = teacherService.login(teacherLoginRequest.getTeacherName(), teacherLoginRequest.getPassword());
        return BaseResult.ok(token);
    }

    /**
     * 验证码生成
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //生成验证码并返回
        String checkCodeGen = CheckCodeUtil.generateVerifyCode(4);

        //存入session
        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen",checkCodeGen);

        System.out.println(session.getId());
        //输出图片
        ServletOutputStream os = response.getOutputStream();
        CheckCodeUtil.outputImage(100,40,os,checkCodeGen);
    }

    @PostMapping("/register")
    public BaseResult register(@RequestBody Teacher teacher){
        teacherService.register(teacher);
        return BaseResult.ok();
    }

    /**
     * 更新用户信息
     * @param teacher
     * @return
     */
    @PostMapping("/update")
    public BaseResult update(@RequestBody Teacher teacher){
        teacherService.update(teacher);
        return BaseResult.ok();
    }

    /**
     * 条件查询
     * @param teacher
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/selectByCondition")
    public BaseResult<Page<Teacher>> selectByCondition(@RequestBody  Teacher teacher,
                                                    @RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "5") Integer size){
        Page<Teacher> teacherPage = teacherService.selectByCondition(teacher, page, size);
        return BaseResult.ok(teacherPage);
    }

    /**
     * 登录状态获取
     * @param token
     * @return
     */
    @GetMapping("/getLoginTeacher")
    public BaseResult<Teacher> checkLogin(@RequestBody String token){
        Integer id = JWTUtil.getId(token);
        Teacher teacher = teacherService.selectById(id);
        return BaseResult.ok(teacher);
    }

}
