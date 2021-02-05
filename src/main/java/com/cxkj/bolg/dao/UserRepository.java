package com.cxkj.bolg.dao;

import com.cxkj.bolg.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Created by Arvin on 2021/2/5.
 */

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
