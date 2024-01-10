package hello.hellospring.controller;

import org.springframework.stereotype.Controller;

import hello.hellospring.service.MemberService;

/*
 * 컴포넌트 스캔 원리
 * @Component 애노테이션이 있으면 스프링 빈으로 자동 등록
 * @Controller 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문
 * @Component 를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록됨.(@Controller, @Service, @Repository)
 */
@Controller
public class MemberController {

  private final MemberService memberService;

  /*
   * 생성자가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
   * 객체의 의존관계를 외부에서 넣어줌(DI - Dependency Injection) 의존성 주입이라 함
   * 생성자가 1개만 있으면 @Autowired 생략 가능
   */
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

}
