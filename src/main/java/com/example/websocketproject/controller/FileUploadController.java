package com.example.websocketproject.controller;

import com.example.websocketproject.entity.AttachFileDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class FileUploadController {

    @Value("${org.zerock.upload.path}") //import 시에 springframework로 시작하는 value
    private String uploadFolder;

    @PostMapping(value="/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseEntity<List<AttachFileDTO>> upload(MultipartFile[] uploadFile) {
        List<AttachFileDTO> list = new ArrayList<>();

        String uploadFolderPath = getFolder();
        // make folder
        File uploadPath = new File(uploadFolder, uploadFolderPath);
        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : uploadFile) {
            AttachFileDTO attachDTO = new AttachFileDTO();

            System.out.println("Upload File Name : " + multipartFile.getOriginalFilename());
            System.out.println("Upload File Size : " + multipartFile.getSize());

            String uploadFileName = multipartFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();

            uploadFileName = uuid.toString() + "_" + uploadFileName;
            attachDTO.setFileName(uploadFileName);

            try {
                File saveFile = new File(uploadPath, uploadFileName);
                multipartFile.transferTo(saveFile);

                attachDTO.setUploadPath(uploadFolderPath);

                // check image type file
                /*if (checkImageType(saveFile)) {
                    FileOutputStream thumbnail = new FileOutputStream(new
                            File(uploadPath, "s_" + uuid + "_" + multipartFile.getOriginalFilename()));

                    Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);

                    thumbnail.close();
                }*/

                list.add(attachDTO);

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private String getFolder() {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String str = sdf.format(date);

        return str.replace("-", File.separator);
    };

    private boolean checkImageType(File file) {

        try {
            String contentType = Files.probeContentType(file.toPath());

            return contentType.startsWith("image");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


}
