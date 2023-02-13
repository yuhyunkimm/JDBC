package myapp_lab.service;

import java.sql.Connection;

import myapp_lab.model.ProductRepository;

public class ProductService {
    private ProductRepository productRepository;
    private Connection conn;

    public ProductService(ProductRepository productRepository, Connection conn) {
        this.productRepository = productRepository;
        this.conn = conn;
    }

    public void 상품삭제(int id) {
        try {
            productRepository.deleteById(id);
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e2) {
                e.printStackTrace();
            }
        }
    }

    public void 상품수정(int id, String name, int price, int qty) {
        try {
            productRepository.UpdateById(id, name, price, qty);
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e2) {
                e.printStackTrace();
            }
        }
    }

    // @Transactional을 직접 만들어 주기
    public void 상품등록(String name, int price, int qty) {
        try {
            productRepository.insert(name, price, qty);
            productRepository.insert(name, price, qty);
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e2) {
                e.printStackTrace();
            }
        }
    }
}
