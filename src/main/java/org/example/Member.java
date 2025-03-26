package org.example;

import java.time.LocalDateTime;

public class Member {

    private final int id;
    private final String loginId;
    private final String loginPw;
    private final String name;
    private final LocalDateTime regDate;

    public Member(int id, String loginId, String loginPw, String name, LocalDateTime regDate) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }
}
