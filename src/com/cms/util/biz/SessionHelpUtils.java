package com.cms.util.biz;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author  作者：qgn
 *
 *@version  创建时间：2019年4月18日 上午10:12:57 
 * 
 *@descriptions 类说明：请添加类描述
 */
public class SessionHelpUtils {
/**
 * @return
 * @Description：获得当前session
 * @return: HttpSession
 */
public static HttpSession getSession() {
    HttpSession session = ((ServletRequestAttributes)RequestContextHolder
            .getRequestAttributes()).getRequest().getSession();// 获得当前session
    return session;
}
}
