package myapp_lab;

import java.sql.Connection;
import java.sql.SQLException;

import myapp_lab.config.DBConfig;
import myapp_lab.model.ProductRepository;
import myapp_lab.service.ProductService;

public class DBApp {
    public static void main(String[] args) throws SQLException {
        // 스프링에서는 컴포넌트 스캔으로 자동으로 일어남
        Connection conn = DBConfig.getConnection();
        ProductRepository productRepository = new ProductRepository(conn);
        ProductService productService = new ProductService(productRepository);
        productService.상품등록("apply", 2000, 5);
    }
}
