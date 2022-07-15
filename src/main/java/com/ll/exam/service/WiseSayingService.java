package com.ll.exam.service;

import com.ll.exam.domain.WiseSaying;
import com.ll.exam.repository.WiseSayingRepository;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository;
    public WiseSayingService() {
        wiseSayingRepository = new WiseSayingRepository();
    }

    public WiseSaying create(String content, String author) {
        return wiseSayingRepository.create(content, author);
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void update(int id, String content, String author) {
        wiseSayingRepository.update(id, content, author);
    }

    public void remove(int id) {
        wiseSayingRepository.remove(id);
    }
}
