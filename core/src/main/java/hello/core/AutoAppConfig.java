package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Configuration
/*
 * @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록
 * @SpringBootApplication 안에 @ComponentScan이 존재, 프로젝트 시작 루트에서 하위 루트까지 컴포넌트를 찾아서 스프링 컨테이너에 빈으로 등록됨
 * @Component : 컴포넌트 스캔에서 사용
 * @Configuration : 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리
 * @Controller : 스프링 MVC 컨트롤러로 인식
 * @Service : 특별한 처리를 하지 않는다. 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움
 * @Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환
 */
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

  /*
   * 중복 빈 등록
   * 수동 빈 등록시 - 수동 빈이 자동 빈을 오버라이딩 해버린다.(수동 빈 등록이 우선권을 가짐)
   */

  // 테스트 코드 실행시 contextLoads()에서 BeanDefinitionOverrideException 에러 발생해서 주석 처리
  // @Bean("memoryMemberRepository")
  // public MemberRepository memberRepository() {
  //   return new MemoryMemberRepository();
  // }
}
