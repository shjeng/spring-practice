package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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

    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 이 경우 수동 빈 등록이 우선권을 가짐(수동 빈이 자동 빈을 오버라이딩 해버린다.)
    // 그래서 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.
    // spring.main.allow-bean-definition-overriding=false 기본 값, 충돌나지 않게 하려면 true로 바꾸어줌
}
