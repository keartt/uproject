package com.example.uproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import javax.persistence.*;

@Entity // DB 가 해당 객체를 인식 가능하게
@AllArgsConstructor
@ToString
@NoArgsConstructor // 디폴트 생성자 추가
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 전략
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article){
        if (article.title != null) // 제목이 있으면 넣는다.
            this.title = article.title;
        if (article.content != null) // 있으면 넣는다.
            this.content = content;
    }
}
