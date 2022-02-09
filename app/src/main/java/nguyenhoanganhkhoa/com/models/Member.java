package nguyenhoanganhkhoa.com.models;

public class Member {


    String memberName;
    boolean isMaleMember;
    String memberCareer;
    String memberDateOfBirth;
    boolean isRoleMember = true;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public boolean isMaleMember() {
        return isMaleMember;
    }

    public void setMaleMember(boolean maleMember) {
        isMaleMember = maleMember;
    }

    public String getMemberCareer() {
        return memberCareer;
    }

    public void setMemberCareer(String memberCareer) {
        this.memberCareer = memberCareer;
    }

    public String getMemberDateOfBirth() {
        return memberDateOfBirth;
    }

    public void setMemberDateOfBirth(String memberDateOfBirth) {
        this.memberDateOfBirth = memberDateOfBirth;
    }

    public boolean isRoleMember() {
        return isRoleMember;
    }

    public void setRoleMember(boolean roleMember) {
        isRoleMember = roleMember;
    }

    public Member(String memberName, boolean isMaleMember, String memberCareer, String memberDateOfBirth) {
        this.memberName = memberName;
        this.isMaleMember = isMaleMember;
        this.memberCareer = memberCareer;
        this.memberDateOfBirth = memberDateOfBirth;
    }

    public Member(String memberName, boolean isMaleMember, String memberCareer, String memberDateOfBirth, boolean isRoleMember) {
        this.memberName = memberName;
        this.isMaleMember = isMaleMember;
        this.memberCareer = memberCareer;
        this.memberDateOfBirth = memberDateOfBirth;
        this.isRoleMember = isRoleMember;
    }
}
