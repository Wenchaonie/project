package com.wyait.manage.config;

import com.wyait.manage.shiro.ShiroRealm;
import com.wyait.manage.web.IndexController;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @项目名称：wyait-manage
 * @包名：com.wyait.manage.config
 * @类描述：
 * @创建人：wyait
 * @创建时间：2017-12-12 18:51
 * @version：V1.0
 */
@Configuration public class ShiroConfig {
	private static final Logger logger = LoggerFactory
			.getLogger(ShiroConfig.class);

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件过滤器
	 *	</br>1,配置shiro安全管理器接口securityManage;
	 *	</br>2,shiro 连接约束配置filterChainDefinitions;
	 */
	@Bean public ShiroFilterFactoryBean shiroFilterFactoryBean(
			org.apache.shiro.mgt.SecurityManager securityManager) {
		//shiroFilterFactoryBean对象
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 配置shiro安全管理器 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 指定要求登录时的链接
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权时跳转的界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		// filterChainDefinitions拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 从上向下顺序判断
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/templates/**", "anon");
		filterChainDefinitionMap.put("/index", "anon");
		filterChainDefinitionMap.put("/home", "anon");

		// 配置退出过滤器,具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//add操作，该用户必须有【addOperation】权限
		filterChainDefinitionMap.put("/add", "perms[addOperation]");

		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问【放行】-->
		filterChainDefinitionMap.put("/user/**", "authc");

		shiroFilterFactoryBean
				.setFilterChainDefinitionMap(filterChainDefinitionMap);
		logger.debug("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}

	/**
	 * shiro安全管理器设置realm认证和ehcache缓存管理
	 * @return
	 */
	@Bean public org.apache.shiro.mgt.SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(shiroRealm());
		// //注入ehcache缓存管理器;
		securityManager.setCacheManager(ehCacheManager());
		return securityManager;
	}

	/**
	 * 身份认证realm; (账号密码校验；权限等)
	 *
	 * @return
	 */
	@Bean public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		return shiroRealm;
	}

	/**
	 * ehcache缓存管理器；shiro整合ehcache：
	 * 通过安全管理器：securityManager
	 * @return EhCacheManager
	 */
	@Bean public EhCacheManager ehCacheManager() {
		logger.debug(
				"=====shiro整合ehcache缓存：ShiroConfiguration.getEhCacheManager()");
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
		return cacheManager;
	}

}
