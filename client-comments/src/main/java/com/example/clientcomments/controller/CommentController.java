package com.example.clientcomments.controller;

import com.example.clientcomments.controller.impl.CommentControllerImpl;
import com.example.clientcomments.model.Comment;
import com.example.clientcomments.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController implements CommentControllerImpl {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> findAllComment(){
        return commentService.findAllComments();
    }

    @GetMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }
}
