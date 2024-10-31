package spring.spring_mvc;

import spring.spring_mvc.member.Grade;
import spring.spring_mvc.member.Member;
import spring.spring_mvc.member.MemberService;
import spring.spring_mvc.member.MemberServiceImpl;

public class MemberApp {

  public static void main(String[] args) {
    MemberService memberService = new MemberServiceImpl();
    Member member = new Member(1L, "memberA", Grade.VIP);

    memberService.join(member);

    Member findMember = memberService.findMember(1L);

    System.out.println("new Member = " + member.getName());
    System.out.println("findMember = " + findMember.getName());
  }
}
