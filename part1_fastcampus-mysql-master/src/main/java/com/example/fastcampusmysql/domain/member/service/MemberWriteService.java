package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 서비스의 크기가 커짐에 따라 read 에 필요한 의존성과 write 에 필요한 의존성들이 점점 달라진다.
@RequiredArgsConstructor
@Service
public class MemberWriteService {

    final private MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public Member create(RegisterMemberCommand command){
        /**
         * 목표 - 회원정보(이메일, 닉네임, 생년월일)을 등록한다.
         *      - 닉네임은 10자를 넘길 수 없다.
         * 파라미터 - memberRegisterCommand
         * val member = Member.of(memberRegisterCommand);
         * memberRepository.save();
         */

        var member = Member.builder()
                .nickname(command.nickName())
                .birthday(command.birthday())
                .email(command.email())
                .build();

        var savedMember = memberRepository.save(member);
        saveMemberNicknameHistory(savedMember);
        return savedMember;
    }

    public void changeNickname(Long memberId, String nickname){
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);

        memberRepository.save(member);

        // TODO : 변경내역 히스토리 저장
        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        MemberNicknameHistory history = MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }


}


