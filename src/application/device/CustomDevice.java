package application.device;

import application.DragIconType;

public abstract class CustomDevice {

    private String name;
    private DragIconType type;
    private String id;

    public CustomDevice() {
    }

    public DragIconType getType() {
        return type;
    }

    public void setType(DragIconType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
