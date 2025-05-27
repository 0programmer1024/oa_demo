package chaoxing.oa_demo.utils;

import chaoxing.oa_demo.consts.TokenPayloads;
import chaoxing.oa_demo.entity.User;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSignerUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Token工具类
 */
@Slf4j
public class TokenUtils {
    /**
     * 秘钥key
     */
    public static byte[] key = "secret-demo".getBytes();

    public static String getToken(User user) {
        if (ObjectUtil.isNull(user)) {
            return StrUtil.EMPTY;
        }
        return JWT.create()
                .setPayload(TokenPayloads.X_USER_ID, user.getId())
                .setPayload(TokenPayloads.X_USER_TYPE, user.getType())
                .setSigner(JWTSignerUtil.hs256(key))
                .sign();
    }

//    public static UserToken decodeToken(String token) {
//        JWT jwt = JWTUtil.parseToken(token);
//        String userId = jwt.getPayload(TokenPayloads.X_USER_ID).toString();
//        long userIdLong = 0L;
//        try {
//            userIdLong = Long.parseLong(userId);
//        } catch (NumberFormatException e) {
//            log.warn("USER ID INVALID", userId);
//        }
//        return new UserToken(userIdLong, 1);
//    }

}
