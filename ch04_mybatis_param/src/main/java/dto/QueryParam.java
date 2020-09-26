package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by 21seu.ftj on 2020/9/26 21:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryParam {

    private String singer;

    private String language;
}
