package com.buxiu.bootexample.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buxiu.bootexample.interceptor.CheckToken;
import com.buxiu.bootexample.interceptor.PageInfo;
import com.buxiu.bootexample.controller.base.BaseCtrl;
import com.buxiu.bootexample.controller.model.RSResult;
import com.buxiu.bootexample.po.UserPo;
import com.buxiu.bootexample.service.common.BizException;
import com.buxiu.bootexample.service.impl.UserServiceImpl;

/**
*
*  UserController.java (Auto generated code)
*
*  @Author bubuxiu@gmail.com
*
**/

// for postman test
@CheckToken(false)
@RestController 
@RequestMapping("/user")
public class UserController extends BaseCtrl {

    @Autowired
    private UserServiceImpl userService;

    /** 
    *  查询列表 (Auto generated code)  
    *
    **/
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RSResult list(@RequestBody UserPo param) {
        RSResult result = new RSResult();
        try {
            List<UserPo> list =  userService.findUsersByPage(param); 
            PageInfo<UserPo> page = (PageInfo<UserPo>)list;

            result.setResult(0);
            result.setTotal(page.getTotal());
            result.setData(page.getList());
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode());
        }
        return result;
    }

    /** 
    *  根据主键查询详情 (Auto generated code)  
    *
    **/
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public RSResult detail(@RequestBody UserPo param) {
        RSResult result = new RSResult();
        try { 
            UserPo obj = userService.getUserById(param.getUserid());
            result.setResult(0);
            result.setData(obj);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode());
        }
        return result;
    }

    /** 
    *  添加记录 (Auto generated code)  
    *
    **/
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public RSResult add(@RequestBody UserPo param) { 
        RSResult result = new RSResult();
        try {
            Integer id = userService.addUser(param);
            
            result.setResult(0);
            result.setData(id);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode());
        }
        return result;
    }

    /** 
    *  更新记录 (Auto generated code)  
    *
    **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RSResult update(@RequestBody UserPo param) {
        RSResult result = new RSResult();
        try { 
            userService.updateUser(param);
            result.setResult(0);
            result.setMessage("修改成功！");
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode());
        }
        return result;
    }

    /** 
    *  根据主键删除记录 (Auto generated code)  
    *
    **/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RSResult delete(@RequestBody UserPo param) {
        RSResult result = new RSResult();
        try { 
            userService.deleteUserById(param.getUserid());
            result.setResult(0);
            result.setMessage("删除成功！");
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode());
        }
        return result;
    }
}