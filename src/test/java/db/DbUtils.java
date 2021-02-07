package db;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ge.odvali.ebookstore.entities.BuyBookDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {

    @Inject
    @Named("db_host_url")
    private String dbHostUrl;

    @Inject
    @Named("db_username")
    private String dbUserName;

    @Inject
    @Named("db_password")
    private String dbPassword;


    private JdbcTemplate getJdbcTemplate() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new org.h2.Driver());
        ds.setUrl(dbHostUrl);
        ds.setUsername(dbUserName);
        ds.setPassword(dbPassword);
        return new JdbcTemplate(ds);
    }


    public Map<String, Object> getUserBookData(Map<String, Integer> params) {
        Integer userId = params.get("userId");
        Integer bookId = params.get("bookId");
        String sql = "SELECT * \n" +
                "from  USER_BOOKS\n" +
                "where USER_ID=" + userId + "\n" +
                "and BOOK_ID=" + bookId + ";";

        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        String res;
        if (!rows.isEmpty())
            res = "FOUND!";
        else
            res = "NOT FOUND";
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("result", res);
        return wrapper;
    }


    public Map<String, Object> getUserNotification(Map<String, String> params) {
        String userId = params.get("userId");
        String message = params.get("message");
        String sql = "SELECT * \n" +
                "from  USER_NOTIFICATION \n" +
                "where USER_ID=" + userId + "\n" +
                "and MESSAGE=" + "'" + message + "';";
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        String res;
        if (!rows.isEmpty())
            res = "FOUND!";
        else
            res = "NOT FOUND";
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("result", res);
        return wrapper;
    }


}
