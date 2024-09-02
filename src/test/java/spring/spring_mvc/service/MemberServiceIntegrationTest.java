package spring.spring_mvc.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import spring.spring_mvc.domain.Member;
import spring.spring_mvc.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Test
  void join() {
    // given
    Member member = new Member();
    member.setName("spring");

    // when
      Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    // given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    // when
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    // then
  }


  @Test
  void findMembers() {

  }

  @Test
  void findOne() {

  }
}
