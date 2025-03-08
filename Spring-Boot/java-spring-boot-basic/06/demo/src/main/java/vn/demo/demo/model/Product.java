package vn.demo.demo.model;



import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String name;
    private String description;
    private String thumbnail;
    private int price;
    private double rating;
    private Integer priceDiscount;

}