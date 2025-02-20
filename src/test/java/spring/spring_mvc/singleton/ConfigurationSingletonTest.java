package spring.spring_mvc.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.spring_mvc.AppConfig;
import spring.spring_mvc.member.MemberRepository;
import spring.spring_mvc.member.MemberServiceImpl;
import spring.spring_mvc.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

  @Test
  void configurationTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberServiceImpl = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderServiceImpl = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    MemberRepository memberRepository1 = memberServiceImpl.getMemberRepository();
    MemberRepository memberRepository2 = orderServiceImpl.getMemberRepository();

    System.out.println("memberServiceImpl -> memberRepository = " + memberRepository1);
    System.out.println("orderServiceImpl -> memberRepository = " + memberRepository2);
    System.out.println("memberRepository = " + memberRepository);

    Assertions.assertThat(memberServiceImpl.getMemberRepository()).isSameAs(memberRepository);
    Assertions.assertThat(orderServiceImpl.getMemberRepository()).isSameAs(memberRepository);
  }


  @Test
  void configurationDeep() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    AppConfig bean = ac.getBean(AppConfig.class);

    System.out.println("bean = " + bean.getClass());
  }
}
