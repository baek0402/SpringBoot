package springDI_study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springDI_study.demo.repository.JpaMemberRepository;
import springDI_study.demo.repository.MemberRepository;
import springDI_study.demo.repository.MemoryMemberRepository;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    //2.
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        //2.
        return new MemoryMemberRepository();
        //1.MemberRepository는 구현체지요 ㅋ

        //2.
        //return new JpaMemberRepository(em);
    }

    //1.이렇게 자바코드로 직접해주면 처음 시작할 때 스프링 컨테이너에 넣어주게된다!
    //또한 Controller은 그냥 어쩔수없이 저렇게 그대로 @Autowired와 함께 해주면 된다!
}
