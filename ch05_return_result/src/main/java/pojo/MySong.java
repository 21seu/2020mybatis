package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by 21seu.ftj on 2020/9/27 20:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MySong {

    private Integer songID;

    private String songName;

    private String songSinger;

}
