package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 21seu.ftj on 2020/9/24 7:18
 * @author 21seu.ftj
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        String resource = "mybatis-config.xml";
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取SqlSession对象方法
     * @return sqlSession对象
     */
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        if (sqlSessionFactory != null){
            sqlSession = sqlSessionFactory.openSession();
        }
        return sqlSession;
    }
}
