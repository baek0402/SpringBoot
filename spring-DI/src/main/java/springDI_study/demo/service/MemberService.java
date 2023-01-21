package springDI_study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springDI_study.demo.domain.Member;
import springDI_study.demo.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*회원가입*/
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
