package com.example.brunchStory.member.controller;

import com.example.brunchStory.member.domain.request.LoginRequest;

import com.example.brunchStory.member.domain.request.WriterApplyRequest;

import com.example.brunchStory.member.domain.response.*;

import com.example.brunchStory.member.service.MemberService;
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


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

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
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        memberService.delete(id);
    }

    // 멤버 찾기
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    @GetMapping("/{id}/member")
    public MemberResponse findByMember(@PathVariable("id") Long id) {

        return memberService.findByMember(id);
    }

    // 저자 찾기
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    @GetMapping("/{id}/author")
    public AuthorResponse findByAuthor(@PathVariable("id") Long id) {
        return memberService.findByAuthor(id);
    }

    // 전체 찾기
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    @GetMapping
    public Page<MemberAllResponse> findAllMember(
            @RequestParam(required = false,defaultValue = "0",name = "page")
                                                  Integer page,
          @RequestParam(required = false,defaultValue = "10",name = "size")
                                                  Integer size) {
        return memberService.findAllMember(PageRequest.of(page,size));
    }

    // 관심사 비율 찾기
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    @GetMapping("/interest")
    public MemberInterestResponse interestRatio() {
        return memberService.findInterestRank();
    }


    @GetMapping("test1")
    @PreAuthorize("hasRole('ROLE_AUTHOR')")
    public void writerTest() {
        System.out.println("작가네요");
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_MEMBER')")
    public void twoRoleTest() {
        System.out.println("당신은 두개의 역할을 가지고 있군요");
    }

    @GetMapping("test3")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void adminTest() {
        System.out.println("관리자시군요");
    }

}