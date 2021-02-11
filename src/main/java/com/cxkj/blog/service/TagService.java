package com.cxkj.blog.service;

import com.cxkj.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *  Created by Arvin on 2021/2/7.
 */

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);
}
