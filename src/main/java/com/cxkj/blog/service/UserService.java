package com.cxkj.blog.service;

import com.cxkj.blog.pojo.User;

/**
 *  Created by Arvin on 2021/2/5.
 */

public interface UserService {

    User checkUser(String username,String password);
}
