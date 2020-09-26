package dao.impl;

import dao.SongDao;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Song;
import utils.MybatisUtil;

import java.util.List;

/**
 * Created by 21seu.ftj on 2020/9/24 7:40
 */
public class SongDaoImplTest {

    @Test
    public void queryAllSongInfos() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao dao = sqlSession.getMapper(SongDao.class);
        //JDK的动态代理
        System.out.println("dao="+dao.getClass().getName());
        List<Song> songs = dao.queryAllSongInfos();
        for (Song song : songs) {
            System.out.println(song);
        }
        sqlSession.close();
    }

    @Test
    public void querySongInfos2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<Song> list = sqlSession.selectList("dao.SongDao.queryAllSongInfos");
        for (Song song : list) {
            System.out.println(song);
        }
        sqlSession.close();
    }
}
