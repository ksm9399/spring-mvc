package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {


  /*
   * 스프링 빈의 이벤트 라이프사이클
   * 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
   */
  @Test
  void lifeCycleTest() {
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

    NetworkClient networkClient = ac.getBean(NetworkClient.class);

    ac.close(); // 스프링 컨테이너를 종료
  }

  @Configuration
  static class LifeCycleConfig {

    @Bean
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();  // 객체를 생성하는 단계에 url이 없다. 그래서 콘솔 출력시 null값이 뜸
      networkClient.setUrl("http://hello-spring.dev");
      return networkClient;
    }
  }
}
