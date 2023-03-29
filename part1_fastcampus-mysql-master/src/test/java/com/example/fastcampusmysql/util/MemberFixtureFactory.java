package com.example.fastcampusmysql.util;

import com.example.fastcampusmysql.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {


    public static Member create(){
        var param = new EasyRandomParameters();
        return new EasyRandom(param).nextObject(Member.class);
    }


    public static Member create(Long seed){
        var param = new EasyRandomParameters();
        param.setSeed(seed);
        return new EasyRandom(param).nextObject(Member.class);
    }
}
