package com.buxiu.bootexample.service;

import java.util.List;
import com.buxiu.bootexample.po.UserPo;

/**
*
*  IUserService.java (Auto generated code)
*
*  @Author bubuxiu@gmail.com
*
**/

public interface IUserService {

    public List<UserPo> findUsersByPage(UserPo param) ;

    public UserPo getUserById(Integer userid) ;

    public UserPo getUserEx(UserPo param) ;

    public Integer addUser(UserPo param) ;

    public int updateUser(UserPo param) ;

    public int deleteUserById(Integer userid) ;

    public int deleteUserEx(UserPo param) ;

}
