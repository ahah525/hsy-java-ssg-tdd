package com.ll.exam;

import com.ll.exam.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private Scanner sc;
    private WiseSayingController wiseSayingController;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언 SSG ==");
        // WiseSayingController 객체 생성(run이 실행되어야 필요하기 때문에)
        wiseSayingController = new WiseSayingController(sc);

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);    // 입력받은 명령어 분리하기

            switch (rq.getPath()) {
                case "등록":
                    wiseSayingController.create();
                    break;
                case "목록":
                    wiseSayingController.findAll();
                    break;
                case "수정":
                    wiseSayingController.update(rq);
                    break;
                case "삭제":
                    wiseSayingController.delete(rq);
                    break;
                case "종료":
                    break outer;
            }
        }
    }
}
