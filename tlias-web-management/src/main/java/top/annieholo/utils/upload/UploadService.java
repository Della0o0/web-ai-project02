package top.annieholo.utils.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UploadService {

    String uploadFile(String newFileName, MultipartFile file) throws IOException;

}
