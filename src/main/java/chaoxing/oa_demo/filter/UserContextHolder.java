//package chaoxing.oa_demo.filter;
//
//import chaoxing.oa_demo.common.UserToken;
//
///**
// * 用户Token上下文控制
// */
//public class UserContextHolder {
//    /**
//     * 用户token上下文
//     */
//    private static ThreadLocal<UserToken> userTokenThreadLocal = new ThreadLocal<>();
//
//    /**
//     * 赋值token
//     *
//     * @param token token
//     */
//    public static void setUserToken(UserToken token) {
//        userTokenThreadLocal.set(token);
//    }
//
//    /**
//     * 获取token
//     *
//     * @return token
//     */
//    public static UserToken getUserToken() {
//        return userTokenThreadLocal.get();
//    }
//
//    /**
//     * 删除token
//     */
//    public static void removeUserToken() {
//        userTokenThreadLocal.remove();
//    }
//}
