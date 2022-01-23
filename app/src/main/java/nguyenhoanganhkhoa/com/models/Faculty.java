package nguyenhoanganhkhoa.com.models;

public class Faculty {
    public String getNameFaculty() {
        return nameFaculty;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }

    public Faculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }

    private String nameFaculty;
}
