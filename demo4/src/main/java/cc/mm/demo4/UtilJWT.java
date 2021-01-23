package cc.mm.demo4;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class UtilJWT {

    //加密用到的私钥
    private static final String SECRET_KEY = "xxxabc!@#$$";

    public static String createToken(Map<String, String> map) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24 * 7);  //token 设定7天过期
        JWTCreator.Builder builder = JWT.create();
        //通过withClaim()放一些用户信息，这些信息是可以被轻松解码解密的，不要放敏感信息在这里
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String token = builder
                .withExpiresAt(calendar.getTime())  //设定token 过期信息
                .sign(Algorithm.HMAC384(SECRET_KEY));  //指定加密算法，注意创建token用到的加解密算法要和后面的verify()验证 统一
        return token;

    }

    //返回 ok  表示验证成功
    //返回其他的表示错误信息
    public static String verify(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC384(SECRET_KEY)).build();
        try {
            DecodedJWT verify = jwtVerifier.verify(token);
            return "ok";
        } catch (SignatureVerificationException e) {
            return "无效签名";
        } catch (TokenExpiredException e) {
            return "token过期";
        } catch (AlgorithmMismatchException e) {
            return "token算法不一致";
        } catch (Exception e) {
            return "无效签名(其他错误)";
        }
    }

    public static DecodedJWT getDecodedJWT(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC384(SECRET_KEY)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify;
    }

}
