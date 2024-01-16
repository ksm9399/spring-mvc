package hello.core.lifecycle;

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

  // 의존 관계 주입이 끝나면 호출해 줌
  public void init() throws Exception {
    System.out.println("NetworkClient.init()");
    connect();
    call("초기화 연결 메세지");
  }

  // 빈이 종료될때 호출 됨
  public void close() throws Exception {
    System.out.println("NetworkClient.close()");
    disconnect();
  }
}
