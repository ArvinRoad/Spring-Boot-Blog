package com.cxkj.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Created by Arvin on 2021/12/16
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about(){

        return "about";
    }

}
