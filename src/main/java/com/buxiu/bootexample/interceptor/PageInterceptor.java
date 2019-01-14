package com.buxiu.bootexample.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor; 
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.buxiu.bootexample.po.base.PageBean;
import com.buxiu.bootexample.utils.MysqlParser;

import net.sf.jsqlparser.JSQLParserException; 

/**
 * 这个是mybatis的拦截器，不能在spring拦截器配置里配置，需要在mybatis plugin里配置
 * @author bubuxiu@gmail.com
 * */

@Intercepts(@Signature(type = Executor.class, method = "query", 
	args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PageInterceptor implements Interceptor {
	//private static final Log logger = LogFactory.getLog(PageInterceptor.class);
	
	private String databaseType;//数据库类型，不同的数据库有不同的分页方法  
    private static String defaultPageSqlId = "ByPage"; // 需要拦截的ID(正则匹配) 

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Object intercept(Invocation invocation) throws Throwable { 
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        // 重写select id 以ByPage结尾的的sql 
        if (ms.getId().endsWith(defaultPageSqlId)) { 
        	// 在调用对应的Mapper映射语句时所传入的参数对象 
        	Object parameterObject = invocation.getArgs()[1];
        	if (parameterObject == null) {
                throw new RuntimeException("parameterObject is null!");
            }
        	
        	// 获取原始sql语句 
            BoundSql boundSql = ms.getBoundSql(parameterObject); 
            if (!(parameterObject instanceof PageBean)) {
            	throw new RuntimeException("parameterObject is subclass of PageBean!");
            }
            
            // 创建新的mappedStatement
            MappedStatement newms = newMappedStatement(ms, new BoundSqlSqlSource(boundSql));
            //将参数中的MappedStatement替换为新的qs，防止并发异常
            invocation.getArgs()[0] = newms;

            MetaObject msObject = SystemMetaObject.forObject(newms);
            String sql = (String) msObject.getValue("sqlSource.boundSql.sql");
            //求count - 重写sql
            msObject.setValue("sqlSource.boundSql.sql", getCoundSql(sql));

            //分页信息
            PageBean pb = (PageBean)parameterObject;
            if(pb.getPageNum() == null || pb.getPageSize() == null) {
            	pb.setPageNum(1);
            	pb.setPageSize(10);
            } 
            PageInfo<?> page = new PageInfo(pb.getPageNum(),pb.getPageSize());
            //查询总数
            Object result = invocation.proceed();
            int totalCount = Integer.parseInt(((List<?>) result).get(0).toString());
            page.setTotal(totalCount); 

            //分页sql - 重写sql
            msObject.setValue("sqlSource.boundSql.sql", getPageSql(page,sql));
            //恢复类型
            msObject.setValue("resultMaps", ms.getResultMaps());
            //执行分页查询
            result = invocation.proceed();
            //得到处理结果
            page.setList((List) result);
            //返回结果
            return page;
        }else {
        
	        // 将执行权交给下一个拦截器
	        return invocation.proceed();
        }
    }
 

    @Override
    public Object plugin(Object target) {
        // 当目标类是Executor类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }
 
    /**
     * 设置注册拦截器时设定的属性
     */
    @Override
    public void setProperties(Properties properties) {
       this.databaseType = properties.getProperty("databaseType");
    }
    
    /**
     * 获取总数sql - 如果要支持其他数据库，修改这里就可以
     * @param sql 
     */
    private String getCoundSql(String oldSql) {
    	String sql = oldSql;
		try {
			// 去除order by语句，提升性能
			sql = MysqlParser.class.newInstance().removeOrderBy(oldSql);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "select count(0) from (" + sql + ")  tt";
    }
    /**
     * 获取Mysql数据库的分页查询语句
     * @param page 分页对象
     * @param sqlBuffer 包含原sql语句的StringBuffer对象
     * @return Mysql数据库分页语句
     */
    private String getMysqlPageSql(PageInfo<?>  page, String sql) {
       //计算第一条记录的位置，Mysql中记录的位置是从0开始的。
       int offset = (page.getPageNum() - 1) * page.getPageSize();
       return sql + " limit " + offset + "," + page.getPageSize(); 
    }

    /**
     * 由于MappedStatement是一个全局共享的对象，因而需要复制一个对象来进行操作，防止并发访问导致错误
     *
     * @param ms
     * @param newSqlSource
     * @return
     */
    private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<ResultMapping>(0);
    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId() + "_分页", newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuffer keyProperties = new StringBuffer();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        //由于resultMaps第一次需要返回int类型的结果，所以这里需要生成一个resultMap - 防止并发错误
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), int.class, EMPTY_RESULTMAPPING).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }
    

    /**
     * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle
     * 其它的数据库都 没有进行分页
     *
     * @param page 分页对象
     * @param sql 原sql语句
     * @return
     */
    private String getPageSql(PageInfo<?> page, String sql) { 
       if ("mysql".equalsIgnoreCase(databaseType)) {
           return getMysqlPageSql(page, sql);
       } else if ("oracle".equalsIgnoreCase(databaseType)) {
           //return getOraclePageSql(page, sqlBuffer);
       }
       return sql;
    }

    
    private class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
