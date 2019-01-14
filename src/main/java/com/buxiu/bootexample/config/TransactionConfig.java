package com.buxiu.bootexample.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
 
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
 
/**
 * 参考：http://www.cnblogs.com/guozp/p/7446477.html
 */
@Aspect
//@Component 事务依然生效
@Configuration
public class TransactionConfig {
 
    private static final int TX_METHOD_TIMEOUT = 5;
    
    // 只对service的实现方法进行事务控制
    private static final String AOP_POINTCUT_EXPRESSION = "execution(public * com.buxiu.bootexample.service.impl.*Impl.*(..))";
 
    @Autowired
    private PlatformTransactionManager transactionManager;
 
    @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        
         /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        
        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(
            Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        
        // 只读
        txMap.put("count*", readOnlyTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        
        // 更改
        txMap.put("add*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("apply*", requiredTx);
        txMap.put("import*", requiredTx);        
        
        source.setNameMap( txMap );
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }
 
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}