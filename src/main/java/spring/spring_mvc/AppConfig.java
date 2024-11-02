package spring.spring_mvc;

import spring.spring_mvc.discount.DiscountPolicy;
import spring.spring_mvc.discount.FixDiscountPolicy;
import spring.spring_mvc.discount.RateDiscountPolicy;
import spring.spring_mvc.member.MemberRepository;
import spring.spring_mvc.member.MemberService;
import spring.spring_mvc.member.MemberServiceImpl;
import spring.spring_mvc.member.MemoryMemberRepository;
import spring.spring_mvc.order.OrderService;
import spring.spring_mvc.order.OrderServiceImpl;

public class AppConfig {

  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  public OrderService orderService() {
    return new OrderServiceImpl(
      memberRepository(),
      discountPolicy()
    );
  }

  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public DiscountPolicy discountPolicy() {
    // return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }
}
