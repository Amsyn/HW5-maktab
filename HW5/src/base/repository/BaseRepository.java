package base.repository;

import base.domain.BaseDomain;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T extends BaseDomain<ID>,ID> {

    T save(T t);

    T update(T t);

    void deleteBYId(ID id) throws SQLException;

    T getById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    Long countAll() throws SQLException;



}
