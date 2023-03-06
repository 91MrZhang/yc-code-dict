package com.yc.code.dict.jsp.controller;

import com.yc.code.dict.jsp.model.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class JspController {

    private final List<UserVO> userList = new ArrayList<>();

    @RequestMapping("/list")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",userList);
        mv.setViewName("list");
        return mv;
    }

    /**
     * 转发写法
     */
    @RequestMapping("/saveA")
    public ModelAndView saveA(UserVO vo) {
        userList.add(vo);
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",userList);
        mv.setViewName("list");
        return mv;
    }

    /**
     * 重定向写法
     */
    @RequestMapping("/saveB")
    public String saveB(UserVO vo) {
        userList.add(vo);
        return "redirect:/view/list";
    }

}
