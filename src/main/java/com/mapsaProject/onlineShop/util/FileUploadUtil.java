package com.mapsaProject.onlineShop.util;
//
//import java.io.*;
//import java.nio.file.*;
//
//import org.springframework.web.multipart.MultipartFile;
//
//public class FileUploadUtil {
//
//    public static void saveFile(String uploadDir, String fileName,
//                                MultipartFile multipartFile) throws IOException {
//        Path uploadPath = Paths.get(uploadDir);
//
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            Path filePath = uploadPath.resolve(fileName);
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException ioe) {
//            throw new IOException("Could not save image file: " + fileName, ioe);
//        }
//    }
//}
import com.mapsaProject.onlineShop.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Objects;


@Service
@PropertySource(value = "classpath:conf.properties")

public class FileUploadUtil {

  private final Environment environment;
  private final String uploadDir;

  @Autowired
  public FileUploadUtil(Environment environment){
    this.environment=environment;
    uploadDir= environment.getProperty("upload.dir");

  }
  public String fileUpload(MultipartFile file) throws IOException {
    if ((file.getContentType().equals("image/jpeg"))) {
      Path path = Paths.get(uploadDir);
      if (!Files.exists(path)) {
        Files.createDirectory(path);
      }
      String uniqueName = createUniqueFileName(file);
      file.transferTo(new File(uploadDir + uniqueName));
      return uniqueName;
    }
    return "";
  }

  private String createUniqueFileName(MultipartFile file) {
    String[] strings = file.getOriginalFilename().split("\\.");
    String extention = strings[strings.length - 1];
    String originalFileName = file.getOriginalFilename();
    Long addSuffix = Instant.now().toEpochMilli();
    String strAddSuffix = addSuffix.toString();
    String uniqueFileName = strAddSuffix + "." + extention;
    return uniqueFileName;
  }

  public Resource loadFile(String fileName) {

    try {

      Path path = Paths.get(uploadDir).resolve(fileName).normalize();
      Resource resource = new UrlResource(path.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      }
      else {
        throw new NotFoundException("Could not find file");
      }
    }
    catch (MalformedURLException e) {
      throw new NotFoundException("Could not download file");
    }
  }

  public boolean deleteFile(String fileName) {
    File file = new File(uploadDir + fileName);
    if (!file.exists())
      return true;
    else {
      if (file.delete())
        return true;
    }
    return false;
  }
}
