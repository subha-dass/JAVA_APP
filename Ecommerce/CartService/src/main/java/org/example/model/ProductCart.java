package org.example.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ProductCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @CreationTimestamp
    private Date addDate;

    @Column(nullable = false)
    private String ItemNo;

    @Column(nullable = false)
    private String ItemName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Long price;

    private String imageFileName;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",referencedColumnName = "id",nullable = false)
    private ItrmDetail itemdetail;
}
