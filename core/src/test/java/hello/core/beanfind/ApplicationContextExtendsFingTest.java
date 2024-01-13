package hello.core.beanfind;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

public class ApplicationContextExtendsFingTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면 중복 오류 발생한다.")
  void findBeanByParentTypeDuplicate() {
    // DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
      () -> ac.getBean(DiscountPolicy.class)
    );
  }

  @Test
  @DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
  void findBeanByParentTypeBeanName() {
    DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
    org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("특정 하위 타입으로 조회")
  void findBeanBySubType() {
    RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
    org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회")
  void findAllBeanByParentType() {
    Map<String, DiscountPolicy> beanOfType = ac.getBeansOfType(DiscountPolicy.class);
    org.assertj.core.api.Assertions.assertThat(beanOfType.size()).isEqualTo(2);

    for (String key : beanOfType.keySet()) {
      System.out.println("key = " + key + ", value = " + beanOfType.get(key));
    }
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회 - Object")
  void findAllBeanByObejctType() {
    Map<String, Object> beanOfType = ac.getBeansOfType(Object.class);

    for (String key : beanOfType.keySet()) {
      System.out.println("key = " + key + ", value = " + beanOfType.get(key));
    }
  }

  @Configuration
  static class TestConfig {

    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }
  }
}
