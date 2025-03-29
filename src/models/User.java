package models;

public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String role;

    public User(int id, String name, String username, String password, String role){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId(){return id;}
    public String getName(){return name;}
    public String getUsername(){return username;}
    public String getpassword(){return password;}
    public String getrole(){return role;}
    
    public void setName(String name){this.name = name;}
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}
    public void setRole(String role){this.role = role;}

    @Override
    public String toString(){
        return "ID: " + id + " | Name: " + name + " | Username: " + username +
        " | password: " + password + " | role: " + role;
    }
}
