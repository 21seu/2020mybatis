package dao.impl;

import dao.SongDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojo.Song;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 21seu.ftj on 2020/9/24 7:40
 */
public class SongDaoImplTest {

    @Test
    public void queryAllSongInfos() {
        //dao.SongDao  全限定名称
        SongDao songDao = new SongDaoImpl();
        /**
         * List<Song> songs = songDao.queryAllSongInfos(); 调用
         * 1、songDao对象，类型是SongDao，全限定名称dao.SongDao
         * 全限定名称和namespace是一样的
         *
         * 2、方法名称queryAllSongInfos()  这个方法名称是Mapper文件中的id
         *
         *
         * 3、通过songDao中方法的返回值也可以确定Mybatis要调用的SqlSesion方法
         * 如果返回值是List，调用的是SqlSesion的selectList()方法
         * 如果返回值int，或者非List，则看mapper文件中的标签是<insert><update>就会调用
         * SqlSession的insert，update等方法
         *
         * mybatis的动态代理：根据dao的方法调用，获取执行sql语句的信息
         *  根据你dao接口创建出一个dao接口的实现类，并创建这个类的对象
         *  完成SqlSession调用方法，访问数据库
         */
        List<Song> songs = songDao.queryAllSongInfos();
        for (Song song : songs) {
            System.out.println(song);
        }
    }
}
