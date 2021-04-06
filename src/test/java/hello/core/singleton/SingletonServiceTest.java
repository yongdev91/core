package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonServiceTest {

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
        // private로 생성자 접근을 막음, 오류
        // new SingletonService()

        // 1. 조회 : 호출할때마다 같은 값을 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        // 2. 조회 : 호출할때마다 같은 값을 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은지 확인
        System.out.println("singletomService1 : " + singletonService1);
        System.out.println("singletomService2 : " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할때마다 같은 객채를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회 : 호출할때마다 같은 객채를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은지 확인
        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService2 : " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
