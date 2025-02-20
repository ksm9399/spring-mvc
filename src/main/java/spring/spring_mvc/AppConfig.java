package spring.spring_mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.spring_mvc.discount.DiscountPolicy;
import spring.spring_mvc.discount.FixDiscountPolicy;
import spring.spring_mvc.discount.RateDiscountPolicy;
import spring.spring_mvc.member.MemberRepository;
import spring.spring_mvc.member.MemberService;
import spring.spring_mvc.member.MemberServiceImpl;
import spring.spring_mvc.member.MemoryMemberRepository;
import spring.spring_mvc.order.OrderService;
import spring.spring_mvc.order.OrderServiceImpl;

@Configuration
public class AppConfig {

  @Bean
  public MemberService memberService() {
    System.out.println("call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call AppConfig.orderService");
    return new OrderServiceImpl(
      memberRepository(),
      discountPolicy()
    );
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("call AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    // return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }
}
