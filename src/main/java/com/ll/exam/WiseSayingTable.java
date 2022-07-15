package com.ll.exam;

import com.ll.exam.domain.WiseSaying;

public class WiseSayingTable {
    private String baseDir;

    public WiseSayingTable(String baseDir) {
        this.baseDir = baseDir;
    }

    public void save(WiseSaying wiseSaying) {
        // 해당 경로의 폴더 생성
        Util.file.mkdir("%s/wise_saying".formatted(baseDir));
        String body = "내용";
        // 해당 경로에 명언의 id를 파일명으로 하여 body를 담아 파일 저장
        Util.file.saveToFile("%s/wise_saying/%d.json".formatted(baseDir, wiseSaying.getId()), body);
    }
}