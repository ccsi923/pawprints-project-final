package com.pawprints.edgeservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pawprints.edgeservice.client.CommentClient;
import com.pawprints.edgeservice.exceptions.CommentClientNotWorkingException;
import com.pawprints.edgeservice.model.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private static final Logger LOGGER = LogManager.getLogger(CommentService.class);

    @Autowired
    private CommentClient commentClient;

    @HystrixCommand(fallbackMethod = "cantFindAllComments")
    public List<Comment> findAllComments(){
        LOGGER.info("INIT - findAllComments");
        return commentClient.findAllComment();
    }

    public List<Comment> cantFindAllComments() {
        LOGGER.error("comment-client-service not available!");
        throw new CommentClientNotWorkingException("comment-client-service not available!");
    }

    @HystrixCommand(fallbackMethod = "cantCreateComment")
    public Comment createComment(Comment comment){
        LOGGER.info("INIT - findAllCarts");
        return commentClient.createComment(comment);
    }

    public Comment cantCreateComment(Comment comment) {
        LOGGER.error("cart-service not available!");
        throw new CommentClientNotWorkingException("comment-client-service not available!");
    }
}
