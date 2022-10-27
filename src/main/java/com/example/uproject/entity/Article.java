package com.example.uproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB 가 해당 ㄱ개체를 인식 가능하게
public class Article {

    @Id // 대표값을 지정
    @GeneratedValue // 자동 생성 어노테이션
    private  Long id; // 대표값을 넣어줘야 함

    @Column
    private String title;

    @Column
    private String content;

    public Article(){
    }
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
