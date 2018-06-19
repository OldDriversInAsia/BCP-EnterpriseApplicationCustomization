
package com.odib.bcp.eac.config.interceptor;

import com.odib.bcp.eac.constant.ApiResultEnum;
import com.odib.bcp.eac.constant.CommonValue;
import com.odib.bcp.eac.exception.ApiException;
import com.odib.bcp.eac.model.pojo.BaseUser;
import com.odib.bcp.eac.service.BaseUserService;
import com.odib.bcp.eac.service.LoginService;
import com.odib.bcp.eac.service.RedisService;
import com.odib.bcp.eac.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <br>
 * 〈拦截器类〉
 *  @author lizhuo
 * @create 2017/12/7
 * @since 1.0.0
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    RedisService redisService;
    @Autowired
    BaseUserService baseUserService;
    @Autowired
    LoginService loginService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(HttpMethod.GET.toString().equals(httpServletRequest.getMethod())){
            return true;
        }
        String token = CookieUtils.getCookie(httpServletRequest, CommonValue.LOGIN_TOKEN_COOKIE_KEY);
        if (null == token) {
            throw new ApiException(ApiResultEnum.LOGIN_100004);
        }
        Integer idNo = loginService.getLoginToken(token);
        //结果为空 返回false 或者 用户信息空
        if (null == idNo) {
            throw new ApiException(ApiResultEnum.LOGIN_100004);
        }
        BaseUser baseUser = baseUserService.selectById(idNo);
        if (baseUser == null || !BaseUser.Status.OPEN.equals(baseUser.getStatus())) {
            throw new ApiException(ApiResultEnum.LOGIN_100005);
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
