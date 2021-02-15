package com.cxkj.blog.web;

import com.cxkj.blog.pojo.Type;
import com.cxkj.blog.service.BlogService;
import com.cxkj.blog.service.TypeService;
import com.cxkj.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *  Created by Arvin on 2021/12/16
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,@PathVariable Long id, Model model){
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeID(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
