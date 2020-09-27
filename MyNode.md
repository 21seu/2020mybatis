# #_Mybatis个人学习笔记_

######20200920

## 第一章

#### 1.三层架构

* 界面层：和用户打交道，接收用户的请求参数，显示处理结果的。(jsp,html,servlet)
* 业务逻辑层：接受了界面层传递的数据，计算逻辑，调用数据库获取数据
* 数据访问层：访问数据库，执行对数据的查询，修改，删除等等

#### 2.三层对应的包

* 界面层：controller包(servlet)
* 业务逻辑层：service包(XXXService类)
* 数据访问层：dao/mapper包(XXXDao/XXXMapper类)

#### 3.三层种类的交互

* 用户使用界面层-->业务逻辑层-->数据访问层（持久层）-->数据库（mysql）

#### 4.三层对应的处理框架

* 界面层---servlet---springmvc（框架）
* 业务逻辑层---service类---spring（框架）
* 数据访问层---dao类--mybatis（框架）

#### 5.框架

* 框架是一个舞台，一个模板
* 模板：
>>1、规定好了一些条款，内容。
>>2、加入自己的东西
* 框架是一个模板
>>1、框架中定义好了一些功能，这些功能是可用的。
>>2、可以加入项目中自己的功能，这些功能可以利用框架中写好的功能。
* 框架是一个软件，半成品的软件，定义好了一些基础功能，需要加入你的功能就是完整的。基础功能是可重复使用的，可升级的。
* 框架特点：
>>1、一般不是全能的，不能干所有事情
>>2、框架是针对某一个领域有效。特长在某一个方面，比如mybatis做数据库操作强，但是他不能做其他的
>>3、框架是一个软件

######20200922

#### 6.mybatis框架

>>一个框架，早期叫ibatis，代码在GitHub
>>mybatis是Mybatis SQL Mapper Framework for Java(sql映射框架)
>>1.sql mapper：sql映射（可以吧数据库中的一行数据映射为一个java对象，一行数据可以看做是一个java对象，操作这个对象，就相当于操作表中数据）
>>2.Data Access Objects(DAOs)：数据访问，对数据库执行增删改查

#### 7.mybatis提供了那些功能

1.提供了创建Connection,Statement,ResultSet的能力，不用开发人员创建这些对象了
2.提供了执行sql语句的能力，不用你执行sql
3.提供了循环sql，把sql的结果转为java对象，List集合的能力
    

```java
while(rs.next()){
        Student stu = new Student();
        stu.setId(rs.getInt("id"));
        stu.setName(rs.getString("name"));
        //从数据库取出数据转为Student对象，封装到List集合
        stuList.add(stu);
    }
```
4.提供了关闭资源的能力，不用你关闭Connection,Statement,ResultSet
5.开发人员只需要提供sql语句---mybatis处理sql---开发人员得到List集合或Java对象（表中的数据）
6、总结：mybatis是一个sql映射框架，提供了数据库的操作能力，增强的JDBC，使用mybatis让开发人员集中精力写sql就可以了，不必关心Connection,Statement,ResultSet的创建，销毁，sql的执行





## 第二章

#### 1.mybatis入门案例

>实现步骤
* 1.新建一张数据库表mybatis_song
```sql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mybatis_song
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_song`;
CREATE TABLE `mybatis_song`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '歌曲id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '曲目名称',
  `singer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '演唱者',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别',
  `writer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语言',
  `issudate` datetime(0) NULL DEFAULT NULL COMMENT '发行日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mybatis_song
-- ----------------------------
INSERT INTO `mybatis_song` VALUES (1, '旋木', '王菲', '流行', '林夕', '国语', '2020-05-17 07:59:46');
INSERT INTO `mybatis_song` VALUES (2, '曾静的你', '许巍', '流行', '许巍', '国语', '2020-05-05 08:00:20');
INSERT INTO `mybatis_song` VALUES (3, '理想三旬', '陈鸿宇', '民谣', '唐映枫', '国语', '2020-04-14 08:01:17');
INSERT INTO `mybatis_song` VALUES (7, '给我一首歌的时间', '周杰伦', '流行1', '周杰伦', '国语', '2020-05-17 12:49:53');

