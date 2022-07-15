package com.ll.exam.controller;

import com.ll.exam.Rq;
import com.ll.exam.domain.WiseSaying;

import java.util.ArrayList;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;
    private int wiseSayingLastId; // 저장된 명언의 마지막 번호
    private ArrayList<WiseSaying> wiseSayings;  // 명언 리스트

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingLastId= 0;                // 0으로 초기화
        wiseSayings = new ArrayList<>();
    }

    public void create() {
        int id = ++wiseSayingLastId;
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        // 리스트에 명언 추가
        wiseSayings.add(new WiseSaying(id, content, author));
        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    }

    public void findAll() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        // 최신 명언부터 역순 출력
        for (int i = wiseSayings.size() - 1 ; i >= 0; i--) {
            WiseSaying ws = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", ws.getId(), ws.getAuthor(), ws.getContent());
        }
    }

    public void update(Rq rq) {
        int modifyId = rq.getIntParam("id", 0);
        // 잘못된 형식의 수정 요청에 대한 경우 예외 처리
        if (modifyId == 0) {
            System.out.println("수정?id=value 형식으로 입력해주세요.");
            return;
        }
        WiseSaying ws = findById(modifyId); // 수정할 명언
        // 존재하지 않는 명언에 대한 예외 처리
        if (ws == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", modifyId);
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
        // 명언 리스트에서 해당 명언 객체 수정
        ws.setContent(newContent);
        ws.setAuthor(newAuthor);
    }

    public void delete(Rq rq) {
        int deleteId = rq.getIntParam("id", 0);
        // 입력된 id가 없는 경우 예외 처리
        if (deleteId == 0) {
            System.out.println("삭제?id=value 형식으로 입력해주세요.");
            return;
        }
        WiseSaying wsD = findById(deleteId); // 삭제할 명언
        // 존재하지 않는 명언에 대한 예외 처리
        if (wsD == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", deleteId);
            return;
        }
        // 명언 리스트에서 해당 명언 삭제
        wiseSayings.remove(deleteId);
        System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
    }

    // 명언 리스트에서 id로 명언 객체 조회하는 메서드
    public WiseSaying findById(int id) {
        for (WiseSaying ws : wiseSayings) {
            if(ws.getId() == id)
                return ws;
        }
        return null;
    }
}
