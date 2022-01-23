package nguyenhoanganhkhoa.com.models;

public class Member {
    int memberThumb;
    String memberCareer;
    String memberName;
    String memberRole;
    String memberGender;
    String memberDateOfBirth;


    public Member(int memberThumb, String memberCareer, String memberName, String memberRole, String memberGender, String memberDateOfBirth) {
        this.memberThumb = memberThumb;
        this.memberCareer = memberCareer;
        this.memberName = memberName;
        this.memberRole = memberRole;
        this.memberGender = memberGender;
        this.memberDateOfBirth = memberDateOfBirth;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberSex(String memberSex) {
        this.memberGender = memberSex;
    }

    public String getMemberDateOfBirth() {
        return memberDateOfBirth;
    }

    public void setMemberDateOfBirth(String memberDateOfBirth) {
        this.memberDateOfBirth = memberDateOfBirth;
    }

    public int getMemberThumb() {
        return memberThumb;
    }

    public void setMemberThumb(int memberThumb) {
        this.memberThumb = memberThumb;
    }

    public String getMemberCareer() {
        return memberCareer;
    }

    public void setMemberCareer(String memberCareer) {
        this.memberCareer = memberCareer;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }


}
