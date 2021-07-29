package cybersoft.java12.model;

public class Member {
    private String name;
    private String roleDescription;
    private String joinDate;
    private int userID;
    private int projectID;

    public Member(){

    }

    public Member(String name, String roleDescription, String joinDate, int userID, int projectID) {
        this.name = name;
        this.roleDescription = roleDescription;
        this.joinDate = joinDate;
        this.userID = userID;
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
