package com.example.uproject.controller;

import com.example.uproject.dto.ArticleForm;
import com.example.uproject.entity.Article;
import com.example.uproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
    //System.out.println(form); > 로깅으로 대체
        log.info(form.toString());

        // DTO 를 Entity 로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        // Repository 에게 Entity 를 DB 에 저장하게
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "";
    }

    //
    @GetMapping("/articles/{id}") // 변수인 id 페이지
    public String show(@PathVariable Long id, Model model){ // URL path 로 부터 변수 입력
        log.info("id = " + id);

        //1 id로 데이터를 가져오고
        Article articleEntity = articleRepository.findById(id).orElse(null); // ENTITY에 id 값이 없으면 null 반환

        //2 가져온 데이터를 모델에 등록하고
        model.addAttribute("article", articleEntity);

        //3 보여줄 페이지를 설정한다.
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){

        //1 모든 Article 을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();
        // repository 에 ArrayList 오버라이딩 필요

        //2 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        //뷰 페이지를 설계한다
        return "articles/index"; // articles/index.mustache

    }

}