SET FOREIGN_KEY_CHECKS = 1;
```
* 2.加入maven的mybatis坐标，mysql驱动的坐标
```xml
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.5</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.18</version>
        </dependency>
```
* 3.创建实体类，Song
```java
package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by 21seu.ftj on 2020/9/22 8:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Song {
    
    private Integer id;

    private String name;

    private String singer;

    private String category;

    private String writer;

    private String language;

    private Date issudate;
}
```
* 4.创建持久层的dao接口，定义操作数据库的方法
```java
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

```
* 5.创建一个mybatis使用的配置文件
    >>sql映射文件，写sql语句的。一般一个表一个sql映射文件。
    >>这个文件是xml文件。
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="dao.SongDao">
    <select id="queryAllSongInfos" resultType="pojo.Song">
    <!--
    id：你要执行的sql语法的唯一表示，mybatis会使用这个id的值来找到要执行的sql语句
        可以自定义，但是要求你使用接口中的方法名称
        
    resultType：表示结果类型，是sql语句执行后的到ResultSet，遍历这个ResultSet得到java对象的类型
        值写的是类型的全限定名称
    -->
        select * from mybatis_song
    </select>
</mapper>
<!--
    sql映射文件：写sql语句，mybatis会执行这些sql
    1、指定约束文件
    <!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

     mybatis-3-mapper.dtd是约束文件的名称，扩展名是dtd的

    2、约束文件作用：限制，检查当前文件中出现的标签，属性必须符合mybatis的要求

    3、mapper  是当前稳健的根标签，必须的
       namespace：叫做命名空间空间，唯一值的，可以是自定义的字符串
                  要求：使用dao接口的全限定名称
    4、在当前文件中，可以使用特定的标签，表示数据库的特定操作。
        <select>：查询
        <update>：表示更新数据库操作
        <insert>：标书插入数据
        <delete>：表示删除数据
-->
```
* 6.创建Mybatis的主配置文件：
    >>一个项目就一个主配置文件。
    >>主配置文件提供了数据库的连接信息和sql映射文件的位置信息
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--环境配置：数据库的连接信息
        default：必须和某个environments的id值一样。
        告诉mybatis使用哪个数据库的连接信息。也就是访问哪个数据库-->
    <environments default="development">
        <!--environment：一个数据库信息的配置，环境
            id：一个胃一直，自定义的，表示环境的名称-->
        <environment id="development">
            <!--mybatis的事务类型
                type：JDBC（表示使用Jdbc中的Connection对象的commit，rollback做事务处理）-->
            <transactionManager type="JDBC"/>
            <!--datatSource表示数据域，连接数据库的
                type：表示数据源的类型，POOLED表示使用连接池-->
            <dataSource type="POOLED">
                <!--name里的命名都是固定的，不能自定义-->
                <!--数据库驱动类名-->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <!--连接数据库的url字符串-->
                <property name="url" value="jdbc:mysql:///mybatis?serverTimezone=UTC&amp;characterEncoding=utf8"/>
                <!--访问数据库的用户名称-->
                <property name="username" value="root"/>
                <!--访问数据库的密码-->
                <property name="password" value="password"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mapper/SongMapper.xml"/>
    </mappers>
</configuration>
```
* 7.创建使用Mybatis类，通过Mybatis访问数据库
```java
package common;


import dao.SongDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Song;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 21seu.ftj on 2020/9/22 23:04
 */
public class MybatisUtils {

    //定义Mybatis主配置文件的名称，从类路径的根开始（target/classes）
    private static String resource = "mybatis-config.xml";
    private static SqlSession session = null;

    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        //创建了SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //获取sqlSession对象，从SqlSessionFactory中获取
        session = sqlSessionFactory.openSession();
        SongDao mapper = session.getMapper(SongDao.class);
        //写法2
        //List<Song> songList = session.selectList("dao.SongDao.queryAllSongInfos");
        List<Song> songs = mapper.queryAllSongInfos();
        for (Song song : songs) {
            System.out.println(song);
        }
        session.close();
    }
}
```
#### 2.配置日志功能
```xml
<settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

<img src="C:\Users\21seu.ftj\AppData\Roaming\Typora\typora-user-images\image-20200923230618060.png" alt="image-20200923230618060" style="zoom:80%;" />

