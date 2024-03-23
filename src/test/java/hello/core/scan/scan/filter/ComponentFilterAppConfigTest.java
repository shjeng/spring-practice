package hello.core.scan.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
            // includeFilter에 MyIncludeComponent 애노테이션을 추가해서 BeanA가 스프링 빈에 등록된다.
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            // excludeFilters에 MyExcludeComponent 애노테이션을 추가해서 BeanB가 스프링 빈에 등록되지 않는다.
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)

//            excludeFilters = {
//                    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
//                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
//            } 이런 식으로 BeanA도 제외할 수 있음.

            /*
            * ANNOTATION: 기본값, 애노테이션을 인식해서 동작함. (위 type = FilterType.ANNOTATION 제거해도 됨)
            * ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
            * ASPECTJ: AspectJ 패턴 사용
            * REGEX: 정규 표현식
            * COSTOM: 'TypeFilter' 이라는 이넡페이스를 구현해서 처리
            * */

            // 스프링 부트는 컴포넌트 스캔을 기본으로 제공하는데, 개인적으로는 옵션을 변경하면서 사용하기 보다는
            // 스프링의 기본 설정에 최대한 맞추어 사용하는 것을 권장하고 선호하는 편이다.
    )
    static class ComponentFilterAppConfig {

    }
}
