package com.freemarker.generic;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface GenericOperations {

    Optional<Integer> findInt(String sql, MapSqlParameterSource params);
    Optional<Long> findLong(String sql, MapSqlParameterSource params);
    Optional<String> findString(String sql, MapSqlParameterSource params);
    Optional<Boolean> findBoolean(String sql, MapSqlParameterSource params);
    Optional<BigDecimal> findBigDecimal(String sql, MapSqlParameterSource params);

    <T> Optional<T> findPrimitiveType(String sql, MapSqlParameterSource params, Class<T> cls);

    <E> Optional<E> findAnyObject(String sql, MapSqlParameterSource params, RowMapper<E> rowMapper);
    <E> List<E> findAnyList(String sql, MapSqlParameterSource params, RowMapper<E> rowMapper);
    <E> List<E> findAnyListPrimitiveType(String sql, MapSqlParameterSource params, Class<E> cls);

    long executeAny(String sql, MapSqlParameterSource params);
    long executeAny(String sql, MapSqlParameterSource params, KeyHolder holder);
    void processQuery(String sql, MapSqlParameterSource params, RowCallbackHandler rch);

    default MapSqlParameterSource compose(String key, Object value) {
        return new MapSqlParameterSource().addValue(key, value);
    }
}
