package common;

import dao.SongDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pojo.Song;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 21seu.ftj on 2020/9/23 22:49
 */
public class MyApp {

    private static String resource = "mybatis-config.xml";
    private static SqlSession session = null;
    @Test
    public void test() throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        //创建了SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //获取sqlSession对象，从SqlSessionFactory中获取
        session = sqlSessionFactory.openSession();
        //SongDao mapper = session.getMapper(SongDao.class);
        Song song = new Song(8,"彩虹","周杰伦","流行","方文山","国语",new Date());
        //写法2
        int result = session.insert("dao.SongDao.insertSong",song);
        //mybatis默认不是自动提交事务的，所以在Insert，update，delete后要手工提交事务
        session.commit();
        if (result > 0){
            System.out.println("数据插入成功");
        }
        //关闭sqlSession对象
        session.close();
    }
}
