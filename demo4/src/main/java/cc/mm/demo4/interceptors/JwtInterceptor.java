package cc.mm.demo4.interceptors;

import cc.mm.demo4.UtilJWT;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String verifyResult = UtilJWT.verify(token);
        if(verifyResult.equals("ok")) {
            //验证成功  放行
            return true;
        } else {
            //验证失败  返回一个json
            JSONObject json = new JSONObject();
            json.put("result","fail");
            json.put("info",verifyResult);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }

    }

}
