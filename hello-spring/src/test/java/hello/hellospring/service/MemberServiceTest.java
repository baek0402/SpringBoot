package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /*MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();*/
    /**
     * clearStroe을 위해 얘를 선언해줬는데
     * MemberService에서 선언한 memberRepository랑
     * 여기에서 새로 선언한 memberRepository랑 달라.. 뭔가 애매해!
     * 그니까 굳이 저걸 두개를 쓸 이유가 없지, 이걸 어떻게?
     *
     * MembeService에서 선언하는 repository를 수정하러가자!
     */
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach //시작하기 전에 넣어주는거임
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { //과감하게 한글로 적어도된대
        //given 이런 상황에서
        Member member = new Member();
        member.setName("hello");

        //when 이걸 실행했을 때
        Long saveId = memberService.join(member);

        //then 이게 나와야해!
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test //this is for validate Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //이 예외가 발생해야하는걸 기대하고!

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원힙니다요");
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}