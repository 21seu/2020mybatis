package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by 21seu.ftj on 2020/9/27 6:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SongDto {

    private String singer;

    private String language;
}
