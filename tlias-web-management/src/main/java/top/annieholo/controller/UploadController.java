package top.annieholo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.annieholo.pojo.Result;
import top.annieholo.utils.upload.UploadService;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {

    @Autowired
    private  UploadService uploadService;
    @PostMapping("/upload")
    public Result upload(String name, String age, MultipartFile file) throws IOException {
        log.info("上传的参数：{}, {}, {}: ", name, age, file);
        // String originalFilename = file.getOriginalFilename();
        // // 需要给文件名做UUID处理
        // // 拿到后缀
        // String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        // String newFileName = UUID.randomUUID().toString() + extension;
        // 上传到oss
        String fileUrl = uploadService.uploadFile(file.getOriginalFilename(), file);
        log.info("controller上传文件返回: {}", fileUrl);
        // file.transferTo(new File("/Users/tangyulian/Documents/Study/JAVA/images/"+newFileName));
        return Result.success(fileUrl);
    }
}
