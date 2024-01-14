package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {

  @Test
  void filterScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

    BeanA beanA = ac.getBean("beanA", BeanA.class);
    Assertions.assertThat(beanA).isNotNull();

    org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
      ac.getBean("beanB", BeanB.class);
    });
  }

  @Configuration
  /*
   * includeFilters에 MyIncludeComponent 애노테이션을 추가해서 BeanA가 스프링 빈에 등록된다.
   * excludeFilters에 MyExcludeComponent 애노테이션을 추가해서 BeanB는 스프링 빈에 등록되지 않는다.
   *
   * FilterType 5가지 옵션
   * 1. ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
   * 2. ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
   * 3. ASPECTJ: AspectJ 패턴 사용
   * 4. REGEX: 정규 표현식
   * 5. CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리
   */
  @ComponentScan(
    includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
    excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
  )
  static class ComponentFilterAppConfig {

  }
}
