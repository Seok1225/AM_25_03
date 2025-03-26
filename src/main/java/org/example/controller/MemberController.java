package org.example.controller;

import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private List<Member> members = new ArrayList<>();
    private int memberId = 1;

    public MemberController(Scanner sc) {
        super(sc);

        // 테스트 멤버 생성
        members.add(new Member(memberId++, "user1", "1234", "홍길동", Util.getNow()));
        members.add(new Member(memberId++, "user2", "abcd", "김철수", Util.getNow()));
        members.add(new Member(memberId++, "admin", "admin", "관리자", Util.getNow()));
    }

    public void handleCommand(String cmd) {
        if (cmd.equals("member join")) {
            join();
        } else if (cmd.equals("member login")) {
            login();
        } else if (cmd.equals("member logout")) {
            logout();
        } else {
            System.out.println("지원하지 않는 member 명령어입니다.");
        }
    }

    public void join() {
        String loginId;

        while (true) {
            System.out.print("로그인 ID: ");
            loginId = sc.nextLine().trim();

            boolean exists = false;
            for (Member m : members) {
                if (m.getLoginId().equals(loginId)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("이미 존재하는 ID입니다.");
            } else {
                break;
            }
        }

        String loginPw;
        while (true) {
            System.out.print("비밀번호: ");
            loginPw = sc.nextLine().trim();
            System.out.print("비밀번호 확인: ");
            String loginPwConfirm = sc.nextLine().trim();

            if (loginPw.equals(loginPwConfirm)) break;
            System.out.println("비밀번호가 일치하지 않습니다.");
        }

        System.out.print("이름: ");
        String name = sc.nextLine().trim();

        members.add(new Member(memberId, loginId, loginPw, name, Util.getNow()));
        System.out.println(memberId + "번 회원이 가입되었습니다.");
        memberId++;
    }

    public void login() {
        if (loginedMember != null) {
            System.out.println("이미 로그인되어 있습니다: " + loginedMember.getName());
            return;
        }

        System.out.print("로그인 ID: ");
        String loginId = sc.nextLine().trim();
        System.out.print("비밀번호: ");
        String loginPw = sc.nextLine().trim();

        for (Member m : members) {
            if (m.getLoginId().equals(loginId) && m.getLoginPw().equals(loginPw)) {
                loginedMember = m;
                System.out.println(loginedMember.getName() + "님 환영합니다!");
                return;
            }
        }

        System.out.println("로그인 정보가 일치하지 않습니다.");
    }

    public void logout() {
        if (loginedMember == null) {
            System.out.println("로그인 상태가 아닙니다.");
        } else {
            System.out.println(loginedMember.getName() + "님 로그아웃 되었습니다.");
            loginedMember = null;
        }
    }

    public List<Member> getMembers() {
        return members;
    }
}
