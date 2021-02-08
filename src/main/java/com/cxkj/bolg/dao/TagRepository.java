package com.cxkj.bolg.dao;

import com.cxkj.bolg.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Created by Arvin on 2021/2/7.
 */

public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);
}
