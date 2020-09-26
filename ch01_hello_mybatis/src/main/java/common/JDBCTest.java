package common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 21seu.ftj on 2020/9/22 21:26
 * 传统JDBC代码
 */
public class JDBCTest {

    private static final String URL = "jdbc:mysql:///mybatis?serverTimezone=UTC&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PWD = "password";

    private static Connection connection = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    private static List<String> list = new ArrayList<String>();

    public static void main(String[] args) throws ClassNotFoundException {
        //加载mysql驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            //获取连接对象
            connection = DriverManager.getConnection(URL, USER, PWD);
            String sql = "select * from mybatis_song";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                list.add(name);
            }
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
