package com.cxkj.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Created by Arvin on 2021/2/3.
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
            /*String blog = null;
            if (blog == null){
                throw  new NotFoundException("博客不存在");
            }*/
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
            /*String blog = null;
            if (blog == null){
                throw  new NotFoundException("博客不存在");
            }*/
        return "blog";
    }

}
