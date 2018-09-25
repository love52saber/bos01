package cn.itcast.service;

import cn.itcast.dao.UserMapper;
import cn.itcast.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> login(User user) {
        return userMapper.selectUserListByUserSelective(user);
    }

    @Override
    public boolean changePsw(User user) {
        return userMapper.updateByPrimaryKeySelective(user)<1?false:true;
    }


}
