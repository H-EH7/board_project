package eh7.board.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member signUp(Member member) {
        return memberRepository.save(member);
    }

    public boolean duplicationCheck(String userId) {
        Optional<Member> result = memberRepository.findByUserId(userId);
        if (result.isPresent()) {
            return true;
        }

        return false;
    }
}
