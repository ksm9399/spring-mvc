package spring.spring_mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.spring_mvc.repository.MemberRepository;
import spring.spring_mvc.repository.MemoryMemberRepository;
import spring.spring_mvc.service.MemberService;

@Configuration
public class SpringConfig {

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
