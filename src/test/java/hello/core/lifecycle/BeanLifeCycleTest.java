package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetwordClient client = ac.getBean(NetwordClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // destoryMethod = "(inferred)" (추론)이 기본값으로 등록이 돼있음. 'close', 'shutdown'라는 이름의 메서드를 자동 호출해줌(이름 그대로 종료 메서드를 추론해서 호출)
        // 따라서 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작함.
        // 추론 기능을 사용하기 싫으면 destroyMethod="" 처럼 공백을 지정
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetwordClient networdClient() {
            NetwordClient networdClient = new NetwordClient();
            networdClient.setUrl("http://hello-spring.dev");
            return networdClient;
        }
    }
}