#### 3.主要类的介绍
1.Resources：Mybatis中的一个类，负责读取主配置文件
```java
InputStream is = Resources.getResourceAsStream(mybatis-config.xml);
```
2.SqlSessionFactoryBuilder：创建SqlSessionFactory对象
```java
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
```
3.SqlSessionFactory：重量级对象，程序创建这个对象耗时比较长，使用资源比较多。在整个项目中，有一个就够用了。
* SqlSqlSessionFactory接口：
>>实现类：DefaultSqlSessionFactory
>>作用：获取sqlSession对象
>>```java
SqlSession session = sqlSessionFactory.openSession();
>>```
>>openSession()方法说明：
>>>* openSession()：无参数，获取非自动提交事务的SqlSession对象
>>>* openSession(boolean)：带参数，true：获取自动提交事务的SqlSession对象。false：获取非自动提交事务的SqlSession对象

4.SqlSession

* SqlSession接口：定义了操作数据库的方法，例如selectOne(),selectList(),insert()...
* SqlSession接口实现类DefaultSqlSession
* 使用要求：SqlSession对象不是线程安全的，需要在方法的内部使用，在执行sql语句之前，使用openSession()获取SqlSession对象，在执行完sql语句后，需要关闭它，执行SqlSession的close()，这样能保证它的使用是线程安全的。



## 第三章

#### 1.动态代理：使用sqlSession.getMapper(dao接口.class) 获取这个dao接口的对象


#### 2.传入参数：从java代码中把数据传入到mapper文件的sql语句中
##### 1.parameterType：写在mapper文件中的一个属性，表示dao接口中方法的参数的数据类型

##### 2.一个简单类型的参数:

* 简单类型：mybatis把java的基本数据类型和String都叫简单类型
* 在mapper文件获取简单类型的一个参数，使用#{任意字符}
```java
public Song selectSongById(Integer id);
```
```xml
<select id="selectSongById" parameterType="integer" resultType="pojo.Song">
        select * from mybatis_song where id = #{id,jdbcType=INTEGER}
</select>
```


##### 3.多个参数，使用@Param命名参数

接口：

```java
public List<Song> selectMulitParam(String singer, String language);
```
使用：@Param("参数名") String name
```java
public List<Song> selectMulitParam(@Param("singer") String singer, @Param("language")String language);
```
```xml
<select id="selectMulitParam" resultType="pojo.Song">
        select * from mybatis_song where singer = #{singer} and language = #{language}
    </select>
```



