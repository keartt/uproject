package com.example.uproject.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB 가 해당 객체를 인식 가능하게
@AllArgsConstructor
@ToString
@NoArgsConstructor // 디폴트 생성자 추가
public class Article {

    @Id // 대표값을 지정
    @GeneratedValue // 자동 생성 어노테이션
    private  Long id; // 대표값을 넣어줘야 함

    @Column
    private String title;

    @Column
    private String content;

//   > @NoArgsConstructor
//    public Article(){
//    }

//  > @AllArgsConstructor
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

//    > @ToString
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
