package com.cxkj.blog.dao;

import com.cxkj.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Created by Arvin on 2021/2/5.
 */

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
