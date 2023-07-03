package nou.hello.spring4.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Board {
    private String bno;
    private String title;
    private String userid;
    private String regdate;
    private String views;
    private String contents;
}
