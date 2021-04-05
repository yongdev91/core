package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.plaf.nimbus.State;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : A사용자 10000 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B사용자 20000 주문
        statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A 주문금액 조회
        int price = statefulService1.getPrice();

        // ThreadA : 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원이 출력
        System.out.println("## price : " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
