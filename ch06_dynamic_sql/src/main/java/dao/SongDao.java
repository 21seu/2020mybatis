package dao;


import pojo.Song;

import java.util.List;

/**
 * Created by 21seu.ftj on 2020/9/22 22:58
 */
public interface SongDao {

    /**
     * 动态sql要使用Java对象作为参数
     * @param song
     * @return
     */
    public List<Song> selectSongsIf(Song song);

    //forEach用法1
    List<Song> selectForEachOne(List<Integer> id);

    //forEach用法2
    List<Song> selectForEachTwo(List<Song> songs);
}
