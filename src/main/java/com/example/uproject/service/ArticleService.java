package com.example.uproject.service;

import com.example.uproject.dto.ArticleForm;
import com.example.uproject.entity.Article;
import com.example.uproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // service 선언 _ service 객체를 스프링 부트에 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public  List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {  // 아이디가 있을경우 POST 로 데이터가 수정되지 않도록
            return null; }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1: 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2: 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3: 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4: 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청이 들어올 경우
        if (target == null) {
            return null;
        }
        //대상 삭제 후 반환
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                                     .map(dto -> dto.toEntity())
                                     .collect(Collectors.toList());
        // entity 묶음을 DB 로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제 예외 발생 ( 에러)
        articleRepository.findById(-1L).orElseThrow(
                ()-> new IllegalArgumentException("실패")
        );
        //결과값 반환
        return articleList;
    }
}
