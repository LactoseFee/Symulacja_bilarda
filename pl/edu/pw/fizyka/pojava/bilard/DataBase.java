package pl.edu.pw.fizyka.pojava.bilard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBase {
    public static void main(String[] args) {
        String URL = "jdbc:sqlite:identifier.sqlite";
        Connection connection = null;
        Statement s = null;

        try {
            //Connecting to the local database
            connection = DriverManager.getConnection(URL);

            //Creating statement object
            s = connection.createStatement();

            //Usuwanie, jesli taka tabela juz istnieje
            try {
                s.executeUpdate("DROP TABLE IF EXISTS identifier");
            } catch (SQLException e) {

            }

            //Tworzenie tabeli
            //s.executeUpdate("CREATE TABLE 'ballz' ('Id' int(16) unsigned NOT NULL auto_increment);");
        } catch (SQLException e) {
            System.out.println("Database processing has failed.");
            System.out.println("Reason: " + e.getMessage());
        } finally {
            if (connection != null) {//c.close();}
            }
        }
    }
}
