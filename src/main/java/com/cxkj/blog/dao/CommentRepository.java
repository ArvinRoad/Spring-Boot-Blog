package com.cxkj.blog.dao;

import com.cxkj.blog.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *  Created by Arvin on 2021/2/13.
 */

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
