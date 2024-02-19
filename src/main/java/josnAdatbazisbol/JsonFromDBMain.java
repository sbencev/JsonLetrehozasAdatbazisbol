package josnAdatbazisbol;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonFromDBMain {

	public static void main(String[] args) {

		try {
			DBHandler.connect();
			Gson gsonObj = new Gson();
			List<Emp> employees = DBHandler.empRead();

			DBHandler.disconnect();

			System.out.println("\nAz adatbazisbol beolvasott lista:");
			for (Emp emp : employees) {

				System.out.println(emp);

			}

			FileWriter fw = new FileWriter("empDatas.json");
			gsonObj.toJson(employees, fw);

			Gson gsonBeauty = new GsonBuilder().setPrettyPrinting().create();
			FileWriter fw2 = new FileWriter("empDatasBeauty.json");
			gsonBeauty.toJson(employees, fw2);

			fw.close();
			fw2.close();

			// beolvasas json fajlbol:

			Gson gsonObjFromJson = new Gson();

			Type listOfEmpClass = new TypeToken<ArrayList<Emp>>() {
			}.getType();

			List<Emp> empFromJson = gsonObjFromJson.fromJson(new FileReader("empDatasBeauty.json"), listOfEmpClass);

			System.out.println("\nA fajlbol beolvasott lista:");
			for (Emp emp : empFromJson) {

				System.out.println(emp);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} catch (IOException e) {

			System.out.println("I/O hiba!");

		}

	}

}
