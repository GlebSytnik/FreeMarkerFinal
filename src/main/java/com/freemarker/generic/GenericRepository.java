package com.freemarker.generic;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public interface GenericRepository<E> extends GenericOperations {

    String PK_NAME = "id";

    default Optional<E> findById(long id) {
        return findByField(PK_NAME, id);
    }

    default Optional<E> findByField(String field, Object value) {
        return findByMap(Collections.singletonMap(field, value));
    }

    default Optional<E> findByMap(Map<String, Object> fieldToValue) {
        if (fieldToValue == null || fieldToValue.isEmpty()){
            return Optional.empty();
        }

        String where = fieldToValue.entrySet().stream()
                .map(e -> resolveArg(e.getKey(), e.getValue()))
                .collect(Collectors.joining(" AND "));

        String sql = String.format("SELECT * FROM %s WHERE %s", getTableName(), where);
        return findObject(sql, new MapSqlParameterSource(fieldToValue));
    }

    default Optional<E> findObject(String sql, MapSqlParameterSource params) {
        return findAnyObject(sql, params, getRowMapper());
    }

    default List<E> findListByIds(Collection<Long> ids) {
        return findListByField(PK_NAME, ids);
    }

    default List<E> findListByField(String field, Object value) {
        boolean collection = value instanceof Collection;
        if (collection && CollectionUtils.isEmpty((Collection<?>) value)) {
            return new ArrayList<>();
        }
        String sql = String.format("SELECT * FROM %s WHERE %s", getTableName(), resolveArg(field, value));
        return findList(sql, compose(field, value));
    }

    default List<E> findListByMap(Map<String, Object> fieldToValue) {
        if (fieldToValue == null || fieldToValue.isEmpty()){
            return new ArrayList<>();
        }

        String where = fieldToValue.entrySet().stream()
                .map(e -> resolveArg(e.getKey(), e.getValue()))
                .collect(Collectors.joining(" AND "));

        String sql = String.format("SELECT * FROM %s WHERE %s", getTableName(), where);
        return findList(sql, new MapSqlParameterSource(fieldToValue));
    }

    default List<E> findList(String sql, MapSqlParameterSource params) {
        return findAnyList(sql, params, getRowMapper());
    }

    default int deleteById(long id) {
        String sql = String.format("DELETE FROM %s WHERE %s=:%s", getTableName(), PK_NAME, PK_NAME);
        return (int) execute(sql, compose(PK_NAME, id));
    }

    default long execute(String sql, MapSqlParameterSource params) {
        return executeAny(sql, params);
    }

    String getTableName();

    RowMapper<E> getRowMapper();

    default String resolveArg(String key, Object value){
        return value instanceof Collection ?
                String.format(" %s IN (:%s) ", key, key) :
                String.format(" %s=:%s ", key, key);
    }
}
