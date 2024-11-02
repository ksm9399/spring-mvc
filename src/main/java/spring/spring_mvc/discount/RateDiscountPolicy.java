package spring.spring_mvc.discount;

import spring.spring_mvc.member.Grade;
import spring.spring_mvc.member.Member;

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
