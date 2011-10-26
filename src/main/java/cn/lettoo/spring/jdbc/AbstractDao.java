package cn.lettoo.spring.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDao<T> implements IDao<T> {

    protected JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
