package com.ll.exam.domain;

public class WiseSaying {
    private int id;     // id
    private String content; // 내용
    private String author;  // 작가

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}