package com.wyait.manage.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @项目名称：wyait-manage
 * @包名：com.wyait.manage.web
 * @类描述：
 * @创建人：wyait
 * @创建时间：2017-11-28 18:52
 * @version：V1.0
 */
@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping("/index")
	public String toIndex(){
		logger.debug("-------------wyait manage =index------------");
		return "index";
	}
	@RequestMapping("/home")
	public String toHome(){
		logger.debug("===111-------------wyait manage =home------------");
		return "home";
	}
}
