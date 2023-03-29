package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("회원 닉네임을 변경할 수 있다.")
    void testChangeName(){

        // ObjectMother pattern
        var member = MemberFixtureFactory.create();
        var expected = "objMother";

        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }


    @Test
    @DisplayName("회원 닉네임은 10자를 초과할 수 없다.")
    void testNicknameMaxLength(){

        // ObjectMother pattern
        var member = MemberFixtureFactory.create();
        var expected = "12345678901";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {member.changeNickname(expected);});
    }





}