package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Component 어노테이션이 붙은 클래스는 스프링 빈으로 등록해줌.
        basePackages = "hello.core.member", // 탐색할 패키지의 시작 위치를 지정함. 이 패키지를 포함해서 하위 패키지를 모두 탐색. {"", ""} 여러개 지정 가능
//        basePackageClasses = AutoAppConfig.class, 지정한 클래스의 패키지를 탐색 시작 위로 지정, 이 경우엔 hello.core부터 탐색
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // @Configuration 어노테이션은 제외한다.

        // 지정하지 않으면  @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨.
        // 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이 관례
)
public class AutoAppConfig {
}
