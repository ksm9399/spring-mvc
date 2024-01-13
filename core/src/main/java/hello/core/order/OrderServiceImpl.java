package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

  /*
   * 문제점
   * DIP 위반 - 추상(DiscountPolicy - 인터페이스) 뿐만 아니라 구체(FixDiscountPolicy, RateDiscountPolicy - 구현) 클래스에도 의존하고 있다.
   * OCP 위반 - 정책변경으로 인해 FixDiscountPolicy에서 RateDiscountPolicy로 구현 클래스를 변경하는 순간 클라이언트 코드인 OrderServiceImpl클래스 수정이 발생
   */
  // private final MemberRepository memberRepository = new MemoryMemberRepository();
  // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

  // AppConfig 파일을 통해 외부에서 의존성을 주입해주기 때문에 DIP원칙 지킴 - 인터페이스에만 의존
  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
