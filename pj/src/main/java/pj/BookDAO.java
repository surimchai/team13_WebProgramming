package pj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    Connection conn = null;
    PreparedStatement pstmt = null;

    final String JDBC_DRIVER = "org.h2.Driver";
    final String JDBC_URL = "jdbc:h2:tcp://localhost/~/project";

    public void open() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, "project", "1234");
        } catch (Exception e) {
            e.printStackTrace();}
    }

    public void close() {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();}
    }

    public void insert(Book b) {
        open();

        String sql = "INSERT INTO BOOK(title, author, price) VALUES(?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, b.getTitle());
            pstmt.setString(2, b.getAuthor());
            pstmt.setInt(3, b.getPrice());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public List<Book> getAll() {
        open();
        List<Book> list = new ArrayList<>();

        String sql = "SELECT * FROM BOOK ORDER BY id ASC";

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setPrice(rs.getInt("price"));
                list.add(b);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return list;
    }
    
    public void deleteBook(int id) {
        String sql = "DELETE FROM BOOK WHERE ID = ?";
        
        open();
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) 
                System.out.println("id = " + id + " 삭제 성공!");
        } catch (SQLException e) {
            System.out.println("delete 실패\n");
        } finally {
            close();
        }
    }
}
