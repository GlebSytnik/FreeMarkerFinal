package com.freemarker.generic;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericOperationsImpl extends Operations implements GenericOperations {

    @Override
    public <E> List<E> findAnyList(String sql, MapSqlParameterSource params, RowMapper<E> rowMapper) {
        try {
            return operations.query(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public <E> List<E> findAnyListPrimitiveType(String sql, MapSqlParameterSource params, Class<E> cls) {
        try {
            return operations.queryForList(sql, params, cls);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Integer> findInt(String sql, MapSqlParameterSource params) {
        return findPrimitiveType(sql, params, Integer.class);
    }

    @Override
    public Optional<Long> findLong(String sql, MapSqlParameterSource params) {
        return findPrimitiveType(sql, params, Long.class);
    }

    @Override
    public Optional<String> findString(String sql, MapSqlParameterSource params) {
        return findPrimitiveType(sql, params, String.class);
    }

    @Override
    public Optional<Boolean> findBoolean(String sql, MapSqlParameterSource params) {
        return findPrimitiveType(sql, params, Boolean.class);
    }

    @Override
    public Optional<BigDecimal> findBigDecimal(String sql, MapSqlParameterSource params) {
        return findPrimitiveType(sql, params, BigDecimal.class);
    }

    @Override
    public <T> Optional<T> findPrimitiveType(String sql, MapSqlParameterSource params, Class<T> cls) {
        try {
            return Optional.ofNullable(operations.queryForObject(sql, params, cls));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (IncorrectResultSizeDataAccessException unhandled) {
            throw unhandled;
        }
    }

    @Override
    public <E> Optional<E> findAnyObject(String sql, MapSqlParameterSource params, RowMapper<E> rowMapper) {
        try {
            return Optional.ofNullable(operations.queryForObject(sql, params, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (IncorrectResultSizeDataAccessException unhandled) {
            throw unhandled;
        }
    }

    @Override
    public void processQuery(String sql, MapSqlParameterSource params, RowCallbackHandler rch) {
        operations.query(sql, params, rch);
    }

    @Override
    public long executeAny(String sql, MapSqlParameterSource params) {
        return operations.update(sql, params);
    }

    @Override
    public long executeAny(String sql, MapSqlParameterSource params, KeyHolder holder) {
        return operations.update(sql, params, holder);
    }
}
