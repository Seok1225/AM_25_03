package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void makeTestData(List<Article> articles, int startId) {
        LocalDateTime now = Util.getNow();
        LocalDateTime yesterday = now.minusDays(1);
        LocalDateTime threeDaysAgo = now.minusDays(3);

        articles.add(new Article(startId++, "오늘 글 1", "내용 1", now, now));
        articles.add(new Article(startId++, "어제 글", "내용 2", yesterday, yesterday));
        articles.add(new Article(startId++, "3일 전 글", "내용 3", threeDaysAgo, threeDaysAgo));
    }

    public static Article findArticleById(int id, List<Article> articles) {
        for (Article a : articles) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public static void makeTestMemberData(List<Member> members, int startId) {
        members.add(new Member(startId++, "user1", "1234", "홍길동", Util.getNow()));
        members.add(new Member(startId++, "user2", "abcd", "김철수", Util.getNow()));
        members.add(new Member(startId++, "admin", "admin", "관리자", Util.getNow()));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Article> articles = new ArrayList<>();
        List<Member> members = new ArrayList<>();
        Member loginedMember = null;
        int articleId = 1;
        int memberId = 1;

        makeTestData(articles, articleId);
        articleId += 3;
        makeTestMemberData(members, memberId);
        memberId += 3;

        while (true) {
            System.out.print("명령어) ");
            String command = sc.nextLine().trim();

            if (command.equals("exit")) break;

            if (command.equals("member join")) {
                System.out.print("로그인 ID: ");
                String loginId = sc.nextLine().trim();

                boolean exists = false;
                for (Member m : members) {
                    if (m.getLoginId().equals(loginId)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("이미 존재하는 ID입니다.");
                    continue;
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
            } else if (command.equals("member login")) {
                if (loginedMember != null) {
                    System.out.println("이미 로그인되어 있습니다: " + loginedMember.getName());
                    continue;
                }

                System.out.print("로그인 ID: ");
                String loginId = sc.nextLine().trim();
                System.out.print("비밀번호: ");
                String loginPw = sc.nextLine().trim();

                Member found = null;
                for (Member m : members) {
                    if (m.getLoginId().equals(loginId) && m.getLoginPw().equals(loginPw)) {
                        found = m;
                        break;
                    }
                }

                if (found == null) {
                    System.out.println("로그인 정보가 일치하지 않습니다.");
                } else {
                    loginedMember = found;
                    System.out.println(loginedMember.getName() + "님 환영합니다!");
                }
            } else if (command.equals("member logout")) {
                if (loginedMember == null) {
                    System.out.println("로그인 상태가 아닙니다.");
                } else {
                    System.out.println(loginedMember.getName() + "님 로그아웃 되었습니다.");
                    loginedMember = null;
                }
            } else if (command.startsWith("article write")) {
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String content = sc.nextLine();
                LocalDateTime now = Util.getNow();
                articles.add(new Article(articleId, title, content, now, now));
                System.out.println(articleId + "번 글이 생성되었습니다");
                articleId++;
            } else if (command.startsWith("article list")) {
                String[] cmdBits = command.split(" ", 3);
                String keyword = cmdBits.length >= 3 ? cmdBits[2] : "";

                System.out.println("번호 / 제목 / 작성일 / 내용");

                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article a = articles.get(i);
                    if (keyword.isEmpty() || a.getTitle().contains(keyword)) {
                        String dateStr = Util.formatDateTimeSmart(a.getCreatedAt());
                        System.out.println(a.getId() + " / " + a.getTitle() + " / " + dateStr + " / " + a.getContent());
                    }
                }
            } else if (command.startsWith("article detail ")) {
                int id = Integer.parseInt(command.split(" ")[2]);
                Article found = findArticleById(id, articles);
                if (found == null) {
                    System.out.println(id + "번 게시글은 없습니다");
                } else {
                    System.out.println("번호 : " + found.getId());
                    System.out.println("작성날짜 : " + Util.formatDateTimeSmart(found.getCreatedAt()));
                    System.out.println("수정날짜 : " + Util.formatDateTimeSmart(found.getUpdatedAt()));
                    System.out.println("제목 : " + found.getTitle());
                    System.out.println("내용 : " + found.getContent());
                }
            } else if (command.startsWith("article delete ")) {
                int id = Integer.parseInt(command.split(" ")[2]);
                boolean removed = articles.removeIf(a -> a.getId() == id);
                if (removed) {
                    System.out.println(id + "번 게시글이 삭제되었습니다");
                } else {
                    System.out.println(id + "번 게시글은 없습니다");
                }
            } else if (command.startsWith("article modify ")) {
                int id = Integer.parseInt(command.split(" ")[2]);
                Article found = findArticleById(id, articles);
                if (found == null) {
                    System.out.println(id + "번 게시글은 없습니다");
                } else {
                    System.out.println("기존 제목 : " + found.getTitle());
                    System.out.println("기존 내용 : " + found.getContent());
                    System.out.print("제목 : ");
                    found.setTitle(sc.nextLine());
                    System.out.print("내용 : ");
                    found.setContent(sc.nextLine());
                    found.setUpdatedAt(Util.getNow());
                    System.out.println(id + "번 게시글이 수정되었습니다.");
                }
            } else {
                System.out.println("알 수 없는 명령어입니다.");
            }
        }

        sc.close();
    }

}

class Article {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Article(int id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}

class Member {
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