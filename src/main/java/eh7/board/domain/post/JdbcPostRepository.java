package eh7.board.domain.post;

import eh7.board.web.post.PostForm;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcPostRepository implements PostRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcPostRepository(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("posts")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Post save(Post post) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(post);
        Number key = jdbcInsert.executeAndReturnKey(param);
        post.setId(key.longValue());
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        String sql = "select * from posts where id=:id";
        try {
            SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
            Post post = template.queryForObject(sql, param, postRowMapper());
            return Optional.of(post);
        } catch (EmptyResultDataAccessException e) {
            //todo: 에러 변경
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAll() {
        String sql = "select * from posts";
        return template.query(sql, postRowMapper());
    }

    @Override
    public void update(Long id, PostForm postForm) {
        String sql = "update posts " +
                "set title=:title, content=:content " +
                "where id=:id";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("title", postForm.getTitle())
                .addValue("content", postForm.getContent())
                .addValue("id", id);

        template.update(sql, param);
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from posts where id=:id";
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        template.update(sql, param);
    }

    private RowMapper<Post> postRowMapper() {
        return BeanPropertyRowMapper.newInstance(Post.class);
    }
}
