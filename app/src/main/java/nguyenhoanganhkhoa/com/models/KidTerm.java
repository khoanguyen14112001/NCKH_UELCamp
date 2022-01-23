package nguyenhoanganhkhoa.com.models;

public class KidTerm {
    private String nameKidTerm;
    private String detailKidTerm;




    public String getNameKidTerm() {
        return nameKidTerm;
    }

    public void setNameKidTerm(String nameKidTerm) {
        this.nameKidTerm = nameKidTerm;
    }

    public String getDetailKidTerm() {
        return detailKidTerm;
    }

    public void setDetailKidTerm(String detailKidTerm) {
        this.detailKidTerm = detailKidTerm;
    }

    public KidTerm(String nameKidTerm, String detailKidTerm) {
        this.nameKidTerm = nameKidTerm;
        this.detailKidTerm = detailKidTerm;
    }
}
