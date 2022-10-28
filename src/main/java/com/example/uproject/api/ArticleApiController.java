package com.example.uproject.api;

import com.example.uproject.dto.ArticleForm;
import com.example.uproject.entity.Article;
import com.example.uproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // log 활용
@RestController // RestAPI 용 컨트롤러, 데이터 (JSON) 을 반환함
public class ArticleApiController {

    @Autowired //DI
    private ArticleRepository articleRepository;

    // GET
    // 1. 목록반환
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }
    // 2. 단일 반환
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles/{id}")
    public Article create(@RequestBody ArticleForm dto) { // 레스트 API 에서 제이슨으로 던질 때 필요
        Article article  = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
    // 1. 수정용 entity 생성
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());
    // 2. 대상 entity 조회
        Article target = articleRepository.findById(id).orElse(null);

    // 3. 잘못된 요청 처리 ( 대상이 null 이거나 id 가 다른 경우
        if (target == null || id != article.getId()) {
            // 400 응답 출력 >> 이를 위해 ResponseEntity<Article> 타입으로 담아서 보내야 함을 수정해줘야함?!
            log.info("잘못된 요청  id : {}, article : {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    // 4. 업데이트 & 정상응답
        target.patch(article);
        // 일부 값을 보내지 않으면 null 이 되는 현상 해결을 위해 기존값에 변경된 값만 붙이는 patch 코드 추가
        // Entity (Article) 에 patch 메소드 추가
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청이 들어올 경우
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //대상 삭제
        articleRepository.delete(target);
        //데이터 반환
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
