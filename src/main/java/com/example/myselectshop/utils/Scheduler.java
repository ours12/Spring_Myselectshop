package com.example.myselectshop.utils;

import com.example.myselectshop.models.ItemDto;
import com.example.myselectshop.models.Product;
import com.example.myselectshop.models.ProductRepository;
import com.example.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor  // final 멤버 변수를 자동으로 생성.
@Component   // 스프링이 필요시 자동으로 생성하는 클래스 목록에 추가.
public class Scheduler {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final NaverShopSearch naverShopSearch;

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException{
        System.out.println("가격 업데이트 실행");

        // 저장된 관심상품을 모두 조회하고,
        List<Product> productList = productRepository.findAll();
        for(int i=0; i<productList.size(); i++){
            // 1초에 한 상품씩 조회한다. (NAVER 제한때문에)
            TimeUnit.SECONDS.sleep(1);
            // i 번째 관심상품을 꺼내고,
            Product p = productList.get(i);
            // i 번째 관심상품의 제목으로 검색을 한다.
            String title = p.getTitle();
            String resultString = naverShopSearch.search(title);
            // i 번째 관심상품의 검색 결과 목록 중에서 첫번째 결과를 꺼낸다.
            List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(resultString);
            ItemDto itemDto = itemDtoList.get(0);
            // 찾는 상품의 id를 찾아서, 관심상품 정보를 업데이트 한다.
            Long id = p.getId();
            productService.updateBySearch(id,itemDto);
        }

    }
}
