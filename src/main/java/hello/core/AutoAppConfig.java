package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// 기존 코드는 삭제하지 않기 위해서 exclude설정을 추가함
// @ComponentScan 어노테이션은 @Component 라는 어노테이션이 붙은 클래스에 대해 스프링 빈으로 등록해준다.
// @Configuration 어노테이션도 열어보면 @Component 라는 어노테이션이 붙어있어 설정정보를 스프링 빈으로 등록해 주는 것
public class AutoAppConfig {

}
