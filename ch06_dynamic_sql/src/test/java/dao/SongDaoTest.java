package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojo.Song;
import utils.MybatisUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 21seu.ftj on 2020/9/27 21:53
 */

public class SongDaoTest {

    @Test
    public void selectSongsIf() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao mapper = sqlSession.getMapper(SongDao.class);
        Song song = new Song();
        //song.setSinger("周杰伦");
        song.setLanguage("国语");
        List<Song> songs = mapper.selectSongsIf(song);
        for (Song song1 : songs) {
            System.out.println(song1);
        }
        sqlSession.close();
    }

    @Test
    public void testfor() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        String sql = "select * from mybatis_song where id in";

        StringBuilder sb = new StringBuilder("");
        int init = 0;
        int len = list.size();
        //添加开始的(
        sb.append("(");
        for (Integer i : list) {
            sb.append(i).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        //循环的结尾增加)
        sb.append(")");
        sql += sb.toString();
        System.out.println("sql==>" + sql);
    }

    @Test
    public void selectForEachOne() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao mapper = sqlSession.getMapper(SongDao.class);
        List<Song> songs = mapper.selectForEachOne(list);
        for (Song song : songs) {
            System.out.println(song);
        }
        sqlSession.close();
    }

    @Test
    public void selectForEachTwo() {
        List<Song> list = new ArrayList<Song>();
        list.add(new Song(1,"彩虹"));
        list.add(new Song(2,"曾静的你"));
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao mapper = sqlSession.getMapper(SongDao.class);
        List<Song> songs = mapper.selectForEachTwo(list);
        for (Song song : songs) {
            System.out.println(song);
        }
        sqlSession.close();
    }
}
