
package com.buxiu.bootexample.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解Controller的方法是否需要校验令牌
 * 如果一个方法不需校验令牌, 请加上注解@CheckToken(false)
 * @author bubuxiu@gmail.com
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckToken {
	/**
	 * 是否校验令牌
	 * @return true校验令牌, false不校验令牌
	 */
	boolean value() default true;
}
