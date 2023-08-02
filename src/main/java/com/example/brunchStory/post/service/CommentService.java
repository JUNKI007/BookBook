package com.example.brunchStory.post.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.service.MemberService;
import com.example.brunchStory.post.domain.entity.Comment;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.request.CommentRequest;
import com.example.brunchStory.post.domain.response.CommentResponse;
import com.example.brunchStory.post.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MemberService memberService;


    // 댓글 작성
    public Comment insertComment(CommentRequest commentRequest, Long postId, Long memberId) {
        Post post = postService.findById(postId);
        Member member = memberService.findById(memberId);
        Comment comment = commentRequest.toEntity(post, member);
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId, Long memberId, String role) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(commentId + "번 댓글은 존재하지 않습니다"));

        if (!role.equals("ROLE_ADMIN") && !comment.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("댓글 삭제 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }

    // 자신이 작성한 모든 댓글 조회
    public List<CommentResponse> getAllCommentByMember(Long memberId) {
        List<Comment> comments = commentRepository.findCommentByMember_Id(memberId);
        return comments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

}
