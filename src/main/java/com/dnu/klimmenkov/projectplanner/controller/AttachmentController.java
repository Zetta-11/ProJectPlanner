package com.dnu.klimmenkov.projectplanner.controller;

import com.dnu.klimmenkov.projectplanner.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;

@Controller
@RequestMapping("/attachments")
@AllArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @GetMapping("/downloadAttachment/{attachmentId}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable int attachmentId) {
        try {
            Path filePath = Path.of(attachmentService.getAttachmentById(attachmentId).getFilePath());
            Resource file = new UrlResource(filePath.toUri());

            if (file.exists() || file.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                        .body(file);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
