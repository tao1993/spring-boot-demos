package cc.mm.demo4.controller;


import cc.mm.demo4.UtilJWT;
import cc.mm.demo4.enity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {


    @GetMapping("/")
    String index(){
        return "index";
    }


    //我的资料 数据，在拦截器里面配置了要带token的请求才能访问
    @PostMapping ("/profile")
    @ResponseBody
    Object profile(HttpServletRequest req){
        String token = req.getHeader("token");
        String strPayload = UtilJWT.getDecodedJWT(token).getPayload();
        strPayload = new String(Base64.getDecoder().decode(strPayload));
        //测试，简单返回一些内容
        JSONObject json = JSON.parseObject(strPayload);
        json.put("info","哈哈哈");
        return json;
    }


    //  接收前台的用户登录请求，验证账号和密码
    //  一开始发现 axios发的请求参数 我spring boot后台接收不到，解决办法参考:
    //   https://blog.csdn.net/qq_36208461/article/details/103079514
    //  我是通过后台加 @RequestBody   解决的，前台axios不做改动，使用默认配置
    @RequestMapping ("/login")
    @ResponseBody
    Object login(@RequestBody User user){
        JSONObject json = new JSONObject();

        //这里因为测试demo，简单模拟验证过程
        if(user.getUserName().equals("abc") && user.getUserPass().equals("123")) {
            //账号密码对的上说明登陆成功，开始用jwt生成一个token返回给前端，让前端自己去把token保存到localStorage
            //可以把一些用户信息放进token，但不要放敏感信息
            Map<String,String> map = new HashMap<>();
            map.put("userId","11122");  //因为测试，简单写死
            map.put("userName",user.getUserName());
            String token = UtilJWT.createToken(map);
            System.out.println(token);
            //给前端返回信息
            json.put("token",token);
            json.put("result","success");
        }else {
            json.put("result","fail");  //登录失败
        }
        return json;
    }

}
