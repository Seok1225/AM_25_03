package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Article.makeTestData;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Article> articles = new ArrayList<>();
        int articleId = 1;
        makeTestData(articles, articleId);
        articleId += 3;

        while (true) {
            System.out.print("명령어) ");
            String command = sc.nextLine().trim();

            if (command.equals("exit")) break;

            if (command.startsWith("article write")) {
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String content = sc.nextLine();
                articles.add(new Article(articleId, title, content, LocalDateTime.now()));
                System.out.println(articleId + "번 글이 생성되었습니다");
                articleId++;
            } else if (command.equals("article list")) {
                System.out.println("번호  /  제목  / 내용");
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article a = articles.get(i);
                    System.out.println(" " + a.getId() + "   /   " + a.getTitle() + " /  " + a.getContent());
                }
            } else if (command.startsWith("article detail ")) {
                int id = Integer.parseInt(command.split(" ")[2]);
                Article found = null;
                for (Article a : articles) {
                    if (a.getId() == id) {
                        found = a;
                        break;
                    }
                }
                if (found == null) {
                    System.out.println(id + "번 게시글은 없습니다");
                } else {
                    System.out.println("번호 : " + found.getId());
                    System.out.println("날짜 : " + found.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
                Article found = null;
                for (Article a : articles) {
                    if (a.getId() == id) {
                        found = a;
                        break;
                    }
                }
                if (found == null) {
                    System.out.println(id + "번 게시글은 없습니다");
                } else {
                    System.out.println("기존 제목 : " + found.getTitle());
                    System.out.println("기존 내용 : " + found.getContent());
                    System.out.print("제목 : ");
                    found.setTitle(sc.nextLine());
                    System.out.print("내용 : ");
                    found.setContent(sc.nextLine());
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

    public Article(int id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static void makeTestData(List<Article> articles, int startId) {
        articles.add(new Article(startId++, "테스트 제목 1", "테스트 내용 1", LocalDateTime.now()));
        articles.add(new Article(startId++, "테스트 제목 2", "테스트 내용 2", LocalDateTime.now()));
        articles.add(new Article(startId++, "테스트 제목 3", "테스트 내용 3", LocalDateTime.now()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}