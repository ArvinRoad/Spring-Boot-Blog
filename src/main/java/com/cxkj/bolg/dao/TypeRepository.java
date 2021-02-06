package com.cxkj.bolg.dao;

import com.cxkj.bolg.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Created by Arvin on 2021/2/6.
 */

public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
