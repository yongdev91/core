package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 구체 클래스는 선언하는 것을 지양해야하지만 테스트 용도로 구체클래스 구현
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 모두 같은 인스턴스를 참고하고 있음
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("meberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // AppConfig도 스프링 빈으로 등록한다.
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        // 출력 : bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$e697fde5
        // AppConfig$$EnhancerBySpringCGLIB : 스프링에서 AppConfig를 상속받아 조작된 AppConfig$$EnhancerBySpringCGLIB를 생성하여 빈으로 등록한다.
        // 내가 직접 만든 클래스는 사실 빈으로 만들어 지지 않고 스프링이 가공하여 빈으로 등록한다.
        // @Configuration 어노테이션이 스프링의 싱글톤이 보장되는 것이다. (@Bean만 등록하면 각각 다른 객체를 생성하게 된다.)
    }
}
