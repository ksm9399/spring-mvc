package spring.spring_mvc.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import spring.spring_mvc.member.Grade;
import spring.spring_mvc.member.Member;
import spring.spring_mvc.member.MemberService;
import spring.spring_mvc.member.MemberServiceImpl;

public class OrderServiceTest {

  MemberService memberService = new MemberServiceImpl();
  OrderService orderService = new OrderServiceImpl();

  @Test
  void createOrder() {
    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);

    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10000);

    Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }
}
