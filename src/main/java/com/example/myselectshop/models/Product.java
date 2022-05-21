package com.example.myselectshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  // 기본 생성자를 만들어 준다
@Entity  // DB 테이블 역할을 한다.
public class Product extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   //Id 자동 생성 및 증가
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    // 관삼 상품 등록과 관련된 ProductRequestDto에서 가져오는 생성자
    public Product(ProductRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.link = requestDto.getLink();
        this.image = requestDto.getImage();
        this.lprice = requestDto.getLprice();
        this.myprice = 0; // 최저가설정이 없을때, 최저가 표시를 나타내지 않기 위해서 가장 낮은 금액 0으로 설정
    }

    // 스케쥴러 update 메서드
    public void updateByItemDto(ItemDto itemDto){
        this.lprice = itemDto.getLprice();
    }

    //update 메서드
    public void update(ProductMypriceRequestDto requestDto){
        this.myprice = requestDto.getMyprice();
    }
}
