package application.device;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "modem")
public class Modem extends CustomDevice {

    @Column(name = "type")
    private String types;

    private List<CustomDevice> customDevices = new ArrayList<>();

    public Modem() {
    }


    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public List<CustomDevice> getCustomDevices() {
        return customDevices;
    }

    public void addDevice(CustomDevice device) {
        customDevices.add(device);
    }

    @Override
    public String toString() {
        return "Modem{" +
                "types='" + types + '\'' +
                ", customDevices=" + customDevices +
                '}';
    }
}
