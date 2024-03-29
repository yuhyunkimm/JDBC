/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package myapp_lab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    // 싱글톤
    private Connection conn;

    // 의존성주입
    public ProductRepository(Connection conn) {
        this.conn = conn;
    }

    public Product findById(int id) throws SQLException {
        Product product = null;

        // 2. 버퍼 접근
        String sql = "select * form product where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 3. 물음표 완성
        pstmt.setInt(1, id);

        // 4. 전송
        ResultSet rs = pstmt.executeQuery(); // 한 건만 죄회되어 컬럼과 함께 출력
        // 커서를 한칸 내려서 필요한 컬럼을 가져온다
        // 처음 커서는 컬럼에 위치해 있다
        if (rs.next()) { // 커서를 한칸 내려서 내용이 있는지 확인해 준다
            int v1 = rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            int qty = rs.getInt("qty");
            Timestamp createdAt = rs.getTimestamp("created_at");
            product = new Product(v1, name, price, qty, createdAt);
        }
        return product;
    }

    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();

        // 2. 버퍼 접근
        String sql = "select * from product";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 3. 전송
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            int qty = rs.getInt("qty");
            Timestamp createdAt = rs.getTimestamp("created_at");
            Product product = new Product(id, name, price, qty, createdAt);
            productList.add(product);
        }
        return productList;
    }

    // DB에 Insert
    public void insert(String name, int price, int qty) throws SQLException {

        // 2. 버퍼 접근
        String sql = "insert into product(name, price, qty, created_at) values(?,?,?, now())";
        // PreparedStatement => 버퍼에 접근하는 class -> ? 문법을 지원 해준다
        PreparedStatement pstmt = conn.prepareStatement(sql); // sql 작성해서 넣으면 된다

        // 3. 물음표 완성
        // table을 보고 타입 지정
        pstmt.setString(1, name); // parameterIndex : ? 의 순번(1번부터 시작) , 값
        pstmt.setInt(2, price); // parameterIndex : ? 의 순번 , 값
        pstmt.setInt(3, qty); // parameterIndex : ? 의 순번 , 값

        // 4. 전송(flush)
        // pstmt.addBatch(); 한번의 데이터베이스로 한번에 전송
        // pstmt.executeQuery(); 1,0,-1 으로 리턴 못찾으면 0리턴
        int result = pstmt.executeUpdate();

        // 5. 응답에 대한 처리
        if (result == 1) {
            System.out.println("insert 되었습니다");
        } else {
            System.out.println("insert 실패");
        }

        // 6. 최종 마무리
        // connection 종료 , stack에 띄워져 있는 것은 메인이 종료될 때 종료 메모리 관리를 할 필요가 없다
        pstmt.close();

    }

    public void deleteById(int id) throws SQLException {
        // 2. 버퍼 접근
        String sql = "delete from product where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 3. 물음표 완성
        pstmt.setInt(3, id); // parameterIndex : ? 의 순번 , 값

        // 4. 전송(flush)
        int result = pstmt.executeUpdate();

        // 5. 응답에 대한 처리
        if (result == 1) {
            System.out.println("delete 되었습니다");
        } else {
            System.out.println("delete 실패");
        }
        // 6. 최종 마무리
        pstmt.close();

    }

    public void UpdateById(int id, String name, int price, int qty) throws SQLException {
        // 2. 버퍼 접근
        String sql = "Update product set name = ?, price = ?, qty = ?, where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 3. 물음표 완성
        pstmt.setString(1, name); // parameterIndex : ? 의 순번 , 값
        pstmt.setInt(2, price); // parameterIndex : ? 의 순번 , 값
        pstmt.setInt(3, qty); // parameterIndex : ? 의 순번 , 값
        pstmt.setInt(4, id); // parameterIndex : ? 의 순번 , 값

        // 4. 전송(flush)
        int result = pstmt.executeUpdate();

        // 5. 응답에 대한 처리
        if (result == 1) {
            System.out.println("Update 되었습니다");
        } else {
            System.out.println("Update 실패");
        }
        // 6. 최종 마무리
        pstmt.close();

    }

}
