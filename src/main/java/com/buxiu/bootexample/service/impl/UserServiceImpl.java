package com.buxiu.bootexample.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.buxiu.bootexample.service.IUserService;
import com.buxiu.bootexample.service.common.BizErrorCode;
import com.buxiu.bootexample.service.common.BizException;
import com.buxiu.bootexample.mapper.UserMapper;
import com.buxiu.bootexample.po.UserPo;
/**
*
*  UserServiceImpl.java (Auto generated code)
*
*  @Author bubuxiu@gmail.com
*
**/


@Component("userService")
public class UserServiceImpl implements IUserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Qualifier("userMapper")
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserPo> findUsersByPage(UserPo param) {
        log.debug("findUsersByPage starting...");
        List<UserPo> users = null;
        try {
            users = userMapper.findUsersByPage(param);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_DATABASE_TRANSACTION_FAIL);
        }
        log.debug("findUsersByPage end");
        return users;
    }

    @Override
    public UserPo getUserById(Integer userid) {
        log.debug("getUser starting...");
        UserPo user = null;
        try {
            user = userMapper.getUserById(userid);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_DATABASE_TRANSACTION_FAIL);
        }
        log.debug("getUser end");
        return user;
    }

    @Override
    public UserPo getUserEx(UserPo param) {
        log.debug("getUserEx starting...");
        UserPo user = null;
        try {
            user = userMapper.getUserEx(param);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_DATABASE_TRANSACTION_FAIL);
        }
        log.debug("getUserEx end");
        return user;
    }

    @Override
    public Integer addUser(UserPo param) {
        log.debug("addUser starting...");
        Integer retval = 0;
        try {
            retval = userMapper.addUser(param);
            
            if (retval == 0) {
                throw new BizException(BizErrorCode.EX_DATABASE_ADD_FAIL);
            }
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_DATABASE_TRANSACTION_FAIL);
        }
        log.debug("addUser end");
        return param.getUserid();
    }

    @Override
    public int updateUser(UserPo param) {
        log.debug("updateUser starting...");
        int retval = 0;
        try {
            retval = userMapper.updateUser(param);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_DATABASE_TRANSACTION_FAIL);
        }
        if (retval == 0) {
            throw new BizException(BizErrorCode.EX_DATABASE_UPDATE_FAIL);
        }
        log.debug("updateUser end");
        return retval;
    }

    @Override
    public int deleteUserById(Integer userid) {
        log.debug("deleteUser starting...");
        int retval = 0;
        try {
            retval = userMapper.deleteUserById(userid);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_DATABASE_TRANSACTION_FAIL);
        }
        log.debug("deleteUser end");
        return retval;
    }

    @Override
    public int deleteUserEx(UserPo param) {
        log.debug("deleteUserEx starting...");
        int retval = 0;
        try {
            retval = userMapper.deleteUserEx(param);
        } catch (SQLException ex) {
            log.error("exception:", ex);
            throw new BizException(BizErrorCode.EX_DATABASE_TRANSACTION_FAIL);
        }
        log.debug("deleteUserEx end");
        return retval;
    }
}