package nguyenhoanganhkhoa.com.models;

public class Devices {
    int thumbDevice;
    String nameDevice;
    String brandDevice;
    String statusDevice;
    boolean currentlyUsedDevice;

    public int getThumbDevice() {
        return thumbDevice;
    }

    public void setThumbDevice(int thumbDevice) {
        this.thumbDevice = thumbDevice;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public String getBrandDevice() {
        return brandDevice;
    }

    public void setBrandDevice(String brandDevice) {
        this.brandDevice = brandDevice;
    }


    public String getStatusDevice() {
        return statusDevice;
    }

    public void setStatusDevice(String statusDevice) {
        this.statusDevice = statusDevice;
    }


    public boolean isCurrentlyUsedDevice() {
        return currentlyUsedDevice;
    }

    public void setCurrentlyUsedDevice(boolean currentlyUsedDevice) {
        this.currentlyUsedDevice = currentlyUsedDevice;
    }

    public Devices(int thumbDevice, String nameDevice, String brandDevice, String statusDevice, boolean currentlyUsedDevice) {
        this.thumbDevice = thumbDevice;
        this.nameDevice = nameDevice;
        this.brandDevice = brandDevice;
        this.statusDevice = statusDevice;
        this.currentlyUsedDevice = currentlyUsedDevice;
    }
}
