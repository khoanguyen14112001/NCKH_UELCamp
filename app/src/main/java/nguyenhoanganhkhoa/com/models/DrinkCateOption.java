package nguyenhoanganhkhoa.com.models;

import java.util.List;

public class DrinkCateOption {
    private String optionBigCate;
    private List<DrinkOption> listOption;

    public String getOptionBigCate() {
        return optionBigCate;
    }

    public void setOptionBigCate(String optionBigCate) {
        this.optionBigCate = optionBigCate;
    }

    public List<DrinkOption> getListOption() {
        return listOption;
    }

    public void setListOption(List<DrinkOption> listOption) {
        this.listOption = listOption;
    }

    public DrinkCateOption(String optionBigCate, List<DrinkOption> listOption) {
        this.optionBigCate = optionBigCate;
        this.listOption = listOption;
    }
}
