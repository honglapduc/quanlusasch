package honglapduc.sachTH.entity;

import honglapduc.sachTH.validator.annotation.ValidCategoryId;
import honglapduc.sachTH.validator.annotation.ValidUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "không duoc trống")
    @Size(max = 50, min = 1, message = "tối đa 50")
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private  String author;
    @Column(name = "price")
    @NotNull(message = "price is required")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;
}
