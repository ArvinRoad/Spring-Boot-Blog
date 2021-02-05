package com.cxkj.bolg.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/bolgs")
    public String bolgs(){
        return "/admin/bolgs";
    }

}
