package chaoxing.oa_demo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ICommonService {
    /**
     * 文件上传
     *
     * @param file 文件
     * @return 文件上传路径
     */
    String upload(MultipartFile file);

    /**
     * 文件下载
     *
     * @param name     文件下载路径
     * @param response 文件响应流
     */
    void download(String name, HttpServletResponse response);
}
