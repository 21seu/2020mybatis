package dao;

import pojo.Song;

import java.util.List;

/**
 * Created by 21seu.ftj on 2020/9/22 22:58
 */
public interface SongDao {

    //查询所有歌曲信息
    public List<Song> queryAllSongInfos();

    /**
     * 插入数据操作
     * @param song  表示要插入的数据
     * @return  int 表示执行insert之后影响数据库的行数
     */
    public int insertSong(Song song);
}
