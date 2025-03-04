package spring.spring_mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import spring.spring_mvc.member.MemberRepository;
import spring.spring_mvc.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
  // 이전에 수동으로 설정한 AppConfig를 제외하기 위해 excludeFilters를 사용.
  excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

  // 수동 빈 등록 vs 자동 빈 등록시, 수동 빈 등록이 우선권을 가짐 - ovveride
  @Bean(name = "memoryMemberRepository")
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
