package application.device;

import application.DragIconType;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceMapper {

    public static CustomDevice build(ResultSet rs) {
        CustomDevice device = new Modem();

        try {
            String id = rs.getString("parent");
            String childId = rs.getString("child");

            device.setId(id);
            //device.setType(type);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return device;
    }
}
