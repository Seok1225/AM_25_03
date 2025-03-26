package org.example;

import java.util.Scanner;

public class App {

    private final Scanner sc = new Scanner(System.in);
    private final MemberController memberController = new MemberController(sc);
    private final ArticleController articleController = new ArticleController(sc);

    public void run() {
        System.out.println("== Article Manager ==");

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();
            articleController.setMemberController(memberController);
            if (cmd.equals("exit")) break;

            if (cmd.startsWith("member ")) {
                memberController.handleCommand(cmd);
            } else if (cmd.startsWith("article ")) {
                articleController.handleCommand(cmd);
            } else {
                System.out.println("알 수 없는 명령어입니다.");
            }
        }
        sc.close();
    }
}
