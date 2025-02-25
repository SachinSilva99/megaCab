package org.example.demo1.repository.repo.transaction;

import org.example.demo1.exception.AppException;
import org.example.demo1.repository.repo.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;


public class TransactionManager {

    public static <T> T executeTransaction(TransactionFunction<T> action) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Start transaction

            T result = action.execute(connection); // Perform the database operation

            connection.commit(); // Commit if successful
            return result;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback on failure
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace(); // Log rollback failure
                }
            }
            e.printStackTrace();
            throw new RuntimeException("Transaction failed.");
        } finally {
            if (connection != null) {
                try {
                    connection.close(); // Always close the connection
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace(); // Log closing failure
                }
            }
        }
    }

    @FunctionalInterface
    public interface TransactionFunction<T> {
        T execute(Connection connection) throws Exception;
    }

}