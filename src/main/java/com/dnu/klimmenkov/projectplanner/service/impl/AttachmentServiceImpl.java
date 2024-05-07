package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.Attachment;
import com.dnu.klimmenkov.projectplanner.entity.Task;
import com.dnu.klimmenkov.projectplanner.repository.AttachmentRepository;
import com.dnu.klimmenkov.projectplanner.service.AttachmentService;
import com.dnu.klimmenkov.projectplanner.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final TaskService taskService;

    @Override
    public Attachment getAttachmentById(int id) {
        return attachmentRepository.getReferenceById((long) id);
    }

    @Override
    public void saveAttachment(MultipartFile file, int taskId) throws IOException {
        if (!file.isEmpty()) {

            byte[] fileContent = file.getBytes();
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get("D:\\University\\Diploma\\Files", fileName);
            Files.write(filePath, fileContent);

            Attachment attachment = Attachment.builder()
                    .task(taskService.getTaskById(taskId))
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .filePath(filePath.toString())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();
            attachmentRepository.save(attachment);

        }
    }

    @Override
    public List<Attachment> getAttachmentsForTask(Task task) {
        List<Attachment> attachmentsByTask = attachmentRepository.findAllByTask(task);
        return attachmentsByTask;
    }
}
