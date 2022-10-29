package com.example.uproject.ioc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Chef {
    // 셰프는 식재료 공장을 알고 있음
    private IngredientFactory ingredientFactory;
    // 셰프과 공장과 협업하기 위한 DI
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
//        Pork pork = new Pork("한돈");
//        return pork.getName() + "으로 만든 "+menu;
//        Beef beef = new Beef("한우 꽃등심");
//        return beef.getName() + "으로 만든 "+menu;

        Ingredient ingredient= ingredientFactory.get(menu);
        return ingredient.getName() +"으로 만든 "+menu;
    }
}
