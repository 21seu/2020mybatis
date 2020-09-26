package common;


import dao.SongDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Song;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 21seu.ftj on 2020/9/22 23:04
 */
public class MybatisTest {

    //定义Mybatis主配置文件的名称，从类路径的根开始（target/classes）
    private static String resource = "mybatis-config.xml";
    private static SqlSession session = null;

    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        //创建了SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //获取sqlSession对象，从SqlSessionFactory中获取
        session = sqlSessionFactory.openSession();
        //SongDao mapper = session.getMapper(SongDao.class);
        //写法2
        List<Song> songs = session.selectList("dao.SongDao.queryAllSongInfos");
        //List<Song> songs = mapper.queryAllSongInfos();
        for (Song song : songs) {
            System.out.println(song);
        }
        //关闭sqlSession对象
        session.close();
    }
}
