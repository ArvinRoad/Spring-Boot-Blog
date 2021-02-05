package com.cxkj.bolg.service;

import com.cxkj.bolg.dao.UserRepository;
import com.cxkj.bolg.pojo.User;
import com.cxkj.bolg.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

}
