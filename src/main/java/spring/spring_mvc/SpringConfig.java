package spring.spring_mvc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import spring.spring_mvc.repository.JdbcMemberRepository;
import spring.spring_mvc.repository.JdbcTemplateMemberRepository;
import spring.spring_mvc.repository.JpaMemberRepository;
import spring.spring_mvc.repository.MemberRepository;
import spring.spring_mvc.repository.MemoryMemberRepository;
import spring.spring_mvc.service.MemberService;

@Configuration
public class SpringConfig {

  private DataSource dataSource;
  private EntityManager em;
  private MemberRepository memberRepository;

  public SpringConfig(
    DataSource dataSource,
    EntityManager em,
    MemberRepository memberRepository
  ) {
    this.dataSource = dataSource;
    this.em = em;
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberService() {
    // return new MemberService(memberRepository());
    return new MemberService(memberRepository);
  }

  // @Bean
  // public MemberRepository memberRepository() {
  //   // return new MemoryMemberRepository();
  //   // return new JdbcMemberRepository(dataSource);
  //   // return new JdbcTemplateMemberRepository(dataSource);
  //   // return new JpaMemberRepository(em);
  // }
}
