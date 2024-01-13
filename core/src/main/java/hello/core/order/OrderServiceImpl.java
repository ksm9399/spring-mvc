package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();
  /*
   * 문제점
   * DIP 위반 - 추상(인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있다.
   * OCP 위반 - 정책변경으로 인해 FixDiscountPolicy에서 RateDiscountPolicy로 구현 클래스를 변경하는 순간 OrderServiceImpl클래스 수정이 발생
   */
  // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
  private DiscountPolicy discountPolicy;

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
