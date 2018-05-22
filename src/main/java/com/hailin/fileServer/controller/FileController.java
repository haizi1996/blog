package com.hailin.fileServer.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import com.hailin.fileServer.domain.File;
import com.hailin.fileServer.service.FileService;
import com.hailin.fileServer.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;


@CrossOrigin(origins = "*", maxAge = 3600)  // 允许所有域名访问
@Controller
@RequestMapping("/fileserver")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FileService fileService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("files", fileService.listFiles());
        return "file/index";
    }

    /**
     * 获取文件片信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity serveFile(@PathVariable String id) {

        Optional<File> optionalFile = fileService.getFileById(id);

        if (optionalFile.isPresent()) {
            File file = optionalFile.get();
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream" )
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize()+"")
                    .header("Connection",  "close") 
                    .body( file.getContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }

    }
    
    /**
     * 在线显示文件
     * @param id
     * @return
     */
    @GetMapping("/view/{id}")
    @ResponseBody
    public ResponseEntity serveFileOnline(@PathVariable String id) {

        Optional<File> optionalFile = fileService.getFileById(id);

        if (optionalFile.isPresent()) {
            File file = optionalFile.get();
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, file.getContentType() )
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize()+"")
                    .header("Connection",  "close") 
                    .body( file.getContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }

    }
    
    /**
     * 上传
     * @param file
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/do/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        try {
        	File f = new File(file.getOriginalFilename(),  file.getContentType(), file.getSize(), file.getBytes());
        	f.setMd5( MD5Util.getMD5(file.getInputStream()) );
        	fileService.saveFile(f);
        } catch (IOException | NoSuchAlgorithmException ex) {
            logger.error(ex.getMessage() , ex);
            redirectAttributes.addFlashAttribute("message",
                    "Your " + file.getOriginalFilename() + " is wrong!");
            return "redirect:/fileserver/";
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/fileserver/";
    }


    /**
     * 上传
     * @param redirectAttributes
     * @return
     */
    @GetMapping ("/delete/{id}")
    public String handleFileUpload(@PathVariable("id") String id,
            RedirectAttributes redirectAttributes) {

        try {

            fileService.deleteById(id);
        } catch (Exception  ex) {
            logger.error(ex.getMessage() , ex);
            redirectAttributes.addFlashAttribute("message",
                    "Your " + id + " is wrong!");
            return "redirect:/fileserver/";
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully delete " + id + "!");

        return "redirect:/fileserver/";
    }



 
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
    	File returnFile = null;
        try {
        	File f = new File(file.getOriginalFilename(),  file.getContentType(), file.getSize(),file.getBytes());
        	f.setMd5( MD5Util.getMD5(file.getInputStream()) );
        	returnFile = fileService.saveFile(f);
        	returnFile.setPath("http://localhost:8080/view/"+f.getId());
        	returnFile.setContent(null) ;
        	return ResponseEntity.status(HttpStatus.OK).body("http://localhost:8080/view/"+f.getId());
 
        } catch (IOException | NoSuchAlgorithmException ex) {
            logger.error(ex.getMessage() , ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
 
    }
}
