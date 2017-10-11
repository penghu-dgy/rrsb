package io.renren.utils.interceptor;

import io.renren.utils.annotation.IgnoreAuth;
import net.sf.json.JSONObject;
import io.renren.entity.TokenEntity;
import io.renren.service.TokenService;
import io.renren.utils.R;
import io.renren.utils.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IgnoreAuth annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        }else{
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if(annotation != null){
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        //token为空
        if(StringUtils.isBlank(token)){
        	RRException e = new RRException("token不能为空");
        	e.setCode(100);
            throw e;
        }

        //查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
        	RRException e = new RRException("token失效，请重新登录");
            //throw new RRException("token失效，请重新登录");
       //向控制器端返回
    	 	/*Map<String,Object> map = new HashMap<String,Object>();
    	 	map.put("code", 207);
    	 	map.put("msg", "token 失效penghu");*/
    	 	
    	 	response.setHeader("Access-Control-Allow-Origin","*");
    	 /*	JSONObject responseJSONObject = JSONObject.fromObject(map);  
    	 	response.setCharacterEncoding("UTF-8");  
    	 	response.setContentType("application/json; charset=utf-8");  
    	 	PrintWriter out = null;  
    	 	out = response.getWriter();
    	 	out.println(responseJSONObject.toString());*/
    	 	//System.out.println("	``````````````````"+responseJSONObject);
            //throw new RRException("token失效，请重新登录");
        	throw e;
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());
      //  System.out.println("aaaaaaaaaaaaaaaaaa"+tokenEntity.getUserId());
        return true;
    }
}
