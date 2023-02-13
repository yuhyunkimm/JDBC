package myapp_lab;

import java.sql.Connection;
import java.sql.SQLException;

import myapp_lab.config.DBConfig;
import myapp_lab.model.Product;
import myapp_lab.model.ProductRepository;
import myapp_lab.service.ProductService;

public class DBApp {
    public static void main(String[] args) throws SQLException {
        // 1. 커넥션 가져오기 @Mapper
        // 스프링에서는 컴포넌트 스캔으로 자동으로 일어남
        Connection conn = DBConfig.getConnection();
        // 2. DAO를 메모리에 올리기
        ProductRepository productRepository = new ProductRepository(conn);
        // 3. SERVICE를 메모리에 올리기
        ProductService productService = new ProductService(productRepository, conn);

        // productService.상품등록("apply", 2000, 5);
        // => 이모든 과정을 @ 어노테이션이 관리 해준다
        Product product = productRepository.findById(1);

    }
}
