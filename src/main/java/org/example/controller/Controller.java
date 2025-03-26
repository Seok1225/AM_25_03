package org.example.controller;

import org.example.dto.Member;

import java.util.Scanner;

public abstract class Controller {

    protected Scanner sc;
    protected Member loginedMember;

    public Controller(Scanner sc) {
        this.sc = sc;
    }

    public Member getLoginedMember() {
        return loginedMember;
    }

    public void setLoginedMember(Member member) {
        this.loginedMember = member;
    }
}
