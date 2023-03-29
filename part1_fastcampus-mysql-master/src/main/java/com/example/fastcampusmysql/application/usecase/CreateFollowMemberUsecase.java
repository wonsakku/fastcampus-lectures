package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateFollowMemberUsecase {

    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId){
        /**
         * 1. 입력받은 memberId로 회원조회
         * 2. FollowWriteService.create() 호출
         */

        MemberDto fromMember = memberReadService.getMember(fromMemberId);
        MemberDto toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }
}
