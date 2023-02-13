package myapp_lab.service;

import java.sql.SQLException;

import myapp_lab.model.ProductRepository;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void 상품등록(String name, int price, int qty) throws SQLException {
        // 트랜젝션 시작 = @Transactional
        productRepository.insert(name, price, qty);
        productRepository.insert(name, price, qty);
        // 트랜젝션 종료
    }
}
