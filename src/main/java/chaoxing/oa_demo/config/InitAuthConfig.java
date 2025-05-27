package chaoxing.oa_demo.config;

import chaoxing.oa_demo.common.RequestMethod;
import chaoxing.oa_demo.enums.UserType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class InitAuthConfig {
    //TODO : 此处应有权限配置页面
    /**
     *
     * @return
     */
    @Bean(name = "userTypeAuthMap")
    public Map<Integer, List<RequestMethod>> interviewerAuthMap() {
        Map<Integer, List<RequestMethod>> map = new HashMap<>();
        List<RequestMethod> requestMethods = new ArrayList<>();
        requestMethods.add(new RequestMethod("GET", "/api/applicant/page"));
        requestMethods.add(new RequestMethod("PUT", "/api/applicant"));
        requestMethods.add(new RequestMethod("GET", "/api/common/download"));
        requestMethods.add(new RequestMethod("PUT", "/api/user/interviewer"));
        requestMethods.add(new RequestMethod("GET", "/api/user/interviewer/all"));
        map.put(UserType.Interviewer.getCode(), requestMethods);
        return map;
    }

    @Bean(name = "visitorAuthList")
    public List<RequestMethod> visitorAuthList() {
        List<RequestMethod> requestMethods = new ArrayList<>();
        requestMethods.add(new RequestMethod("GET", "/api/applicant/page"));
        requestMethods.add(new RequestMethod("GET", "/api/user/interviewer/all"));
        return requestMethods;
    }

}
