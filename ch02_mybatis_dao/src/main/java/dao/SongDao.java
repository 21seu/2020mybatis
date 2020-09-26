package dao;

import pojo.Song;

import java.util.List;

/**
 * Created by 21seu.ftj on 2020/9/22 22:58
 */
public interface SongDao {

    //查询所有歌曲信息
    public List<Song> queryAllSongInfos();

}
