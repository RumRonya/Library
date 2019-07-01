package by.verdeth.models;

public class User {

    private Integer idUser;
    private String nameUser;
    private String password;
    private Short roleUser;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Short roleUser) {
        this.roleUser = roleUser;
    }

    public User(Integer idUser, String nameUser, String password, Short roleUser) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.password = password;
        this.roleUser = roleUser;
    }


}
