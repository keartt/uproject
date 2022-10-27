package com.example.uproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 선언
public class FistController {

    @GetMapping("/hi") // 컨트롤러는 어노테이션을 통해 요청을 받는다.
    public String niceToMeetU(Model model){ // 템플릿 변수를 활용하기 위해 모델 을 파라미터로 삽입
        model.addAttribute("username","호날두"); // 모델은 데이터를 뷰에게 전달한다.
        return "greetings"; // templates/greetings.mustache > 브라우저로 전송 > 리턴값
    }

    @GetMapping("/bye")
    public String seeYou(Model model){
        model.addAttribute("nickname","우리형");
        return "goodbye";
    }
}
