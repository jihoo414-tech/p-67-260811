package com.p67260811.domain.post.post.service;


import com.p67260811.domain.post.comment.entity.PostComment;
import com.p67260811.domain.post.post.entity.Post;
import com.p67260811.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(String title, String content) {
        Post post = new Post(title, content);
        return postRepository.save(post);
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public long count() {
        return postRepository.count();
    }
    public void flush() {
        postRepository.flush();
    }
    public PostComment writeComment(Post post, String content) {
        return post.addComment(content);
    }

    public PostComment modifyComment(Post post, int commentId, String content) {
        return post.modifyComment(commentId, content);
    }
    public void deleteComment(Post post, int commentId) {
        post.removeComment(commentId);
    }
}