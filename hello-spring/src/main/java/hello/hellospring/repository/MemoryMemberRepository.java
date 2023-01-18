package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //optional > null일 경우를 대비하기위해 미리 감싸주는거야
    }

    @Override
    public Optional<Member> findByName(String name) {
        //lamda활용..찾아보기
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //여기까지하고, 이제 이거 검증해야하니 test를 해봐야하니! 테스트 케이스 작성해보기

    public void clearStore() {
        store.clear();
    }
}
