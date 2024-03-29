package hello.hellospring.repository;

import java.util.*;

import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
// @Repository
public class MemoryMemberRepository implements MemberRepository{

  private static Map<Long, Member> store = new HashMap<>();
  private static Long sequnce = 0L;

  @Override
  public Member save(Member member) {
    member.setId(++sequnce);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return  Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
            .filter(member -> member.getName().equals(name))
            .findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  public void clearStory() {
    store.clear();
  }
}
