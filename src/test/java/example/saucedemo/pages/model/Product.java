package example.saucedemo.pages.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Product {
    String name,description,price;

}
