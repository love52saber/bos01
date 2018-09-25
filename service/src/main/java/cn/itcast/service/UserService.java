package cn.itcast.service;

import cn.itcast.pojo.User;

import java.util.List;

public interface UserService {
    List<User> login(User User);


    boolean changePsw(User user);
}
