package com.cxkj.blog.web.admin;

import com.cxkj.blog.pojo.Blog;
import com.cxkj.blog.pojo.Tag;
import com.cxkj.blog.pojo.User;
import com.cxkj.blog.service.BlogService;
import com.cxkj.blog.service.TagService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final  String INPUT = "/admin/blogs-input";
    private static final  String LIST = "/admin/blogs";
    private static final  String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogQuery, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,BlogQuery blogQuery,Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        return "/admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput (@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null){
            b = blogService.saveBlog(blog);
        }else {
            b = blogService.updateBlog(blog.getId(),blog);
        }
        if (b == null){
            attributes.addFlashAttribute("message","操作失败 ﾍ(;´Д｀ﾍ),管理员大大重新试下吧");
        }else {
            attributes.addFlashAttribute("message","操作成功 ≖‿≖✧ 快去发布新内容吧");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功,期待您发布更加美好的内容︿(￣︶￣)︿");
        return REDIRECT_LIST;
    }
}
