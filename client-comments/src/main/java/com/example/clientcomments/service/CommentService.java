package com.example.clientcomments.service;

import com.example.clientcomments.model.Comment;
import com.example.clientcomments.repository.CommentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private static final Logger LOGGER = LogManager.getLogger(CommentService.class);


    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAllComments(){
        LOGGER.info("INIT - findAllComments");
        LOGGER.info("END - findAllComments");
       return commentRepository.findAll();
    }

    public Comment createComment(Comment comment){
        LOGGER.info("INIT - createComment");
        LOGGER.info("END - createComment");
        return commentRepository.save(comment);
    }
}
