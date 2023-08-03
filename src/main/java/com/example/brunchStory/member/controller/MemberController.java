package com.example.brunchStory.member.controller;

import com.example.brunchStory.config.auth.AuthService;
import com.example.brunchStory.member.domain.entity.WriterApply;
import com.example.brunchStory.member.domain.request.LoginRequest;

import com.example.brunchStory.member.domain.request.WriterApplyRequest;

import com.example.brunchStory.member.domain.response.*;


import com.example.brunchStory.member.domain.response.LoginResponse;
import com.example.brunchStory.member.domain.response.MemberAllResponse;
import com.example.brunchStory.member.domain.response.MemberResponse;
import com.example.brunchStory.member.service.AlarmService;

import com.example.brunchStory.member.service.MemberService;
import com.example.brunchStory.member.service.SubScribeService;
import com.example.brunchStory.member.service.WriterApplyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.brunchStory.member.domain.request.SignupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final AlarmService alarmService;
    private final AuthService authService;
    private final SubScribeService subScribeService;
    private final WriterApplyService writerApplyService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return memberService.login(loginRequest);
    }



    // 회원가입

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody SignupRequest signupRequest) {
        memberService.insert(signupRequest);
    }

    // 회원탈퇴
    @DeleteMapping
    public void delete(
                       @RequestHeader("Authorization")String token) {
        Map<String, Object> data = authService.getClaims(token.replace("Bearer ", ""));
        Long memberId = ((Integer) data.get("memberId")).longValue();
            memberService.delete(memberId);
        }

    // 멤버 찾기
    @GetMapping("/member/{id}")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    public MemberResponse findByMember(@PathVariable("id") Long id) {

        return memberService.findByMember(id);
    }

    // 저자 찾기
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    @GetMapping("/author/{id}")
    public AuthorResponse findByAuthor(@PathVariable("id") Long id) {
        return memberService.findByAuthor(id);
    }

    // 전체 찾기
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    public Page<MemberAllResponse> findAllMember(
            @RequestParam(required = false,defaultValue = "0",name = "page")
                                                  Integer page,
          @RequestParam(required = false,defaultValue = "10",name = "size")
                                                  Integer size) {
        return memberService.findAllMember(PageRequest.of(page,size));
    }

    // 관심사 비율 찾기
    @GetMapping("/interest")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    public MemberInterestResponse interestRatio() {
        return memberService.findInterestRank();
    }

    /////////////////

    @PostMapping("/alarm/{authorId}")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    public void Alarm(@PathVariable Long authorId,
                      @RequestHeader("Authorization")String token) {

        Map<String, Object> data = authService.getClaims(token.replace("Bearer ", ""));
        Long memberId = ((Integer) data.get("memberId")).longValue();

        alarmService.insertAlarm(authorId, memberId);
    }

    //////////////////

    @PostMapping("/subscribe/{authorId}")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    public void subscribe(@PathVariable Long authorId,
                          @RequestHeader("Authorization")String token) {
        Map<String, Object> data = authService.getClaims(token.replace("Bearer ", ""));
        Long memberId = ((Integer) data.get("memberId")).longValue();

        subScribeService.subscribe(authorId, memberId);
    }

    ///////////////////
    /*

    작가신청 및 허용

    */

    //전체 찾기
    @GetMapping("/application")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<WriterApplyResponse> getWriterApplies() {
        return writerApplyService.findAll();


    }
    // 한명 찾기
    @GetMapping("/application/{applicantId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public WriterApplyResponse getWriterAppliesForMember(@PathVariable(name = "applicantId") Long applicantId) {
        return writerApplyService.findByApplicantResponse(applicantId);


    }
    // 작가 신청
    @PostMapping("/application")
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public void saveWriter(@RequestBody WriterApplyRequest request,
                           @RequestHeader("Authorization")String token) {
        Map<String, Object> data = authService.getClaims(token.replace("Bearer ", ""));
        Long memberId = ((Integer) data.get("memberId")).longValue();

        writerApplyService.saveWriter(request, memberId);
    }

    //신청 삭제

    @DeleteMapping("/application/{applicantId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteWriterApply(@PathVariable Long applicantId,
                                  @RequestHeader("Authorization")String token) {

        Map<String, Object> data = authService.getClaims(token.replace("Bearer ", ""));
        Long memberId = ((Integer) data.get("memberId")).longValue();

        writerApplyService.deleteWriterApply(applicantId, memberId);
    }
    //

    @PutMapping("/application/{applicantId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void approveWriterApply(
            @PathVariable(name = "applicantId") Long applicantId) {
        writerApplyService.approveWriterApply(applicantId);
    }

}