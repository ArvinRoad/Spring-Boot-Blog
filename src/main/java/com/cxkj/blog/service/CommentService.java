package com.cxkj.blog.service;

import com.cxkj.blog.pojo.Comment;

import java.util.List;

/**
 *  Created by Arvin on 2021/2/13.
 */

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
