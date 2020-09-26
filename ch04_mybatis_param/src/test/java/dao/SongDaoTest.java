package dao;

import dto.QueryParam;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.Song;
import utils.MybatisUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 21seu.ftj on 2020/9/26 6:46
 */
public class SongDaoTest {

    @Test
    public void selectSongById() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao dao = sqlSession.getMapper(SongDao.class);
        Song song = dao.selectSongById(1);
        System.out.println(song);
        sqlSession.close();
    }


    @Test
    public void selectMulitParam() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao dao = sqlSession.getMapper(SongDao.class);
        List<Song> songs = dao.selectMultiParam("周杰伦", "国语");
        for (Song song : songs) {
            System.out.println(song);
        }
        sqlSession.close();
    }


    @Test
    public void selectMultiObject() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        QueryParam qp = new QueryParam("周杰伦","国语");
        SongDao dao = sqlSession.getMapper(SongDao.class);
        List<Song> songs = dao.selectMultiObject(qp);
        for (Song song : songs) {
            System.out.println(song);
        }
        sqlSession.close();
    }

    @Test
    public void selectMultiPosition() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao dao = sqlSession.getMapper(SongDao.class);
        List<Song> songs = dao.selectMultiPosition("周杰伦","国语");
        for (Song song : songs) {
            System.out.println(song);
        }
        sqlSession.close();
    }

    @Test
    public void selectMultiMap() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("singer","许巍");
        map.put("language","国语");
        SongDao dao = sqlSession.getMapper(SongDao.class);
        List<Song> songs = dao.selectMultiMap(map);
        for (Song song : songs) {
            System.out.println(song);
        }
        sqlSession.close();
    }

    @Test
    public void selectUse$Order() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao dao = sqlSession.getMapper(SongDao.class);
        List<Song> songs = dao.selectUse$Order("name");
        for (Song song : songs) {
            System.out.println(song);
        }
        sqlSession.close();
    }
}
