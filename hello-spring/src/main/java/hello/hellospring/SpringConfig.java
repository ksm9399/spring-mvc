package hello.hellospring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;

@Configuration
public class SpringConfig {

  // private DataSource dataSource;
  // private EntityManager em;
  private final MemberRepository memberRepository;

  public SpringConfig(
    // DataSource dataSource
    // EntityManager em
    MemberRepository memberRepository
  ) {
    // this.dataSource = dataSource;
    // this.em = em;
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository);
  }

  // @Bean
  // public TimeTraceAop timeTraceAop() {
  //   return new TimeTraceAop();
  // }

  // @Bean
  // public MemberRepository memberRepository() {
  //   // return new MemoryMemberRepository();
  //   // return new JdbcMemberRepository(dataSource);
  //   // return new JdbcTemplateMemberRepository(dataSource);
  //   return new JpaMemberRepository(em);
  // }
}
