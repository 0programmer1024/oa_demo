//package chaoxing.oa_demo.config;
//
//import chaoxing.oa_demo.interceptor.LoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class DemoWebConfig implements WebMvcConfigurer {
//
//
//    /**
//     * 拦截器配置
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        //添加拦截器
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
//                //放行路径，可以添加多个
//                .excludePathPatterns("/api/login");
//    }
//}
