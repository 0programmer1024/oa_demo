package chaoxing.oa_demo.controller;


import chaoxing.oa_demo.common.R;
import chaoxing.oa_demo.service.ICommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 通用服务controller
 */
@RestController
@RequestMapping("/api/common")
@Slf4j
@RequiredArgsConstructor
public class CommonController {
    private final ICommonService commonService;


    /**
     * 文件上传
     *
     * @param file 文件
     * @return 上传后的路径
     */
    @PostMapping("/upload")
    public R<String> upload(@RequestBody MultipartFile file) {
        return R.success(commonService.upload(file));
    }

    /**
     * 文件下载
     *
     * @param name     文件名
     * @param response 响应流
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        commonService.download(name, response);
    }
}
