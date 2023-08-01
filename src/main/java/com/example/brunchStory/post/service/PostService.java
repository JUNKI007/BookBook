package com.example.brunchStory.post.service;

import com.example.brunchStory.member.domain.entity.Member;
import com.example.brunchStory.member.domain.response.MemberResponse;
import com.example.brunchStory.member.service.MemberService;
import com.example.brunchStory.post.domain.dto.PostCondition;
import com.example.brunchStory.post.domain.dto.SubjectWithPost;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.entity.Subject;
import com.example.brunchStory.post.domain.response.PostResponseForMail;
import com.example.brunchStory.post.repository.PostRepository;
import com.example.brunchStory.post.domain.request.PostRequest;
import com.example.brunchStory.post.domain.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final SubjectService subjectService;





    public void write(Long memberId, PostRequest postRequest){

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
    public List<PostResponseForMail> findAllByLike(){

        List<Post> allByLike = postRepository.findAllByLike(LocalDateTime.now().minusDays(1));
        // 현재 시간보다 하루전부터 쓰여진 글만 가져오기.
        // 좋아요가 5이상인 글을 뽑아오기.
        return allByLike.stream().map(PostResponseForMail::new).toList();

    }

    public Map<Long, List<PostResponseForMail>> makeSubjectBox(){
        List<PostResponseForMail> allByLike = findAllByLike();

        List<SubjectWithPost> subjectBox = new ArrayList<>();
        Map<Long, List<PostResponseForMail>> topicMap = new HashMap<>();

        for (PostResponseForMail post:
                allByLike) {
                Long topic = post.getSubjectNum();
                List<PostResponseForMail> topicPosts = topicMap.getOrDefault(topic, new ArrayList<>());
                topicPosts.add(post);
                topicMap.put(topic, topicPosts);
            }

        return topicMap;
        }

        // 해당 토픽을 좋아하는 사람들의 email list를 가져와야한다. ( 멤버 서비스)
        //
    }


