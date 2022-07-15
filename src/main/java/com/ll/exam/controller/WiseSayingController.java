package com.ll.exam.controller;

import com.ll.exam.Rq;
import com.ll.exam.domain.WiseSaying;
import com.ll.exam.service.WiseSayingService;

import java.util.ArrayList;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;
    private WiseSayingService wiseSayingService;
    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingService = new WiseSayingService();
    }

    public void create() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        // 명언
        WiseSaying ws = wiseSayingService.create(content, author);
        // 리스트에 명언 추가
        System.out.printf("%d번 명언이 등록되었습니다.\n", ws.getId());
    }

    public void findAll() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        // 명언 리스트 얻어오기
        ArrayList<WiseSaying> wiseSayings = wiseSayingService.findAll();
        // 최신 명언부터 역순 출력
        for (int i = wiseSayings.size() - 1 ; i >= 0; i--) {
            WiseSaying ws = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", ws.getId(), ws.getAuthor(), ws.getContent());
        }
    }

    public void update(Rq rq) {
        int id = rq.getIntParam("id", 0);
        // 잘못된 형식의 수정 요청에 대한 경우 예외 처리
        if (id == 0) {
            System.out.println("수정?id=value 형식으로 입력해주세요.");
            return;
        }
        WiseSaying ws = wiseSayingService.findById(id); // 수정할 명언
        // 존재하지 않는 명언에 대한 예외 처리
        if (ws == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        // 명언 입력받기
        System.out.printf("명언(기존) : %s\n", ws.getContent());
        System.out.print("명언) ");
        String newContent = sc.nextLine();
        // 작가 입력받기
        System.out.printf("작가(기존) : %s\n", ws.getAuthor());
        System.out.print("작가) ");
        String newAuthor = sc.nextLine();
        // 명언 수정
        wiseSayingService.update(id, newContent, newAuthor);
        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }

    public void delete(Rq rq) {
        int id = rq.getIntParam("id", 0);
        // 입력된 id가 없는 경우 예외 처리
        if (id == 0) {
            System.out.println("삭제?id=value 형식으로 입력해주세요.");
            return;
        }
        WiseSaying ws = wiseSayingService.findById(id); // 삭제할 명언
        // 존재하지 않는 명언에 대한 예외 처리
        if (ws == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        // 명언 삭제
        wiseSayingService.remove(id);
        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }
}
