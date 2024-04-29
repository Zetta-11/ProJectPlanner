package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(Comment comment);

    Comment getCommentById(int id);

    List<Comment> getAllComments();

    void deleteComment(int id);
}
