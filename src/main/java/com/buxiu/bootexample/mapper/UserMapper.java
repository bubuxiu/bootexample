package com.buxiu.bootexample.mapper;

import java.sql.SQLException;
import java.util.List;
import com.buxiu.bootexample.po.UserPo;

/**
*
*  UserMapper.java (Auto generated code)
*
*  @Author bubuxiu@gmail.com
*
**/

public interface UserMapper extends ISqlMapper {

    public int countUsers(UserPo param) throws SQLException;

    public List<UserPo> findUsersByPage(UserPo param) throws SQLException;

    public UserPo getUserById(Integer userid) throws SQLException;

    public UserPo getUserEx(UserPo param) throws SQLException;

    public Integer addUser(UserPo param) throws SQLException;

    public int updateUser(UserPo param) throws SQLException;

    public int deleteUserById(Integer userid) throws SQLException;

    public int deleteUserEx(UserPo param) throws SQLException;

}
