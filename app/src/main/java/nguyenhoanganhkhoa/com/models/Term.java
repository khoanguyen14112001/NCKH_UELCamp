package nguyenhoanganhkhoa.com.models;

import java.util.List;

public class Term {
    private String term;
    private List<KidTerm> mListKidTerm;
    private boolean expandable;


    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<KidTerm> getmListKidTerm() {
        return mListKidTerm;
    }

    public void setmListKidTerm(List<KidTerm> mListKidTerm) {
        this.mListKidTerm = mListKidTerm;
    }

    public Term(String term, List<KidTerm> mListKidTerm) {
        this.term = term;
        this.mListKidTerm = mListKidTerm;
        this.expandable = false;
    }
}
