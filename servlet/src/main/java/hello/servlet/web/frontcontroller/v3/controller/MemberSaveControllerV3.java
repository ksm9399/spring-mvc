package hello.servlet.web.frontcontroller.v3.controller;

import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3 {

  MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public ModelView process(Map<String, String> paramMap) {
    String userName = paramMap.get("username");
    int age = Integer.parseInt(paramMap.get("age"));

    Member member = new Member(userName, age);
    memberRepository.save(member);

    ModelView mv = new ModelView("save-result");
    mv.getModal().put("member", member);
    return mv;
  }
}
