package chaoxing.oa_demo.filter;

import chaoxing.oa_demo.common.UserToken;

public class UserContextHolder {
    private static ThreadLocal<UserToken> userTokenThreadLocal = new ThreadLocal<>();

    public static void setUserToken(UserToken token) {
        userTokenThreadLocal.set(token);
    }

    public static UserToken getUserToken() {
        return userTokenThreadLocal.get();
    }

    public static void removeUserToken() {
        userTokenThreadLocal.remove();
    }
}
