package com.example.uproject.dto;

import com.example.uproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 롬복을 이용해 생성자 제거
@ToString           // 롬복을 이용해 ToStirng 메서드 제거
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(id, title, content);
    }


}
