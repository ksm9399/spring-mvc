package spring.spring_mvc.discount;

import spring.spring_mvc.member.Grade;
import spring.spring_mvc.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

  private int discountFixAmount = 1000;

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return discountFixAmount;
    }
    else {
      return 0;
    }
  }
}
