package chaoxing.oa_demo.service.impl;

import chaoxing.oa_demo.common.CustomException;
import chaoxing.oa_demo.service.ICommonService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 通用服务实现类
 */
@Service
@Slf4j
public class CommonServiceImpl implements ICommonService {
    @Value("${oa.path}")
    private String basePath;

    @Override
    public String upload(MultipartFile file) {
        log.info(file.toString());
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isBlank(originalFilename)) {
            throw new CustomException("文件上传失败，原始文件名不能为空");
        }
        String suffix = StrUtil.addPrefixIfNot(FileNameUtil.getSuffix(originalFilename), ".");
        String fileName = IdUtil.fastSimpleUUID() + suffix;
        FileUtil.mkdir(basePath);
        File dest = FileUtil.file(basePath, fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error(e.toString());
            throw new CustomException("文件上传失败");
        }
        return fileName;
    }

    @Override
    public void download(String name, HttpServletResponse response) {
        File file = FileUtil.file(basePath, name);
        if (!file.exists() || !file.isFile()) {
            throw new CustomException("文件不存在：" + name);
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + URLUtil.encode(name, StandardCharsets.UTF_8));
        response.setContentLengthLong(file.length());
        try (InputStream in = FileUtil.getInputStream(file);
             ServletOutputStream out = response.getOutputStream()) {
            IoUtil.copy(in, out);
            out.flush();
        } catch (IOException e) {
            log.error(e.toString());
            throw new CustomException("文件下载失败");
        }
    }


}
