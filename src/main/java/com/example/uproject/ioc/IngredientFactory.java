package com.example.uproject.ioc;

import org.springframework.stereotype.Component;

@Component // 해당 클래스를 객체로 만들고, ioc 컨테이너에 등록
public class IngredientFactory {
    public Ingredient get(String menu) {
        switch (menu) {
            case "돈까스" : return new Pork("한돈");
            case "스테이크" : return new Beef("꽃등심");
            case "치킨" : return new Dak("6호닭");
            default: return null;
        }
    }
}
