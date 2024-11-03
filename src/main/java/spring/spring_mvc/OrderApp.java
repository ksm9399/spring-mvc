package spring.spring_mvc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.spring_mvc.member.Grade;
import spring.spring_mvc.member.Member;
import spring.spring_mvc.member.MemberService;
import spring.spring_mvc.member.MemberServiceImpl;
import spring.spring_mvc.order.Order;
import spring.spring_mvc.order.OrderService;
import spring.spring_mvc.order.OrderServiceImpl;

public class OrderApp {

  public static void main(String[] args) {
    // MemberService memberService = new MemberServiceImpl();
    // OrderService orderService = new OrderServiceImpl();

    // AppConfig appConfig = new AppConfig();
    // MemberService memberService = appConfig.memberService();
    // OrderService orderService = appConfig.orderService();

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
    OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);

    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10000);

    System.out.println("order = " + order);
    // System.out.println("order = " + order.calculatePrice());
  }
}