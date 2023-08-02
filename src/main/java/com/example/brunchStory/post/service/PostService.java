package com.example.brunchStory.post.service;

import com.example.brunchStory.config.domain.dto.SubjectDto;
import com.example.brunchStory.email.service.EmailService;
import com.example.brunchStory.member.domain.entity.Member;


import com.example.brunchStory.member.domain.response.MemberAllResponse;
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
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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


    public void write(Long memberId, PostRequest postRequest){
        Member member = Member.builder().id(memberId).build();
        Subject subject = subjectService.findById(postRequest.getSubjectNum());
        System.out.println(subject);
        Post post = postRequest.toEntity(member,subject);
        postRepository.save(post);
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
        return postRepository.findAllByCondition(pageRequest,postCondition);
    }

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

    public void sendMailTest(){
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

            emailService.sendForSubscriber(member.getEmail(),content);
        }
    }
        // 해당 토픽을 좋아하는 사람들의 email list를 가져와야한다. ( 멤버 서비스)
        //

}
    // 해당 토픽을 좋아하는 사람들의 email list를 가져와야한다. ( 멤버 서비스)
    //
