package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test끝날때마다 리포지토리ㅡ를 깔끔하게 지워주는거래
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //일단 여기까지는 작동이 잘 되니까 다음으로 이어서

        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result " + (result == member) );
        //근데 이렇게 할 수는 없으니까
        /* import org.junit.jupiter.api.Assertions; */
        //Assertions.assertEquals(member, result);
        //출력은 되지않으나 save() 옆에 녹색불이 떠있음
        //실패하면 빨간불이 뜨지요,,

        //Assertions.assertThat(member).isEqualTo(result);
        /* import static org.assertj.core.api.Assertions.*; 하면 Assertions 없애도돼*/
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //shift+f6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        //Optical이기에 get() 을 꼭 해줘야함
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
    //근데 이 상태로 전체 class를 검사해보면 에러가 난다!!
    //일단 순서가 문제여서인데, (findAll이 먼저 되어버리니까)
    //findbyname 할때 findall 에서 저장된 spring1이 나와서 그런거래
}
