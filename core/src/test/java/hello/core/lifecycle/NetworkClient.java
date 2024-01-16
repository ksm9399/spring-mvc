package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/*
 * InitializingBean, DisposableBean 단점
 * 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존
 * 초기화, 소멸 메서드의 이름을 변경할 수 없다.
 * 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.
 * *** 인터페이스를 사용하는 초기화, 종료 방법은 스프링 초창기에 나온 방법들이고, 지금은 다음의 더 나은 방법들이 있어서 거의 사용하지 않는다
 */
public class NetworkClient {

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);

  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void connect() {
    System.out.println("connect = " + url);
  }

  public void call(String message) {
    System.out.println("call = " + url + ", message = " + message);
  }

  public void disconnect() {
    System.out.println("close = " + url);
  }

  /*
   * @PostConstruct, @PreDestroy 애노테이션 특징
   * 최신 스프링에서 가장 권장하는 방법
   * 애노테이션 하나만 붙이면 되므로 매우 편리
   * 스프링에 종속적인 기술이 아니라 자바 표준이다.
   * 컴포넌트 스캔과 잘 어울린다.
   *
   * 유일한 단점은 외부 라이브러리에는 적용하지 못한다는 것
   * 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean 의 initMethod , destroyMethod를 사용
   */

  // 의존 관계 주입이 끝나면 호출해 줌
  @PostConstruct
  public void init() throws Exception {
    System.out.println("NetworkClient.init()");
    connect();
    call("초기화 연결 메세지");
  }

  // 빈이 종료될때 호출 됨
  @PreDestroy
  public void close() throws Exception {
    System.out.println("NetworkClient.close()");
    disconnect();
  }
}
