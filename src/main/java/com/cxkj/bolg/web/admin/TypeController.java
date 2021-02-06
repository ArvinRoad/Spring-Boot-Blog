package com.cxkj.bolg.web.admin;

import com.cxkj.bolg.pojo.Type;
import com.cxkj.bolg.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 *  Created by Arvin on 2021/2/6.
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){

        model.addAttribute("page",typeService.listType(pageable));
        return "/admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "/admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type typename = typeService.getTypeByName(type.getName());
        if (typename != null){
            result.rejectValue("name","nameError","管理员大大，这个分类已经有了。((٩(//̀Д/́/)۶))做人要专一哦！");
        }
        if (result.hasErrors()){
            return "/admin/types-input";
        }
        Type t =  typeService.saveType(type);
        if (t == null){
            attributes.addFlashAttribute("message","操作失败 ﾍ(;´Д｀ﾍ),管理员大大重新试下吧");
        }else {
            attributes.addFlashAttribute("message","操作成功 ≖‿≖✧ 快去发布新内容吧");
        }
        return "redirect:../admin/types";
    }

}
