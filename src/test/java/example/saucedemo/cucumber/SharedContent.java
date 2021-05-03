package example.saucedemo.cucumber;

import example.saucedemo.pages.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @Getter
public class SharedContent {
    private List<Product> addedProducts = new ArrayList<>();

}
