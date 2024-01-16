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

    /*
     * 설정 정보 사용 특징
     * 메서드 이름을 자유롭게 줄 수 있다.
     * 스프링 빈이 스프링 코드에 의존하지 않는다
     * 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();  // 객체를 생성하는 단계에 url이 없다. 그래서 콘솔 출력시 null값이 뜸
      networkClient.setUrl("http://hello-spring.dev");
      return networkClient;
    }
  }
}
