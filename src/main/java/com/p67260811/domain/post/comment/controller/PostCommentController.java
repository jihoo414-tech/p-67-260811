package com.p67260811.domain.post.comment.controller;


import com.p67260811.domain.post.comment.dto.PostCommentDto;
import com.p67260811.domain.post.comment.entity.PostComment;
import com.p67260811.domain.post.post.entity.Post;
import com.p67260811.domain.post.post.service.PostService;
import com.p67260811.global.dto.RsData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
public class PostCommentController {

    private final PostService postService;

    record CommentWriteForm(
            @NotBlank(message = "댓글 내용을 입력해주세요.")
            @Size(min = 2, max = 100, message = "댓글 내용은 2글자 이상 100글자 이하로 입력해주세요.")
            String content
    ) {
    }

    @GetMapping
    public List<PostCommentDto> list(
            @PathVariable int postId
    ) {
        Post post = postService.findById(postId).get();

        return post.getComments()
                .stream()
                .map(PostCommentDto::new)
                .toList();
    }

    @GetMapping("/{commentId}")
    public PostCommentDto item(
            @PathVariable int postId,
            @PathVariable int commentId
    ) {
        Post post = postService.findById(postId).get();
        PostComment postComment = postService.findCommentById(post, commentId);

        return new PostCommentDto(postComment);
    }

    @GetMapping("/write")
    @Transactional
    public String write(
            @PathVariable int postId,
            @Valid CommentWriteForm form
    ) {

        Post post = postService.findById(postId).get();
        PostComment postComment = postService.writeComment(post, form.content);
        // DB 저장
        postService.flush();

        return "%d번 댓글이 성공적으로 등록되었습니다.".formatted(postComment.getId()); // 아직 DB에 저장되지 않은 시점

    }

    @GetMapping("/{commentId}/delete")
    @Transactional
    public RsData<PostCommentDto> delete(
            @PathVariable int postId,
            @PathVariable int commentId
    ) {

        Post post = postService.findById(postId).get();
        PostComment postComment = postService.findCommentById(post, commentId);

        postService.deleteComment(post, commentId);

        return new RsData<>(
                "200-1",
                "%d번 댓글이 삭제되었습니다.".formatted(commentId),
                new PostCommentDto(postComment)
        );
    }

    record CommentModifyForm(
            @NotBlank(message = "댓글 내용을 입력해주세요.")
            @Size(min = 2, max = 100, message = "댓글 내용은 2글자 이상 100글자 이하로 입력해주세요.")
            String content
    ) {
    }

    @GetMapping("/{commentId}/modify")
    @Transactional
    public String modify(
            @PathVariable int postId,
            @PathVariable int commentId,
            @Valid CommentModifyForm form
    ) {

        Post post = postService.findById(postId).get();
        postService.modifyComment(post, commentId, form.content);

        return "%d번 댓글이 수정되었습니다.".formatted(commentId);
    }
}