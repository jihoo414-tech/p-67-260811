package com.p67260811.global.dto;

import com.p67260811.domain.post.comment.dto.PostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData {
    private String resultCode;
    private String msg;
    private PostCommentDto data;
}
