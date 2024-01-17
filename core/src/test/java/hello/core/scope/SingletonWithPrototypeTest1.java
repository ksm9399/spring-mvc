package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {


  @Test
  void prototypeFind() {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.addCount();
    Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    prototypeBean2.addCount();
    Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);

    // 프로토타입 빈 생성되니까 각각의 주소값을 가진 빈이 생성 (2개)
    System.out.println("prototypeBean1 = " + prototypeBean1);
    System.out.println("prototypeBean2 = " + prototypeBean2);
  }

  // 싱글톤 빈과 프로토 타입 빈 함께 사용 테스트
  @Test
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    Assertions.assertThat(count1).isEqualTo(1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    Assertions.assertThat(count2).isEqualTo(2);

    // 클라이언트 빈에서 프로토타입을 주입받음
    // 주입받은 시점에 프로토타입의 빈이 이미 생성되어있음
    // 생성된 프로토타입의 빈이 싱글톤 빈으로 사용됨 결국 싱글톤 빈으로 성격이 변하는 것?
    System.out.println("clientBean1 = " + clientBean1);
    System.out.println("clientBean2 = " + clientBean2);
  }

  @Scope("prototype")
  static class PrototypeBean {
    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init = " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }

  @Scope("singleton")
  static class ClientBean {
    private final PrototypeBean prototypeBean;  // 생성시점에 이미 주입됨

    public ClientBean(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }

    public int logic() {
      prototypeBean.addCount();

      int count = prototypeBean.getCount();

      return count;
    }
  }
}
