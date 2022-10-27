package com.example.uproject.controller;

import com.example.uproject.dto.ArticleForm;
import com.example.uproject.entity.Article;
import com.example.uproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create" )
    public String createArticle(ArticleForm form){
        System.out.println(form);

        // DTO 를 Entity 로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());

        // Repository 에게 Entity 를 DB 에 저장하게
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());

        return "";
    }
}
