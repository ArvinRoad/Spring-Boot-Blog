package com.cxkj.blog.dao;

import com.cxkj.blog.pojo.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *  Created by Arvin on 2021/2/8.
 */

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);
}
