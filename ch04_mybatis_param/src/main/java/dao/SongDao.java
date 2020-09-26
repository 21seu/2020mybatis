package dao;

import dto.QueryParam;
import org.apache.ibatis.annotations.Param;
import pojo.Song;

import java.util.List;
import java.util.Map;

/**
 * Created by 21seu.ftj on 2020/9/22 22:58
 */
public interface SongDao {

    public Song selectSongById(Integer id);

    public List<Song> selectMultiParam(@Param("singer") String singer, @Param("language") String language);

    public List<Song> selectMultiObject(QueryParam queryParam);

    /**
     * 多个参数-简单类型，按位置传值
     * mybatis3.4之前使用#{0} #{1}
     * mybatis3.4之后，使用#{arg0} #{arg1}
     *
     * @param singer
     * @param language
     * @return
     */
    public List<Song> selectMultiPosition(String singer, String language);

    public List<Song> selectMultiMap(Map<String, Object> map);

    List<Song> selectUse$Order(@Param("singer") String singer);
}
