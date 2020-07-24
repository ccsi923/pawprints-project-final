package com.pawprints.edgeservice.client;

import com.pawprints.edgeservice.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name ="client-comments-service", url = "https://client-comments-pawprints-serv.herokuapp.com/")
public interface CommentClient {


    @GetMapping("/comments")
    public List<Comment> findAllComment();

    @GetMapping("/comment")
    public Comment createComment(@RequestBody Comment comment);
}
