package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/*
 * 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스 생성
 * AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성, 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)
 * 객체를 생성하고 연결하는 역할(AppConfig는)과, 실행하는 역할(MemberServiceImpl, OrderServiceImpl 등) 분리
 */
@Configuration
public class AppConfig {

  // MemoryMemberRepository() 이 부분이 중복 제거
  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  // 고정 할인이 아닌 비율 할인으로 변경
  @Bean
  public DiscountPolicy discountPolicy() {
    // return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }

  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }
}
