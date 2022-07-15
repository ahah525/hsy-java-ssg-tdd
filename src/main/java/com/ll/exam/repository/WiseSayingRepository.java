package com.ll.exam.repository;

import com.ll.exam.App;
import com.ll.exam.WiseSayingTable;
import com.ll.exam.domain.WiseSaying;

import java.util.List;

public class WiseSayingRepository {
    private WiseSayingTable wiseSayingTable;

    public WiseSayingRepository() {
        // 앱 실행 모드에 맞는 기본 경로로 DB 생성
        wiseSayingTable = new WiseSayingTable(App.getDefaultPath());
    }

    // 명언 삽입
    public WiseSaying create(String content, String author) {
        return wiseSayingTable.save(content, author);
    }

    // 모든 명언 조회
    public List<WiseSaying> findAll() {
        return wiseSayingTable.findAll();
    }

    // id로 명언 조회
    public WiseSaying findById(int id) {
        return wiseSayingTable.findById(id);
    }

    // id로 명언 수정
    public void update(int id, String content, String author) {
        wiseSayingTable.save(new WiseSaying(id, content, author));
    }

    // id로 명언 삭제
    public void remove(int id) {
        wiseSayingTable.removeById(id);
    }
}
