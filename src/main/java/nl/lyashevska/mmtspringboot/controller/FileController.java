/**
 * https://www.bezkoder.com/spring-boot-upload-file-database/
 */

package nl.lyashevska.mmtspringboot.controller;

import nl.lyashevska.mmtspringboot.message.ResponseFile;
import nl.lyashevska.mmtspringboot.message.ResponseMessage;
import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.service.ManuscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

// changed controller to REST controller
@RestController

//@CrossOrigin("http://localhost:8081")

public class FileController {
 @Autowired
 private ManuscriptService manuscriptService;
 // ResponseEntity allows to manage the HTTP status and headers
 @PostMapping("/upload")
public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
  String message = "";
  try {
   manuscriptService.store(file);
   message = "Uploaded the file successfully: " + file.getOriginalFilename();
   return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
  } catch (Exception e) {
   message = "Could not upload the file: " + file.getOriginalFilename() + "!";
   return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
  }
 }
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = manuscriptService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();
            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable int id) {
        Manuscript m = manuscriptService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + m.getName() + "\"")
                .body(m.getData());
    }
}
