package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for managing database connections.
 *
 * @author Technical University of Cluj-Napoca, Romania Distributed Systems
 *         Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @since Apr 03, 2017
 * @source http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-create-database-example/
 */
public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/managementsys"; //jdbc:mysql://localhost:3306/schooldb
	private static final String USER = "root";
	private static final String PASS = "rootadmin";

	private static ConnectionFactory singleInstance = new ConnectionFactory();

	/**
	 * Private constructor to prevent instantiation from outside.
	 */
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Driver not found", e);
		}
	}

	/**
	 * Creates a new database connection.
	 *
	 * @return A database connection.
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occurred while trying to connect to the database", e);
		}
		return connection;
	}

	/**
	 * Retrieves a database connection.
	 *
	 * @return A database connection.
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	/**
	 * Closes a database connection.
	 *
	 * @param connection The database connection to close.
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occurred while trying to close the connection", e);
			}
		}
	}

	/**
	 * Closes a database statement.
	 *
	 * @param statement The database statement to close.
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement", e);
			}
		}
	}

	/**
	 * Closes a result set.
	 *
	 * @param resultSet The result set to close.
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occurred while trying to close the ResultSet", e);
			}
		}
	}
}