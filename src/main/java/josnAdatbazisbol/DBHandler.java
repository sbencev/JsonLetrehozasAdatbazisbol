package josnAdatbazisbol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {

	private static Connection connect;
	private static PreparedStatement sqlStatement;

	private DBHandler() {

	}

	public static void connect() throws SQLException {

		try {
			String connectionString = "jdbc:mysql://localhost:3306/dml?useSSL=false";
			connect = DriverManager.getConnection(connectionString, "root", "Attpzk78@");

			System.out.println("Sikeres kapcsolodas az adatbazishoz");
		} catch (Exception e) {

			throw new SQLException("A csatlakozas sikertelen! " + e.getMessage());

		}

	}

	public static void disconnect() throws SQLException {
		try {
			connect.close();
		} catch (Exception e) {

			throw new SQLException("A kapcsolat bontasa sikertelen! " + e.getMessage());

		}
	}

	public static List<Emp> empRead() throws SQLException {

		try {
			List<Emp> employees = new ArrayList<Emp>();

			sqlStatement = connect.prepareStatement("SELECT ename,job,sal from emp");

			ResultSet res = sqlStatement.executeQuery();
			while (res.next()) {

				employees.add(new Emp(res.getString("ename"), res.getString("job"), res.getInt("sal")));

			}
			res.close();

			return employees;

		} catch (Exception e) {

			throw new SQLException("Az adatbazisbol olvasas sikertelen! " + e.getMessage());

		}
	}

}
