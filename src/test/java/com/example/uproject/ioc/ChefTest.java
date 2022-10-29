package com.example.uproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChefTest {

    @Autowired // @Component 로 가져오기
    IngredientFactory ingredientFactory;

    @Autowired
    Chef chef;

    @Test
    void 돈까스() {
//        IngredientFactory ingredientFactory = new IngredientFactory();

//        Chef chef = new Chef(ingredientFactory);    //준비
        String menu = "돈까스";

        String food = chef.cook(menu);              //수행

        String expected = "한돈으로 만든 돈까스";      //예상

        assertEquals(expected, food);               //검증
        System.out.println(food);
    }
    @Test
    void 스테이크() {
//        IngredientFactory ingredientFactory = new IngredientFactory();

//        Chef chef = new Chef(ingredientFactory);                     //준비
        String menu = "스테이크";

        String food = chef.cook(menu);              //수행

        String expected = "꽃등심으로 만든 스테이크";      //예상

        assertEquals(expected, food);               //검증
        System.out.println(food);
    }
    @Test
    void 치킨() {
//        IngredientFactory ingredientFactory = new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu = "치킨";

        String food = chef.cook(menu);
        String expected = "6호닭으로 만든 치킨";

        assertEquals(expected, food);
        System.out.println(food);

    }
}