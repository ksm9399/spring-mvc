package spring.spring_mvc.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.spring_mvc.AutoAppConfig;
import spring.spring_mvc.member.MemberService;

public class AutoAppConfigTest {

  @Test
  void basicSacn() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    MemberService memberService = ac.getBean(MemberService.class);
    Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
  }
}
