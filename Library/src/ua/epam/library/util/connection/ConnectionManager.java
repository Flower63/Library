package ua.epam.library.util.connection;

import java.sql.Connection;

public abstract class ConnectionManager {
	protected abstract Connection getConnection();
}
