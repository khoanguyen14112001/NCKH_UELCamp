package nguyenhoanganhkhoa.com.models;

public class QuestionsCategories {
    String nameQuestion_Categories;
    int thumbQuestion_Categories;
    int articlesCategories;

    public QuestionsCategories(String nameQuestion_Categories, int thumbQuestion_Categories) {
        this.nameQuestion_Categories = nameQuestion_Categories;
        this.thumbQuestion_Categories = thumbQuestion_Categories;
    }

    public String getNameQuestion_Categories() {
        return nameQuestion_Categories;
    }

    public void setNameQuestion_Categories(String nameQuestion_Categories) {
        this.nameQuestion_Categories = nameQuestion_Categories;
    }

    public int getThumbQuestion_Categories() {
        return thumbQuestion_Categories;
    }

    public void setThumbQuestion_Categories(int thumbQuestion_Categories) {
        this.thumbQuestion_Categories = thumbQuestion_Categories;
    }

    public int getArticlesCategories() {
        return articlesCategories;
    }

    public void setArticlesCategories(int articlesCategories) {
        this.articlesCategories = articlesCategories;
    }

    public QuestionsCategories(String nameQuestion_Categories, int thumbQuestion_Categories, int articlesCategories) {
        this.nameQuestion_Categories = nameQuestion_Categories;
        this.thumbQuestion_Categories = thumbQuestion_Categories;
        this.articlesCategories = articlesCategories;
    }
}
