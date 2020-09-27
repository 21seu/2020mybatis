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

    public Song(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
