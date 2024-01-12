package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

  /*
   * 실무에서는 동시성 문제 때문에 ConcurrentHashMap 사용하는것을 권장
   * ConcurrentHashMap이란? (따로 알아보고 정리해보자!)
   */
  private static Map<Long, Member> store = new HashMap<>();

  @Override
  public void save(Member member) {
    store.put(member.getId(), member);
  }

  @Override
  public Member findById(Long memberId) {
    return store.get(memberId);
  }
}
