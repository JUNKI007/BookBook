package com.example.brunchStory.post.service;

import com.example.brunchStory.config.domain.dto.SubjectDto;
import com.example.brunchStory.email.service.EmailService;
import com.example.brunchStory.member.domain.entity.Member;


import com.example.brunchStory.member.domain.response.MemberAllResponse;

import com.example.brunchStory.member.service.MemberService;
import com.example.brunchStory.post.domain.dto.PostCondition;
import com.example.brunchStory.post.domain.entity.Post;
import com.example.brunchStory.post.domain.entity.Subject;
import com.example.brunchStory.post.domain.event.PostCreateEvent;
import com.example.brunchStory.post.domain.response.PostResponseForMail;
import com.example.brunchStory.post.repository.PostRepository;
import com.example.brunchStory.post.domain.request.PostRequest;
import com.example.brunchStory.post.domain.response.PostResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final SubjectService subjectService;
    private final EmailService emailService;
    private final ApplicationEventPublisher eventPublisher;
    //글 작성시 이벤트 발생




    public void write(Long memberId, PostRequest postRequest){
        Member member = Member.builder().id(memberId).build();
        Subject subject = subjectService.findById(postRequest.getSubjectNum());
        System.out.println(subject);
        Post post = postRequest.toEntity(member,subject);
        postRepository.save(post);

        eventPublisher.publishEvent(new PostCreateEvent(this, post));
    }


    public void delete(Long postId, Long memberId) throws SQLIntegrityConstraintViolationException {
        Member author = Member.builder().id(memberId).build();
        try {
            int isDone = postRepository.deleteCustom(postId, author);
        }catch (Exception e){
                throw new SQLIntegrityConstraintViolationException("지울 수 없는 글입니다.");
            }

    }
    public List<Post>findAllById(List<Long> postIds){ // 아이디들로 모든 글 찾아오기
        List<Post> allById = postRepository.findAllById(postIds);
        return allById;
    }

    // 이건 책을 발간하기 위한 작업임.


    public Post findById(Long postId){ // 글 하나 찾아오기.
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.orElseThrow(RuntimeException::new);
        return post;
    }
    public PostResponse findByIdCustom(Long postId){



        Optional<Post> byIdCustom = postRepository.findByIdCustom(postId);
        Post post = byIdCustom.orElseThrow(() -> new RuntimeException());
        return new PostResponse(post);
    }
    @Transactional(readOnly = true) // 조건에 맞게 찾아오기
    public Page<PostResponse> findAllByCondition(PostCondition postCondition, PageRequest pageRequest){

        List<Subject> allByIds = subjectService.findAllByIds(postCondition.getInterest());

        return postRepository.findAllByCondition(pageRequest,postCondition,allByIds);
    }

    // 기능 실험 해야함.

    public void savePost(Post post) {
        postRepository.save(post);
    }

    ////////////////////////////////
    @Transactional(readOnly = true)
    public List<PostResponseForMail> findAllByLike(){

        List<Post> allByLike = postRepository.findAllByLike(LocalDateTime.now().minusDays(1));
        // 현재 시간보다 하루전부터 쓰여진 글만 가져오기.
        // 좋아요가 5이상인 글을 뽑아오기.
        return allByLike.stream().map(PostResponseForMail::new).toList();
    }
    //////////////////////////////////////
    private Map<Long, List<PostResponseForMail>> makeSubjectBox(){
        List<PostResponseForMail> allByLike = findAllByLike();

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

    public void sendMail(){
        List<MemberAllResponse> forMailService = memberService.findAllMemberForMail();

        Map<Long, List<PostResponseForMail>> map = makeSubjectBox();

        for (MemberAllResponse member:
             forMailService) {
            List<PostResponseForMail> forMails = new ArrayList<>();

            for (SubjectDto subjectDto:
                 member.getInterests()) {
                List<PostResponseForMail> postResponseForMails = map.getOrDefault(subjectDto.getId(),new ArrayList<>());

                forMails.addAll(postResponseForMails);
            }

            String content = new Gson().toJson(forMails);

            emailService.send("당신이 좋아할만한 글입니다.",member.getEmail(),content);
        }
    }
        // 해당 토픽을 좋아하는 사람들의 email list를 가져와야한다. ( 멤버 서비스)
        //

}



