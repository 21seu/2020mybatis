package dao;

import dto.SongDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojo.MySong;
import pojo.Song;
import utils.MybatisUtil;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 21seu.ftj on 2020/9/27 6:46
 */
public class SongDaoTest {

    @Test
    public void selectSongsByInfos() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao mapper = sqlSession.getMapper(SongDao.class);
        List<SongDto> songDtos = mapper.selectSongsByInfos("王菲", "国语");
        for (SongDto songDto : songDtos) {
            System.out.println(songDto);
        }
        sqlSession.close();
    }

    @Test
    public void selectMapById() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao mapper = sqlSession.getMapper(SongDao.class);
        Map<Object, Object> map = mapper.selectMapById(1);
        System.out.println(map);
        sqlSession.close();
    }

    @Test
    public void selectAllSongs() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao mapper = sqlSession.getMapper(SongDao.class);
        List<Song> songs = mapper.selectAllSongs();
        for (Song song : songs) {
            System.out.println(song);
        }
        sqlSession.close();
    }

    @Test
    public void selectAllSong() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao mapper = sqlSession.getMapper(SongDao.class);
        List<MySong> mySongs = mapper.selectAllSong();
        for (MySong mySong : mySongs) {
            System.out.println(mySong);
        }
        sqlSession.close();
    }

    @Test
    public void selectDiffColProperty() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao mapper = sqlSession.getMapper(SongDao.class);
        List<MySong> mySongs = mapper.selectDiffColProperty();
        for (MySong mySong : mySongs) {
            System.out.println(mySong);
        }
        sqlSession.close();
    }

    @Test
    public void selectLikeTwo() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SongDao mapper = sqlSession.getMapper(SongDao.class);
        List<Song> mySongs = mapper.selectLikeTwo("三");
        for (Song mySong : mySongs) {
            System.out.println(mySong);
        }
        sqlSession.close();
    }
}
