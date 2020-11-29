package freemarker.repository;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;
import java.util.List;

public class CookieRepositoryImpl implements CookieRepository {
    private final DataSource dataSource;
    private final SimpleJdbcTemplate template;

    private final String SQL_CREATE = "insert into auth (value) values (?);";
    private final String SQL_FIND = "select * from auth where value=?;";

    public CookieRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        template = new SimpleJdbcTemplate(dataSource);
    }

    public RowMapper<Cookie> cookieRowMapper = row -> new Cookie("AUTH", row.getString("value"));

    public void create(Cookie cookie) {
        template.execute(SQL_CREATE, cookie.getValue());
    }

    public boolean find(Cookie cookie) {
        List<Cookie> cookies = template.query(SQL_FIND, cookieRowMapper, cookie.getValue());
        return !cookies.isEmpty();
    }
}
