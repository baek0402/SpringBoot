package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //얘 바꿔주기
    private final MemberRepository memberRepository;//= new MemoryMemberRepository();

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        //ctrl + T 해서 추출하기 해서 밑에 함수로 뺐음
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        //Optional<Member> result = memberRepository.findByName(member.getName());

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원힙니다요");
                });
        //optional을 통해 null을 감싸서 반환하니까 이렇게..

    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
        //findAll 반환 타입 (MemberRepository)가 List니까 그냥 그대로 return
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
