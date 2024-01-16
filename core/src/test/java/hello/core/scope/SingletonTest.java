package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration.AnnotationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/*
 * 빈 스코프란? 빈이 존재할 수 있는 범위
 * 싱글톤 - 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프
 * 프로토타입 - 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프
 * 웹 관련 스코프
 *  - request : 웹 요청이 들어오고 나갈때 까지 유지되는 스코프
 *  - session : 웹 세션이 생성되고 종료될 때 까지 유지되는 스코프
 *  - application : 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프
 */
public class SingletonTest {

  // 싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환
  // 프로토타입 스코프를 스프링 컨테이너에 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서 반환

  @Test
  public void singletonBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
    SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
    SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

    System.out.println("singletonBean1 = " + singletonBean1);
    System.out.println("singletonBean2 = " + singletonBean2);
    Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
    ac.close();
  }

  @Scope("singleton")
  static class SingletonBean {

    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init()");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean.destroy()");
    }
  }
}
