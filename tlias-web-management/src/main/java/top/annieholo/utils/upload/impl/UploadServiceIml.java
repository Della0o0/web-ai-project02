package top.annieholo.utils.upload.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.annieholo.utils.upload.UploadService;
import top.annieholo.utils.upload.OssProperties;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UploadServiceIml implements UploadService {


    private static final Logger log = LoggerFactory.getLogger(UploadServiceIml.class);
    @Autowired
    private OssProperties ossProperties;
    /**
     * 上传文件
     *
     * @return
     */
    @Override
    // @SneakyThrows
    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        String endpoint = ossProperties.getEndpoint();
        String bucketName = ossProperties.getBucketName();
        String region = ossProperties.getRegion();
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getAccessKeySecret();

        log.info("配置: {}, {}, {}, {}", endpoint, bucketName, accessKeyId, accessKeySecret );
        URL fileUrl = null;

        // 当前系统日期的字符串 yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        // 生成一个不重复的文件名
        String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;
        log.info("objectName: {}", objectName );

        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 如果你只是为了上传 OSS，其实不建议转成 File，直接这样更好：
        // ossClient.putObject(bucketName, objectName, multipartFile.getInputStream());
        // MultipartFile -> File 会多一次磁盘写入，还要考虑临时文件删除。直接用输入流上传更清爽。
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file.getInputStream());
            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 设置预签名URL过期时间，单位为毫秒。本示例以设置过期时间为1小时为例。
            // Date expiration = new Date(new Date().getTime() + 3600 * 1000L);
            // fileUrl = ossClient.generatePresignedUrl(bucketName, fileName , expiration);
            // log.info("上传完成，文件路径：{}", fileUrl);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        // return fileUrl;
        // return ossProperties.getUrl() + "/" + path + "/" + fileName;
        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }
}
