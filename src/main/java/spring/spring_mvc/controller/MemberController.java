package spring.spring_mvc.controller;

import org.springframework.stereotype.Controller;

import spring.spring_mvc.service.MemberService;

@Controller
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
}
