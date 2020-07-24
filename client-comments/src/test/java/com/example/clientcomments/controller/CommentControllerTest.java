package com.example.clientcomments.controller;

import com.example.clientcomments.model.Comment;
import com.example.clientcomments.repository.CommentRepository;
import com.example.clientcomments.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CommentControllerTest {

    @Autowired
    private CommentController commentController;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();



    private Comment comment;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        comment = new Comment( "Hello, it was amazind", (long) 1);
        commentService.createComment(comment);
    }

    @AfterEach
    void tearDown(){
        commentRepository.deleteAll();
    }
    @Test
    void findAllComment() throws Exception {
        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk());

    }

    @Test
    void createComment() throws Exception {
        mockMvc.perform(post("/comment")
                .content(objectMapper.writeValueAsString(new Comment("superCris", (long) 4)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());

    }
}