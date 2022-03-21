package com.example.demo.controller;

import com.example.demo.domain.WorksDTO;
import com.example.demo.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;

import java.util.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WorksController {

    @Autowired
    private WorksService worksService;

    //create
    @PostMapping("/file/upload")
    public boolean uploadFile(String title, String kind, String sub, List<MultipartFile> files, String gitSub, String gitLink, String titleJap, String subJap, List<MultipartFile> filesJap, String more) throws IllegalStateException, IOException {
        String UPLOAD_PATH = "/home/ec2-user/src/" + new Date().getTime(); // 업로드 할 위치 // 현재 날짜 값 폴더
        String UPLOAD_PATH_JAP = "/home/ec2-user/src/" + new Date().getTime() + "_JAP";
        WorksDTO worksDTO = new WorksDTO();
        try {
            for (int i = 0; i < files.size(); i++) {
                String fileId = "" + i;
                String originName = files.get(i).getOriginalFilename(); // ex) 파일.jpg
                String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
//                originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
//                long fileSize = files.get(i).getSize(); // 파일 사이즈

                File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
                if (!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
                    fileSave.mkdirs();
                }

                files.get(i).transferTo(fileSave); // fileSave의 형태로 파일 저장

//                files.get(i).transferTo(new File(files.get(i).getOriginalFilename()));
            }
            for (int i = 0; i < filesJap.size(); i++) {
                String fileId = "" + i;
                String originName = filesJap.get(i).getOriginalFilename(); // ex) 파일.jpg
                String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
//                originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
//                long fileSize = filesJap.get(i).getSize(); // 파일 사이즈

                File fileSave = new File(UPLOAD_PATH_JAP, fileId + "." + fileExtension); // ex) fileId.jpg
                if (!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
                    fileSave.mkdirs();
                }

                filesJap.get(i).transferTo(fileSave); // fileSave의 형태로 파일 저장

//                files.get(i).transferTo(new File(files.get(i).getOriginalFilename()));
            }

            worksDTO.setTitle(title);
            worksDTO.setKind(kind);
            worksDTO.setSub(sub);
            worksDTO.setImgLink(UPLOAD_PATH);
            worksDTO.setImgLinkJap(UPLOAD_PATH_JAP);
            worksDTO.setImgCnt(files.size());
            worksDTO.setGitSub(gitSub);
            worksDTO.setGitLink(gitLink);
            worksDTO.setTitleJap(titleJap);
            worksDTO.setSubJap(subJap);
            worksDTO.setMore(more);

        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

        return worksService.registerWorks(worksDTO);
    }

    //list
    @GetMapping("/file")
    public List<Object> getFileList() throws IllegalStateException, IOException {
        return worksService.getWorksList();
    }

    //read
    @GetMapping("/file/{id}")
    public WorksDTO getFile(@PathVariable("id") Integer id) throws IllegalStateException {
        return worksService.getWorksDetail(id);
    }

    //read img
    @GetMapping(value = "/file/test/{id}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<byte[]>> test(@PathVariable("id") Integer id) throws IOException {
        List<byte[]> fileList = new ArrayList<byte[]>();
        String DATA_DIRECTORY = worksService.getWorksDetail(id).getImgLink();
        String[] files = new File(DATA_DIRECTORY).list();

        for(int i=0; i<files.length; i++) {
            InputStream imageStream = new FileInputStream(
                    DATA_DIRECTORY + "/" + files[i]);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int read;
            byte[] imageByteArray = new byte[imageStream.available()];
            while ((read = imageStream.read(imageByteArray, 0, imageByteArray.length)) != -1) {
                buffer.write(imageByteArray, 0, read);
            }
            buffer.flush();
            byte[] array = buffer.toByteArray();
            imageStream.close();
            fileList.add(array);
//		InputStream imageStream = new FileInputStream("/home/ubuntu/images/feed/" + imagename);

        }
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(fileList);
    }

    //read img
    @GetMapping(value = "file/{id}/{number}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@PathVariable("id") Integer id, @PathVariable("number") Integer number) throws IOException {
        String DATA_DIRECTORY = worksService.getWorksDetail(id).getImgLink();
        String[] files = new File(DATA_DIRECTORY).list();

        InputStream imageStream = new FileInputStream(
                DATA_DIRECTORY + "/" + files[number]);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        byte[] imageByteArray = new byte[imageStream.available()];
        while ((read = imageStream.read(imageByteArray, 0, imageByteArray.length)) != -1) {
            buffer.write(imageByteArray, 0, read);
        }
        buffer.flush();
        byte[] targetArray = buffer.toByteArray();
        imageStream.close();
//		InputStream imageStream = new FileInputStream("/home/ubuntu/images/feed/" + imagename);

        return new ResponseEntity<byte[]>(targetArray, HttpStatus.OK);
    }

    //read img JAP
    @GetMapping(value = "file/jap/{id}/{number}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearchJAP(@PathVariable("id") Integer id, @PathVariable("number") Integer number) throws IOException {
        String DATA_DIRECTORY = worksService.getWorksDetail(id).getImgLinkJap();
        String[] files = new File(DATA_DIRECTORY).list();

        InputStream imageStream = new FileInputStream(
                DATA_DIRECTORY + "/" + files[number]);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        byte[] imageByteArray = new byte[16384];
        while ((read = imageStream.read(imageByteArray, 0, imageByteArray.length)) != -1) {
            buffer.write(imageByteArray, 0, read);
        }
        buffer.flush();
        byte[] targetArray = buffer.toByteArray();
        imageStream.close();
        buffer.close();
//		InputStream imageStream = new FileInputStream("/home/ubuntu/images/feed/" + imagename);

        return new ResponseEntity<byte[]>(targetArray, HttpStatus.OK);
    }

    //update
    @PostMapping("file/{id}")
    public boolean updateFile(@PathVariable("id") Integer id, String title, String kind, String sub, List<MultipartFile> files, String titleJap, String subJap, List<MultipartFile> filesJap) throws IllegalStateException, IOException {
        WorksDTO worksDTO = worksService.getWorksDetail(id);
        String UPLOAD_PATH = "/home/ec2-user/src/" + new Date().getTime(); // 업로드 할 위치 // 현재 날짜 값 폴더
        String UPLOAD_PATH_JAP = "/home/ec2-user/src/" + new Date().getTime() + "_JAP";
        try {
            for (int i = 0; i < files.size(); i++) {
                if (!files.get(i).isEmpty()) {
                    String fileId = "" + i;
                    String originName = files.get(i).getOriginalFilename();
                    String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
//                originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
//                long fileSize = files.get(i).getSize(); // 파일 사이즈

                    File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
                    if (!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
                        fileSave.mkdirs();
                    }

                    files.get(i).transferTo(fileSave); // fileSave의 형태로 파일 저장
                }
//                files.get(i).transferTo(new File(files.get(i).getOriginalFilename()));
            }
            for (int i = 0; i < filesJap.size(); i++) {
                if (!files.get(i).isEmpty()) {
                    String fileId = "" + i;
                    String originName = filesJap.get(i).getOriginalFilename(); // ex) 파일.jpg
                    String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
//                originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
//                long fileSize = filesJap.get(i).getSize(); // 파일 사이즈

                    File fileSave = new File(UPLOAD_PATH_JAP, fileId + "." + fileExtension); // ex) fileId.jpg
                    if (!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
                        fileSave.mkdirs();
                    }
                    filesJap.get(i).transferTo(fileSave); // fileSave의 형태로 파일 저장
                }
//                files.get(i).transferTo(new File(files.get(i).getOriginalFilename()));
            }
            worksDTO.setTitle(title);
            worksDTO.setKind(kind);
            worksDTO.setTitleJap(titleJap);
            worksDTO.setSub(sub);
            worksDTO.setSubJap(subJap);
            worksDTO.setImgLink(UPLOAD_PATH);
            worksDTO.setImgLinkJap(UPLOAD_PATH_JAP);
            worksDTO.setImgCnt(files.size());

        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return worksService.registerWorks(worksDTO);

    }
}
