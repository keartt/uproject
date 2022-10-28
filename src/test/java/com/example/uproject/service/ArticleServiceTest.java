package com.example.uproject.service;

import com.example.uproject.dto.ArticleForm;
import com.example.uproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest // 스프링 부트와 연동되어 테스팅
class ArticleServiceTest {
    @Autowired ArticleService articleService; // ArticleService 가져오기

    @Test
    void index() {
        //예상
        Article a = new Article(1L,"a","apple");
        Article b = new Article(2L,"b","boat");
        Article c = new Article(3L,"c","cake");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        //실제
        List<Article> articles = articleService.index();

        //비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        //예상
        Long id = 1L;
        Article expected = new Article(1L,"a","apple");
        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지않는_id_입력() {
        //예상
        Long id = -1L;
        Article expected = null;
        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_잇는_dto() {
        //예상
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L,title, content);
        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_실패_id가_포함된_dto_입력() {
        //예상
        String title = "dddd";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;
        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected, article);
    }
}