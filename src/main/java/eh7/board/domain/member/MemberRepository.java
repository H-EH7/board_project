package eh7.board.domain.member;

import java.util.Optional;

/**
 * 회원 저장소 인터페이스
 */
public interface MemberRepository {
    /**
     * 새 회원 저장
     */
    Member save(Member member);

    /**
     * 회원 조회
     */
    Optional<Member> findById(Long id);
}
