package bll;

import dao.LogDAO;
import model.Log;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business logic layer for managing logs.
 */
public class LogBLL {
    private LogDAO logDAO = new LogDAO();

    /**
     * Constructs a LogBLL object.
     */
    public LogBLL() {
    }

    /**
     * Retrieves all logs.
     *
     * @return A list of logs.
     * @throws NoSuchElementException if no logs are found.
     */
    public List<Log> findAll() {
        List<Log> logList = logDAO.findAll();
        if (logList.isEmpty()) {
            throw new NoSuchElementException("No logs found");
        }
        return logList;
    }

    /**
     * Inserts a log into the database.
     *
     * @param log The log to be inserted.
     * @throws IllegalAccessException if the insertion operation is not allowed.
     */
    public void insert(Log log) throws IllegalAccessException {
        logDAO.insert(log);
    }
}