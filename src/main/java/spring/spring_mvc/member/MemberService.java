package spring.spring_mvc.member;

public interface MemberService {

  void join(Member member);
  Member findMember(Long memberId);
}
