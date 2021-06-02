package hello.core;

import hello.core.ifecycle.NetworkClient;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class LifeCycleTest {

    /**
     * 객체를 생성하는 단계에는 url이 없고 생성한 이후 url을 주입하게된다.
     * 스프링빈은 다음과 같은 라이프 사이클을 가지고 있다.
     * 객체생성 -> 의존관계 주입
     *
     * 따라서 초기화 작업은 의존관계 주입까지 마친 이후 호출해야 한다.
     * 의존관계 주입이 끝난 시점은 어떻게 알 수 있을까?
     *
     * 스프링은 의존관계주입이 끝날 떄 콜백메서드를 통해 초기화 시점을 다양하게 알려주고 있다.
     *
     * 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
     * - 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
     * - 소멸전 콜백 : 빈이 소멸되기 직전에 호출
     */


    @Test
    public void networkClient() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://hello-spring.dev");
            return networkClient;
        }
    }
}
