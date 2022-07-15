package com.ll.exam.service;

import com.ll.exam.domain.WiseSaying;

import java.util.ArrayList;

public class WiseSayingService {
    private int wiseSayingLastId; // 저장된 명언의 마지막 번호
    private ArrayList<WiseSaying> wiseSayings;  // 명언 리스트

    public WiseSayingService() {
        wiseSayingLastId= 0;                // 0으로 초기화
        wiseSayings = new ArrayList<>();
    }

    public WiseSaying create(String content, String author) {
        int id = ++wiseSayingLastId;
        WiseSaying ws = new WiseSaying(id, content, author);
        wiseSayings.add(ws);

        return ws;
    }


    public ArrayList<WiseSaying> findAll() {
        return wiseSayings;
    }

    // 명언 리스트에서 id로 명언 객체 조회하는 메서드
    public WiseSaying findById(int id) {
        for (WiseSaying ws : wiseSayings) {
            if(ws.getId() == id)
                return ws;
        }
        return null;
    }

    public void update(int id, String content, String author) {
        WiseSaying ws = findById(id);
        // 명언 리스트에서 해당 명언 객체 수정
        ws.setContent(content);
        ws.setAuthor(author);
    }

    public void remove(int id) {
        // 명언 리스트에서 해당 명언 삭제
        wiseSayings.remove(id);
    }
}
