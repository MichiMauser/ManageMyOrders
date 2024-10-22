package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;
import model.Porder;
import model.Product;

/**
 * Abstract class for Data Access Objects.
 *
 * @param <T> The type of the object managed by the DAO.
 * @Author Technical University of Cluj-Napoca, Romania Distributed Systems
 *         Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	/**
	 * Constructs an AbstractDAO instance.
	 */
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Creates a select query based on the provided field and all flag.
	 *
	 * @param field The field to filter by, or null if no filter is needed.
	 * @param all   Flag indicating whether to select all fields.
	 * @return The generated select query.
	 */
	private String createSelectQuery(String field, boolean all) {
		StringBuilder sb = new StringBuilder();
		if (all) {
			sb.append("SELECT ");
			sb.append(" * ");
			sb.append(" FROM ");
			sb.append(type.getSimpleName());
		} else {
			sb.append("SELECT ");
			sb.append(" * ");
			sb.append(" FROM ");
			sb.append(type.getSimpleName());
			sb.append(" WHERE " + field + " =?");
		}
		return sb.toString();
	}

	/**
	 * Creates an insert query based on the provided field.
	 *
	 * @param field The object to be inserted.
	 * @return The generated insert query.
	 * @throws IllegalAccessException if access to the field is denied.
	 */
	private String createInsertQuery(T field) throws IllegalAccessException {

		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO ");
		sb.append(type.getSimpleName());

		sb.append(" VALUES ( ");
		Field[] fields = type.getDeclaredFields();
		int cnt = 0;
		for (Field field1 : fields) {

			field1.setAccessible(true);
			try {
				Object response = field1.get(field);
				if (response != null) {
					sb.append("'").append(response.toString()).append("'");
				} else {
					sb.append("NULL");
				}
			} catch (IllegalAccessException e) {
				LOGGER.log(Level.SEVERE, "Couldn't insert into the Query", e);
			}
			if (cnt < fields.length - 1) {
				sb.append(", ");
			}
			cnt++;
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Creates an update query based on the provided field.
	 *
	 * @param field The object to be updated.
	 * @return The generated update query.
	 * @throws IllegalAccessException if access to the field is denied.
	 */
	private String createUpdateQuery(T field) throws IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		int id;
		if (type.isInstance(new Client())) {
			Client c = (Client) field;
			id = c.getId();

		} else if (type.isInstance(new Porder())) {
			Porder c = (Porder) field;
			id = c.getId();

		} else {
			Product c = (Product) field;
			id = c.getId();
		}
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		Field[] fields = type.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				Object response = fields[i].get(field);
				sb.append(fields[i].getName()).append("=");
				if (response != null) {
					sb.append("'").append(response.toString()).append("'");
				} else {
					sb.append("NULL");
				}
			} catch (IllegalAccessException e) {
				LOGGER.log(Level.SEVERE, "Couldn't update the Query", e);
			}
			if (i < fields.length - 1) {
				sb.append(", ");
			}
		}
		if (type.getSimpleName().equals("Log")) {
			sb.append("WHERE BillNr = ");
		} else {
			sb.append("WHERE Id = ").append(id);
		}
		return sb.toString();
	}

	/**
	 * Creates a delete query based on the provided field.
	 *
	 * @param field The object to be deleted.
	 * @return The generated delete query.
	 * @throws IllegalAccessException if access to the field is denied.
	 */
	private String createDeleteQuery(T field) throws IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		int id;
		if (type.isInstance(new Client())) {
			Client c = (Client) field;
			id = c.getId();
		} else if (type.isInstance(new Porder())) {
			Porder c = (Porder) field;
			id = c.getId();
		} else {
			Product c = (Product) field;
			id = c.getId();
		}
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE Id = ").append(id);
		return sb.toString();
	}

	/**
	 * Finds all instances of type T.
	 *
	 * @return A list of all instances of type T.
	 */
	public List<T> findAll() {

		StringBuilder sb = new StringBuilder();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String query = createSelectQuery(null, true);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Finds an instance of type T by its ID.
	 *
	 * @param id The ID of the object to find.
	 * @return The instance of type T with the specified ID, or null if not found.
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id", false);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			try {
				return createObjects(resultSet).get(0);
			} catch (IndexOutOfBoundsException e) {
				// Handle exception
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Creates objects of type T from the given ResultSet.
	 *
	 * @param resultSet The ResultSet containing the data to be transformed into objects.
	 * @return A list of objects of type T.
	 */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T) ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();

					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException | IllegalAccessException | SQLException | InvocationTargetException | IntrospectionException e) {
			LOGGER.log(Level.SEVERE, "Error creating objects from ResultSet", e);
		}
		return list;
	}

	/**
	 * Inserts an object into the database.
	 *
	 * @param t The object to insert.
	 * @throws IllegalAccessException if access to the object's fields is denied.
	 */
	public void insert(T t) throws IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error inserting object into the database", e);
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

	}

	/**
	 * Updates an object in the database.
	 *
	 * @param t The object to update.
	 * @throws IllegalAccessException if access to the object's fields is denied.
	 */
	public void update(T t) throws IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createUpdateQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error updating object in the database", e);
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

	}

	/**
	 * Deletes an object from the database.
	 *
	 * @param t The object to delete.
	 * @throws IllegalAccessException if access to the object's fields is denied.
	 */
	public void delete(T t) throws IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDeleteQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error deleting object from the database", e);
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
}