##### 4.多个参数--使用对象

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryParam {

    private String singer;

    private String language;
```



```java
public List<Song> selectMultiObject(QueryParam queryParam);
```

```
<select id="selectMultiObject" resultType="pojo.Song">
        select * from mybatis_song where singer = #{singer} and language = #{language}
    </select>
```



##### 5.多个参数--按位置

```java
/**
     * 多个参数-简单类型，按位置传值
     * mybatis3.4之前使用#{0} #{1}
     * mybatis3.4之后，使用#{arg0} #{arg1}
     * @param singer
     * @param language
     * @return
     */
    public List<Song> selectMultiPosition(String singer, String language);
```

```xml
<!--按位置传值-->
    <select id="selectMultiPosition" resultType="pojo.Song">
        select * from mybatis_song where singer = #{arg0} and language = #{arg1}
    </select>
```



##### 6.多个参数--使用Map

```java
public List<Song> selectMultiMap(Map<String,Object> map);
```

```xml
<select id="selectMultiMap" resultType="pojo.Song">
        select * from mybatis_song where singer = #{singer} and language = #{language}
    </select>
```



#### 3.#和$

#：占位符，告诉mybatis实际的参数值代替，并使用PreparedStatement对象执行sql语句，#{...}代替sql语句的“？”。这样做更安全，更迅速，通常也是首选做法。

```sql
select * from mybatis_song where singer = ? and language = ?
```



$ 字符串替换，告诉Mybatis使用$包含的"字符串"替换所在位置。使用Statement把sql语句和${}的内容连接起来。主要用在替换表名，列名，不同列排序等操作。

```sql
select * from mybatis_song where id = 1
```

其实这里的sql是拼接的(使用Statement对象，执行的效率比PreparedStatement低)：

```java
String sql = "select * from mybatis_song where id =" + "1" 
```

$可以替换表名或列名，你能确定数据是安全的，可以使用；否则会出现sql注入问题。

```java
List<Song> selectUse$Order(@Param("singer") String singer);
```

```xml
<!--$替换列名-->
    <select id="selectUse$Order" resultType="pojo.Song">
         select * from mybatis_song order by ${singer}
    </select>
```

```java
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
```

![image-20200926222325387](C:\Users\21seu.ftj\AppData\Roaming\Typora\typora-user-images\image-20200926222325387.png)



#和$区别：

* #使用？在sql语句中做占位，使用PreparedStatement执行SQL，效率高

* #能够避免sql注入，更安全

* $不使用占位符，是字符串连接的方式，使用Statement对象执行SQL，效率低

* $有sql注入的风险，缺乏安全性

* $在替换列名或表名时使用

  

#### 4.封装Mybatis输出结果
1.resultType结果类型，指sql语句执行完毕后，数据转为的java对象

  resultType结果类型的它值1、类型的全限定名称 2、类型的别名，例如java.lang.Integer别名为Int

处理方式：
* mybatis执行sql语句，然后mybatis调用类的无参构造方法，创建对象
* mybatis把result指定列值赋给同名的属性

2.定义自定义类型的别名（最好使用全限定名称，如果定义了不同包，同名类的别名就会出现问题）
* 在Mybatis主配置文件中定义，使用<typeAlias>定义别名
* 可以在resultType中使用
```xml
<typeAliases>
        <!--可以指定一个类型一个自定义的别名
        type：自定义类型的全限定名称
        alias：别名（短小，容易记忆的）
        -->
        <typeAlias type="pojo.Song" alias="Song"/>
        <typeAlias type="dto.SongDto" alias="SongDto"/>
    <!--第二种方式
        <package> name是包名，这个包中的所有类，类名就是别名（类名不区分大小写）
        -->
    	<package name="pojo"/>
    </typeAliases>
```

3.resultMap：结果映射，指定列名和java对象的属性对应关系
* 自定义列值赋值给哪个属性
* 当列名和属性名不一样时，使用resultMap
* resultMap和resultType不要一起用，二选一

4.like模糊查询
* 方式一：直接传%参数%
```xml
<select id="selectLikeOne" resultType="Song">
        select * from mybatis_song where name like #{name,jdbcType=VARCHAR}
</select>
```
* 方式二：mapper.xml文件中拼接
```xml
<select id="selectLikeTwo" resultType="Song">
        select * from mybatis_song where name like "%" #{name,jdbcType=VARCHAR} "%"
</select>
```
* 方式三：concat拼接
```xml
<select id="selectLikeTwo" resultType="Song">
        select * from mybatis_song where name like <!--concat('%',#{name,jdbcType=VARCHAR},'%')-->
        concat(concat('%',#{name}),'%')
</select>
```



## 第四章

#### 1.动态sql

* sql的内容是可以变化的，可以根据条件获取到不同的sql语句。
* 主要是where的部分发生变化
* 动态sql的实现，使用的是Mybatis提供的标签：<if>,<where>,<foreach>

#### 2.if标签
* <if>是片段条件的，语法：
```xml
<if test="判断java对象的属性值">
部分sql语句
</if>
```
* 示例
```java
public interface SongDao {

    /**
     * 动态sql要使用Java对象作为参数
     * @param song
     * @return
     */
    public List<Song> selectSongsIf(Song song);
}
```
```xml
<!--if 动态sql-->
    <select id="selectSongsIf" resultType="Song">
        select * from mybatis_song
        where 1 = 1  <!--这里1=1这样做是因为如果第一个条件不成立，但是第二个条件成立时，那么sql就会变成
        select * from mybatis_song where and language = ? 语法错误  或者简单粗暴直接使用<where></where>
        -->
        <if test="singer != null and singer != ''">
            singer = #{singer,jdbcType=VARCHAR}
        </if>
        <if test="language != null and language != ''">
            and language = #{language,jdbcType=VARCHAR}
        </if>
    </select>
```



#### 3.where标签

* 用来包含多个<if>，当if有一个成立，where标签会自动增加一个where关键字，并去掉if中多余的and，or等

```xml
<select id="selectSongsIf" resultType="Song">
        select * from mybatis_song
        <where>
        <if test="singer != null and singer != ''">
            singer = #{singer,jdbcType=VARCHAR}
        </if>
        <if test="language != null and language != ''">
            and language = #{language,jdbcType=VARCHAR}
        </if>
        </where>
    </select>
```



#### 4.foreach标签

* 循环java中的数据，list集合。主要用在sql的in语句中

* 手工拼接sql的方式

  ```java
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
  ```

* 使用foreach标签1：传递的参数是基本数据类型

  ```java
  //forEach用法1
      List<Song> selectForEachOne(List<Integer> id);
  ```

  ```xml
  <!--foreach用法1-->
      <select id="selectForEachOne" resultType="Song">
          select * from mybatis_song where id in
          <!--
          collection：表示接口中方法参数的类型，如果是数组使用array，如果是list集合使用list
          item：自定义的，表示数组和集合成员的变量
          open：循环开始时的字符
          close：循环结束时的字符
          separator：集合成员之间的分隔符
          -->
          <foreach collection="list" item="myId" open="(" close=")" separator=",">
              #{myId,jdbcType=INTEGER}
          </foreach>
      </select>
  ```

  

* 使用foreach标签1：传递的参数是对象

  ```java
  //forEach用法2
      List<Song> selectForEachTwo(List<Song> songs);
  ```

  ```xml
  <!--foreach用法2-->
      <select id="selectForEachTwo" resultType="Song">
          select * from mybatis_song where id in
          <foreach collection="list" item="song" open="(" close=")" separator=",">
              #{song.id,jdbcType=INTEGER}
          </foreach>
      </select>
  ```

  

#### 5.代码片段

* 步骤
  1. 先定义<sql id="自定义名称唯一"> sql语句，表名，字段等</sql>
  2. 在使用，<include refid="id的值"/>

```xml
<!--sql代码片段-->
    <sql id="sql1">
        select * from mybatis_song where id in
    </sql>

	<select id="selectForEachOne" resultType="Song">
        <!--select * from mybatis_song where id in-->
        <!--使用sql代码片段-->
        <include refid="sql1"/>
        	<foreach collection="list" item="myId" open="(" close=")" separator=",">
            	#{myId,jdbcType=INTEGER}
        	</foreach>
    </select>
```



#### 6.扩展trim标签

* mybatis的**trim**标签一般用于去除sql语句中多余的and关键字，逗号，或者给sql语句前拼接 “where“、“set“以及“values(“ 等前缀，或者添加“)“等后缀，可用于选择性插入、更新、删除或者条件查询等操作

* 属性

  |    **属性**     |                           **描述**                           |
  | :-------------: | :----------------------------------------------------------: |
  |     prefix      |                     给sql语句拼接的前缀                      |
  |     suffix      |                     给sql语句拼接的后缀                      |
  | prefixOverrides | 去除sql语句前面的关键字或者字符，该关键字或者字符由prefixOverrides属性指定，假设该属性指定为"AND"，当sql语句的开头为"AND"，trim标签将会去除该"AND" |
  | suffixOverrides | 去除sql语句后面的关键字或者字符，该关键字或者字符由suffixOverrides属性指定 |

* 使用trim标签去掉多余的and

  ```xml
  <trim prefix="WHERE" prefixOverrides="AND">
  	<if test="state != null">
  	  state = #{state}
  	</if> 
  	<if test="title != null">
  	  AND title like #{title}
  	</if>
  	<if test="author != null and author.name != null">
  	  AND author_name like #{author.name}
  	</if>
  </trim>
  
  ```

* 使用trim标签去掉多余的逗号

  ```xml
  <insert id="...">
  <trim prefix="(" suffix=")" suffixOverrides=",">
      <if test="roleName != null">
      role_name,
      </if>
      <if test="note != null">
      note
      </if>
  </trim>
  <trim prefix="(" suffix=")" suffixOverrides=",">
      <if test="roleName != null">
      #{roleName,jdbcType=VARCHAR},
      </if>
      <if test="note != null">
      #{note,jdbcType=VARCHAR}
      </if>
  </trim>
  </insert>
  ```

  