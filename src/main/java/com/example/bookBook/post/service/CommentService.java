package com.example.bookBook.post.service;

import com.example.bookBook.member.domain.entity.Member;
import com.example.bookBook.member.repository.MemberRepository;
import com.example.bookBook.post.domain.entity.Comment;
import com.example.bookBook.post.domain.entity.Post;
import com.example.bookBook.post.repository.CommentRepository;
import com.example.bookBook.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void write(Long postId, Long memberId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Comment comment = new Comment();
        comment.updateComment(content);
        comment.updateMember(member);
        comment.updatePost(post);
        comment.updateUserName(member.getName());
        comment.updateCommentTime(java.time.LocalDate.now());

        commentRepository.save(comment);
    }
}
