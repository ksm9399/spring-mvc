package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

  @Test
  void statefulServiceSingleton() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    // ThreadA : A사용자 10000원 주문
    statefulService1.order("userA", 10000);
    // ThreadB : B사용자 20000원 주문
    statefulService2.order("userB", 20000);

    // ThreadA : 사용자A 주문 금액 조회
    // A사용자 10000원에 대한 금액이 나와야 하는데 중간에 B사용자로 인해 A사용자 금액이 20000만원으로 변경이 됨...
    int price = statefulService1.getPrice();
    System.out.println("price = " + price);

    Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
  }

  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }
}
