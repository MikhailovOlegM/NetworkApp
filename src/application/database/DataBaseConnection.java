package application.database;

import application.DraggableNode;
import application.device.CustomDevice;
import application.device.DeviceMapper;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/networkApp?serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String password = "76222377";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public DataBaseConnection() {
    }

    /*
    create table devices(
    id VARCHAR(40) PRIMARY KEY,
    type varchar(40)
     );

    create table connectedDevices(
    parent VARCHAR(40),
    child VARCHAR(40),
    FOREIGN KEY(parent) REFERENCES devices(id),
    FOREIGN KEY(child) REFERENCES devices(id)
    );
    */
    public void insertValue(DraggableNode node) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {
            String id = node.getId();
            String style = String.valueOf(node.getStyleClass());
            String type = style.substring(style.indexOf("-") + 1, style.length());

            statement.execute("insert into devices(id, type) values('" + id + "','" + type + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addChildDevice(DraggableNode parent, DraggableNode child) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {
            String parentId = parent.getId();
            String childId = child.getId();

            statement.execute(
                    "insert into connectedDevices(parent, child) values('" + parentId + "','"
                            + childId + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllStructure() {

        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {
            List<CustomDevice> array = new ArrayList<CustomDevice>();

            ResultSet result = statement.executeQuery("SELECT parent, child FROM connectedDevices");
            while (result.next()) {
                CustomDevice device = DeviceMapper.build(result);
                array.add(device);
            }

            Gson gson = new Gson();
            String re = gson.toJson(array);
            System.out.println("RESULT: " + re);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
