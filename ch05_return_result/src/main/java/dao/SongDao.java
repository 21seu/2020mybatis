package dao;

import dto.SongDto;
import org.apache.ibatis.annotations.Param;
import pojo.MySong;
import pojo.Song;

import java.util.List;
import java.util.Map;

/**
 * Created by 21seu.ftj on 2020/9/22 22:58
 */
public interface SongDao {

    List<SongDto> selectSongsByInfos(@Param("singer") String singer, @Param("language") String language);

    //定义方法放回Map
    Map<Object,Object> selectMapById(Integer id);

    /**
     * 使用resultMap定义映射关系
     */
    List<Song> selectAllSongs();

    List<MySong> selectAllSong();

    List<MySong> selectDiffColProperty();

    /**
     * 第一种模糊查询，在java代码中指定like内容
     */
    List<Song> selectLikeOne(String name);

    /**
     * name就是值，在mapper中拼接like
     */
    List<Song> selectLikeTwo(String name);

}
