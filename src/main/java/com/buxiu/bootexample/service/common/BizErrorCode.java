package com.buxiu.bootexample.service.common;

public class BizErrorCode {
    // TOKEN
    public static Integer EX_TOKEN_MISS = 1;
    public static Integer EX_TOKEN_IPCHANGE = 2;
    public static Integer EX_TOKEN_ERROR = 3;

    // 数据库操作相关
    public final static Integer EX_DATABASE_TRANSACTION_FAIL = 100; // 事务失败
    public final static Integer EX_DATABASE_RECORD_NOTEXIST = 101;// 查询记录不存在 
    public final static Integer EX_DATABASE_ADD_FAIL = 102;// 增删改失败
    public final static Integer EX_DATABASE_UPDATE_FAIL = 103;
    public final static Integer EX_DATABASE_DELETE_FAIL = 104;
    public final static Integer EX_DATABASE_NAME_EXIST = 105; // 名称已经存在
    public final static Integer EX_DATABASE_KEY_INUSE = 106; // 关联主键正在使用
    public final static Integer EX_DATABASE_NAME_CONFLICT = 107; // 名字冲突
    public final static Integer EX_DATABASE_KEY_INVALID = 108; // 关联主键失效
    public final static Integer EX_DATABASE_UPLOAD_FAIL = 109; // 上传失败
    public final static Integer EX_DATABASE_USED = 110;//已被使用
 
    //请求参数问题
    public final static Integer EX_REQUEST_INVALID_PARAM = 500;//请求参数缺失
    
    /*       自定义错误码从这里开始     */ 
     
	 
}
