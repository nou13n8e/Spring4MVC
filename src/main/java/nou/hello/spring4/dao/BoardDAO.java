package nou.hello.spring4.dao;

import nou.hello.spring4.model.Board;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface BoardDAO {

    List<Board> selectBoard(int snum);
    Board selectOneBoard(String bno);
}
