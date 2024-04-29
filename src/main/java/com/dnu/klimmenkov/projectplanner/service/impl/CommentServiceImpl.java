package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.Comment;
import com.dnu.klimmenkov.projectplanner.repository.CommentRepository;
import com.dnu.klimmenkov.projectplanner.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(int id) {
        return commentRepository.getReferenceById((long) id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById((long) id);
    }
}
