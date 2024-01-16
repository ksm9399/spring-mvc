package hello.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

@Component
// @Qualifier("mainDiscountPolicy") // @Primary와 @Qualifier 중 @Qualifier가 우선순위가 높다
// @Primary  // @Autowired 시에 여러 빈이 매칭되면 @Primary 가 우선권을 가진다
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

  private int discountPercent = 10;
  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return price * discountPercent / 100;
    }
    else {
      return 0;
    }
  }
}
