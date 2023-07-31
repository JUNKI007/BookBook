package com.example.brunchStory.post.domain.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.post.domain.dto.PostCondition;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.repository.PostRepository;
import com.example.brunchStory.post.domain.request.PostRequest;
import com.example.brunchStory.post.domain.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    public void write(Integer memberId, PostRequest postRequest){
        //TODO 멤버 아이디로 멤버 찾아오기
        Member member = null; // 찾아온 멤버로 변경 예정
        Post post = postRequest.toEntity(member);
        postRepository.save(post);

    }

    public void delete(Integer postId, Integer memberId){
        //TODO 내 글이 맞는지 확인


    }


    @Transactional(readOnly = true)
    public Page<PostResponse> findAll(PostCondition postCondition, PageRequest pageRequest){
        return postRepository.findAllByCondition(pageRequest,postCondition);
    }


}
