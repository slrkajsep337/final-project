package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.configuration.EncoderConfig;
import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.dto.PostCreateRequest;
import com.example.finalprojectsujin221220.dto.PostCreateResponse;
import com.example.finalprojectsujin221220.dto.PostModifyRequest;
import com.example.finalprojectsujin221220.dto.PostModifyResponse;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objtMpr;

    @MockBean
    EncoderConfig encoderConfig;

    @MockBean
    PostService ps;

    PostCreateRequest dto = PostCreateRequest.builder()
            .title("title1")
            .body("body1")
            .build();

    //-----------------????????? ??????-------------------
    @Test
    @WithMockUser
    @DisplayName("????????? ?????? ??????")
    void postCreate_success() throws Exception {
        when(ps.newPost(any(), any())).thenReturn(PostCreateResponse.builder()
                        .postId(0l)
                        .build());

        mockMvc.perform(post("/api/v1/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("????????? ?????? ??????")
    void postCreate_fail1() throws Exception {
        when(ps.newPost(any(), any())).thenThrow(new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage()));

        mockMvc.perform(post("/api/v1/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    //-----------------????????? ??????-------------------

    @Test
    @WithMockUser
    @DisplayName("????????? ?????? ??????")
    void postModify_success() throws Exception {

        PostModifyResponse post = PostModifyResponse.builder()
                .postId(1l)
                .build();

        when(ps.modifyPost(any(),any(),any())).thenReturn(post);

        mockMvc.perform(put("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("????????? ?????? ??????(1) - ?????? ??????")
    @WithAnonymousUser
    void postModify_fail1() throws Exception {

        when(ps.modifyPost(any(),any(),any()))
                .thenThrow(new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage()));

        mockMvc.perform(put("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("????????? ?????? ??????(2) - ????????? ?????????")
    @WithMockUser
    void postModify_fail2() throws Exception {

        when(ps.modifyPost(any(),any(),any()))
                .thenThrow(new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage()));

        mockMvc.perform(put("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getStatus().value()));

    }

    @Test
    @DisplayName("????????? ?????? ??????(3) - ?????????????????? ??????")
    @WithMockUser
    void postModify_fail3() throws Exception {
        when(ps.modifyPost(any(),any(),any()))
                .thenThrow(new ApplicationException(ErrorCode.DATABASE_ERROR,ErrorCode.DATABASE_ERROR.getMessage()));

        mockMvc.perform(put("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(status().is(ErrorCode.DATABASE_ERROR.getStatus().value()));

    }


    //-----------------????????? ??????-------------------

    @Test
    @DisplayName("????????? ?????? ??????")
    @WithMockUser
    void postDelete_success() throws Exception {

        mockMvc.perform(delete("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                        .andDo(print())
                        .andExpect(status().isOk());

    }

    @Test
    @WithAnonymousUser
    @DisplayName("????????? ?????? ??????(1) : ?????? ??????")
    void postDelete_fail1() throws Exception {

        when(ps.deletePost(any(), any()))
                .thenThrow(new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage()));

        mockMvc.perform(delete("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                        .andDo(print())
                        .andExpect(status().isUnauthorized());
    }
    @Test
    @WithMockUser
    @DisplayName("????????? ?????? ??????(2) : ????????? ?????????")
    void postDelete_fail2() throws Exception {

        when(ps.deletePost(any(), any()))
                .thenThrow(new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage()));

        mockMvc.perform(delete("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                        .andDo(print())
                        .andExpect(status().is(ErrorCode.INVALID_PERMISSION.getStatus().value()));
    }

    @Test
    @WithMockUser
    @DisplayName("????????? ?????? ??????(3) : ?????????????????? ??????")
    void postDelete_fail3() throws Exception {

        when(ps.deletePost(any(), any()))
                .thenThrow(new ApplicationException(ErrorCode.DATABASE_ERROR, ErrorCode.DATABASE_ERROR.getMessage()));

        mockMvc.perform(delete("/api/v1/posts/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objtMpr.writeValueAsBytes(dto)))
                        .andDo(print())
                        .andExpect(status().is(ErrorCode.DATABASE_ERROR.getStatus().value()));
    }

}