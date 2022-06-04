package base.repository;

import base.domain.BaseDomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepositoryImpl<T extends BaseDomain<ID>, ID>
        implements BaseRepository<T, ID> {

    protected final Connection connection;


    protected BaseRepositoryImpl(Connection connection) {
        if (connection == null) {
            throw new RuntimeException("connection is null");
        }
        this.connection = connection;
    }

    @Override
    public void deleteBYId(ID id) throws SQLException {
        String deleteQuery = "DELETE  FROM " + getDomainTableName() + " WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

        preparedStatement.setLong(1, (Long) id);
        preparedStatement.executeUpdate();
    }



    @Override
    public T getById(ID id) throws SQLException {
        String getByIdQuery = "SELECT*FROM" + getDomainTableName() + "WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(getByIdQuery);

        preparedStatement.setLong(1, (Long) id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return convertResultSetToDomain(resultSet);
        }

        return null;
    }

    @Override
    public List<T> findAll() throws SQLException {
        String findAllQuery = "SELECT * FROM"+getDomainTableName();
        PreparedStatement preparedStatement=connection.prepareStatement(findAllQuery);

        ResultSet resultSet= preparedStatement.executeQuery();
        return  convertResultSetToDomainList(resultSet);

    }

    @Override
    public Long countAll() throws SQLException {
        String countAllQuery = "SELECT COUNT(*) FROM"+getDomainTableName();
        PreparedStatement preparedStatement=connection.prepareStatement(countAllQuery);

        ResultSet resultSet= preparedStatement.executeQuery();
        return resultSet.next() ? resultSet.getLong(1):0;

    }

    protected List<T> convertResultSetToDomainList(ResultSet resultSet) throws SQLException {
        List<T> domainList= new ArrayList<>();
        while (resultSet.next()){
            domainList.add(convertResultSetToDomain(resultSet));

        }
        return domainList;
    }

    public abstract String getDomainTableName();
    public abstract T convertResultSetToDomain(ResultSet resultSet);
}
