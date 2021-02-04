package com.cxkj.bolg.web;

import com.cxkj.bolg.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *  Created by Arvin on 2021/2/3.
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
            /*String bolg = null;
            if (bolg == null){
                throw  new NotFoundException("博客不存在");
            }*/
        return "index";
    }

    @GetMapping("/bolg")
    public String bolg(){
            /*String bolg = null;
            if (bolg == null){
                throw  new NotFoundException("博客不存在");
            }*/
        return "bolg";
    }

}
