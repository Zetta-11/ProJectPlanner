package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.Attachment;
import com.dnu.klimmenkov.projectplanner.entity.Task;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    void saveAttachment(MultipartFile file, int taskId) throws IOException;

    List<Attachment> getAttachmentsForTask(Task task);
}
