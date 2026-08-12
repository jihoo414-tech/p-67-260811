package com.p67260811.domain.post.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.p67260811.domain.post.post.entity.Post;
import com.p67260811.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostComment extends BaseEntity {
    private String content;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public void modify(String content) {
        this.content = content;
    }
}