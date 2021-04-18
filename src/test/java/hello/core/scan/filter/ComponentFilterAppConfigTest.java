package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {

    @Test
    void filterSacn() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);

        assertThat(beanA).isNotNull();
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
            // includeFilters -> beanA가 등록
            // excludeFilters -> beanB 등록제외

            /*
            FilterType 5가지 옵션
            - ANNOTION : 기본값, 어노테이션을 인식해서 동작
                ex) org.example.SomeAnnotation
            - ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작
                ex) org.example.SomeClass
            - ASPECT : AspectJ 패턴을 사용
                ex) org.example.*Service*
            - REGEX : 정규식 사용
                ex) org\.example\.Default.*
            - CUSTOM : TypeFilter라는 인터페이스를 구현하여 처리
                ex) org.example.MyTypeFilter

            ====> 보통 ANNOTATION 을 주로 사용한다.
             */
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {
    }
}
