package com.ll.exam.repository;

import com.ll.exam.domain.WiseSaying;

import java.util.ArrayList;

public class WiseSayingRepository {
    private int wiseSayingLastId; // 저장된 명언의 마지막 번호
    private ArrayList<WiseSaying> wiseSayings;  // 명언 리스트

    public WiseSayingRepository() {
        wiseSayingLastId= 0;                // 0으로 초기화
        wiseSayings = new ArrayList<>();
    }

    // 명언 삽입
    public WiseSaying create(String content, String author) {
        int id = ++wiseSayingLastId;
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    // 모든 명언 조회
    public ArrayList<WiseSaying> findAll() {
        return wiseSayings;
    }

    // id로 명언 조회
    public WiseSaying findById(int id) {
        for (WiseSaying ws : wiseSayings) {
            if(ws.getId() == id)
                return ws;
        }
        return null;
    }

    // id로 명언 수정
    public void update(int id, String content, String author) {
        WiseSaying wiseSaying = findById(id);
        // 명언 리스트에서 해당 명언 객체 수정
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    // id로 명언 삭제
    public void remove(int id) {
        // 명언 리스트에서 해당 명언 삭제
        wiseSayings.remove(id);
    }
}
