package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {

    private final Scanner sc;
    private final List<Article> articles = new ArrayList<>();
    private int articleId = 1;
    private MemberController memberController;

    public ArticleController(Scanner sc) {
        this.sc = sc;
    }

    public void setMemberController(MemberController memberController) {
        this.memberController = memberController;
    }

    public void handleCommand(String cmd) {
        if (cmd.equals("article write")) {
            write();
        } else if (cmd.equals("article list")) {
            list();
        } else if (cmd.startsWith("article detail ")) {
            detail(cmd);
        } else if (cmd.startsWith("article delete ")) {
            delete(cmd);
        } else if (cmd.startsWith("article modify ")) {
            modify(cmd);
        } else {
            System.out.println("지원하지 않는 article 명령어입니다.");
        }
    }

    private void write() {
        Member loginUser = memberController.getLoginedMember();

        if (loginUser == null) {
            System.out.println("로그인 후 사용 가능합니다.");
            return;
        }

        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();

        LocalDateTime now = Util.getNow();
        articles.add(new Article(articleId, title, content, now, now, loginUser.getId()));
        System.out.println(articleId + "번 글이 생성되었습니다");
        articleId++;
    }

    private void list() {
        System.out.println("번호 / 제목 / 작성일 / 내용");
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article a = articles.get(i);
            String dateStr = Util.formatDateTimeSmart(a.getCreatedAt());
            System.out.println(a.getId() + " / " + a.getTitle() + " / " + dateStr + " / " + a.getContent());
        }
    }

    private void detail(String cmd) {
        int id = Integer.parseInt(cmd.split(" ")[2]);
        Article found = findById(id);

        if (found == null) {
            System.out.println(id + "번 게시글은 없습니다");
            return;
        }

        System.out.println("번호 : " + found.getId());
        System.out.println("작성자 ID : " + found.getMemberId());
        System.out.println("작성날짜 : " + Util.formatDateTimeSmart(found.getCreatedAt()));
        System.out.println("수정날짜 : " + Util.formatDateTimeSmart(found.getUpdatedAt()));
        System.out.println("제목 : " + found.getTitle());
        System.out.println("내용 : " + found.getContent());
    }

    private void delete(String cmd) {
        int id = Integer.parseInt(cmd.split(" ")[2]);
        Article found = findById(id);

        if (found == null) {
            System.out.println(id + "번 게시글은 없습니다");
            return;
        }

        Member loginUser = memberController.getLoginedMember();

        if (loginUser == null || found.getMemberId() != loginUser.getId()) {
            System.out.println("해당 게시글에 대한 권한이 없습니다.");
            return;
        }

        articles.remove(found);
        System.out.println(id + "번 게시글이 삭제되었습니다");
    }

    private void modify(String cmd) {
        int id = Integer.parseInt(cmd.split(" ")[2]);
        Article found = findById(id);

        if (found == null) {
            System.out.println(id + "번 게시글은 없습니다");
            return;
        }

        Member loginUser = memberController.getLoginedMember();

        if (loginUser == null || found.getMemberId() != loginUser.getId()) {
            System.out.println("해당 게시글에 대한 권한이 없습니다.");
            return;
        }

        System.out.println("기존 제목 : " + found.getTitle());
        System.out.println("기존 내용 : " + found.getContent());
        System.out.print("제목 : ");
        found.setTitle(sc.nextLine());
        System.out.print("내용 : ");
        found.setContent(sc.nextLine());
        found.setUpdatedAt(Util.getNow());
        System.out.println(id + "번 게시글이 수정되었습니다.");
    }

    private Article findById(int id) {
        for (Article a : articles) {
            if (a.getId() == id) return a;
        }
        return null;
    }
}
