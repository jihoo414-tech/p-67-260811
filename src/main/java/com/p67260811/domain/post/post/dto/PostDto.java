package com.p67260811.domain.post.post.dto;

import com.p67260811.domain.post.comment.entity.PostComment;
import com.p67260811.domain.post.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class PostDto {


    private int id;
    private String title;
    private String content;
    private List<PostComment> comments;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public PostDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = post.getComments();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifyDate();
    }


}
