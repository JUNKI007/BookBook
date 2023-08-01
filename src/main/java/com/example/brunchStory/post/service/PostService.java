package com.example.brunchStory.post.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.response.MemberResponse;
import com.example.brunchStory.member.service.MemberService;
import com.example.brunchStory.post.domain.dto.PostCondition;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.entity.Subject;
import com.example.brunchStory.post.repository.PostRepository;
import com.example.brunchStory.post.domain.request.PostRequest;
import com.example.brunchStory.post.domain.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final SubjectService subjectService;





    public void write(Long memberId, PostRequest postRequest){
        //TODO 멤버 아이디로 멤버 찾아오기
        Member member = Member.builder().id(memberId).build();

        Subject subject = subjectService.findById(postRequest.getSubjectNum());
        System.out.println(subject);

        Post post = postRequest.toEntity(member,subject);
        postRepository.save(post);

    }

    public void delete(Integer postId, Integer memberId){
        //TODO 내 글이 맞는지 확인


    }

    public List<Post>findAllById(List<Long> postIds){ // 아이디들로 모든 글 찾아오기
        List<Post> allById = postRepository.findAllById(postIds);
        return allById;
    }

    public Post findById(Long postId){ // 글 하나 찾아오기.
        Optional<Post> byId = postRepository.findById(postId);

        Post post = byId.orElseThrow(RuntimeException::new);

        return post;
    }

    public PostResponse findByIdCustom(Long postId){
        System.out.println(postId);

        Optional<Post> byIdCustom = postRepository.findByIdCustom(postId);
        Post post = byIdCustom.orElseThrow(() -> new RuntimeException());
        return new PostResponse(post);
    }

    @Transactional(readOnly = true) // 조건에 맞게 찾아오기
    public Page<PostResponse> findAllByCondition(PostCondition postCondition, PageRequest pageRequest){
        return postRepository.findAllByCondition(pageRequest,postCondition);
    }
    @Transactional(readOnly = true)
    public Page<PostResponse> findAllByFavorite(Long memberId, PageRequest pageRequest){
        MemberResponse byId = memberService.findById(memberId);


        // 멤버 아이디로 멤버 찾아서 그 유저의 흥미 뽑아오기 ( 리스트로 )
        // 그 흥미에 맞게 글을 찾아와야하는데, 중복이 되면 안됨.
        return  null;
    }


}
