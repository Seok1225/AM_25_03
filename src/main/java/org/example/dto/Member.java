package org.example.dto;
import java.time.LocalDateTime;

public class Member {

    private int id;
    private String loginId;
    private String loginPw;
    private String name;
    private LocalDateTime regDate;

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
