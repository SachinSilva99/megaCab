package org.example.demo1.repository.repo.transaction;

import org.example.demo1.exception.AppException;
import org.example.demo1.repository.repo.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;


public class TransactionManager {

    public static void executeTransaction(TransactionAction action) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Start transaction

            action.execute(connection); // Perform the database operation

            connection.commit(); // Commit if successful
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback on failure
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace(); // Log rollback failure
                }
            }
            e.printStackTrace();
            throw new AppException("Transaction failed.");
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
    public interface TransactionAction {
        void execute(Connection connection) throws Exception;
    }
}