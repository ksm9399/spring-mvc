package spring.spring_mvc.member;

public interface MemberRepository {

  void save(Member member);
  Member findById(Long memberId);
}
