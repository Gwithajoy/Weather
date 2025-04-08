package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.respository.JdbcMemoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional // 데이터베이스 테스트 할 때 많이 사용되는 어노테이션 -> 디비 데이터를 안건든다.
public class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest(){

        // given: ~한 것이 주어지고
        Memo newMemo = new Memo(2, "insertMemoTest");

        // when ~을 했을때
        jdbcMemoRepository.save(newMemo);

        // then ~처럼 이렇게 될 것이다.
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "insertMemoTest" );
    }



    @Test
    void findAllMemoTest () {
        List<Memo> memoList = jdbcMemoRepository.findAll();
        System.out.println(memoList);
        assertNotNull(memoList);
    }

}
