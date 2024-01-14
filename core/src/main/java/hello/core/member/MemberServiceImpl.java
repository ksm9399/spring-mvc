package hello.core.member;

/*
 * [회원 도메인 설계의 문제점]
 * OCP 원칙을 잘 준수하고 있는가?
 * DIP를 잘 지키고 있는가?
 *
 * OCP(Open Closed Principle - 개방 폐쇄 원칙)란?
 * - 기존의 코드를 변경하지 않으면서, 기능을 추가할 수 있도록 설계가 되어야 한다는 원칙
 * - 확장에 대해서는 개방적(open), 수정에 대해서는 폐쇄적(closed)
 *
 * DIP(Dependency Inversion Principle - 의존 역전 원칙)란?
 * - 객체에서 어떤 Class를 참조해서 사용해야하는 상황이 생긴다면, 그 Class를 직접 참조하는 것이 아니라 그 대상의 상위 요소(추상 클래스 or 인터페이스)로 참조하라는 원칙
 */
public class MemberServiceImpl implements MemberService {

  // 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음, (MemberRepository - 인터페이스, MemoryMemberRepository - 구현체)
  // private final MemberRepository memberRepository = new MemoryMemberRepository();

  // AppConfig 파일을 통해 외부에서 의존성을 주입해주기 때문에 DIP원칙 지킴 - 인터페이스에만 의존
  private final MemberRepository memberRepository;

  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
