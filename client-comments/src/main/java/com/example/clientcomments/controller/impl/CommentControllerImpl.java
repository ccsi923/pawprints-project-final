package com.example.clientcomments.controller.impl;

import com.example.clientcomments.model.Comment;

import java.util.List;

public interface CommentControllerImpl {
    public Comment createComment(Comment comment);
    public List<Comment> findAllComment();
}
