package dao.impl;

import dao.SongDao;
import org.apache.ibatis.session.SqlSession;
import pojo.Song;
import utils.MybatisUtil;

import java.util.List;

/**
 * Created by 21seu.ftj on 2020/9/24 7:38
 * @author 21seu.ftj
 */
public class SongDaoImpl implements SongDao {

    public List<Song> queryAllSongInfos() {
        //获取SqlSession对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<Song> list = sqlSession.selectList("dao.SongDao.queryAllSongInfos");
        //关闭
        sqlSession.close();
        return list;
    }
}
