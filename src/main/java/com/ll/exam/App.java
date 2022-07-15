package com.ll.exam;

import com.ll.exam.domain.WiseSaying;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private Scanner sc;
    private int wiseSayingLastId; // 저장된 명언의 마지막 번호
    private ArrayList<WiseSaying> wiseSayings;  // 명언 리스트

    public App(Scanner sc) {
        this.sc = sc;
        wiseSayingLastId= 0;                // 0으로 초기화
        wiseSayings = new ArrayList<>();
    }

    public void run() {
        System.out.println("== 명언 SSG ==");

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "등록":
                    int id = ++wiseSayingLastId;
                    System.out.print("명언 : ");
                    String content = sc.nextLine();
                    System.out.print("작가 : ");
                    String author = sc.nextLine();
                    // 리스트에 명언 추가
                    wiseSayings.add(new WiseSaying(id, content, author));
                    System.out.printf("%d번 명언이 등록되었습니다.\n", id);
                    break;
                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------------");
                    // 최신 명언부터 역순 출력
                    for (int i = wiseSayingLastId - 1; i >= 0; i--) {
                        WiseSaying ws = wiseSayings.get(i);
                        System.out.printf("%d / %s / %s\n", ws.getId(), ws.getAuthor(), ws.getContent());
                    }
                    break;
                case "종료":
                    break outer;
            }
        }
    }
}
