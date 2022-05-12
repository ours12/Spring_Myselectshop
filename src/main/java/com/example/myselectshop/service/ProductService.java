package com.example.myselectshop.service;


import com.example.myselectshop.models.Product;
import com.example.myselectshop.models.ProductMypriceRequestDto;
import com.example.myselectshop.models.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    // 관심 가격 정보가 전달됬을때, id를 받아서 찾고, 그 상품의 myprice를 업데이트해주는 메서드
    @Transactional   //DB를 바꿔줘야 한다.(DB정보가 업데이트 되는것)
    public Long update(Long id, ProductMypriceRequestDto requestDto){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        product.update(requestDto);
        return id;
    }
}
