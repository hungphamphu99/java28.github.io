package vn.demo.demo.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private String id;
    private String title;
    private String author;
    private int year;
}