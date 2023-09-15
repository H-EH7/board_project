package eh7.board.domain.view;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcViewRepository implements ViewRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcViewRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("views")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void save(View view) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(view);
        simpleJdbcInsert.execute(param);
    }

    @Override
    public List<View> findByPostId(Long postId) {
        String sql = "select * from views where post_id = :id";
        SqlParameterSource param = new MapSqlParameterSource("id", postId);
        return template.query(sql, param, viewRowMapper());
    }

    private RowMapper<View> viewRowMapper() {
        return BeanPropertyRowMapper.newInstance(View.class);
    }
}
