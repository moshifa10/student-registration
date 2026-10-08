package za.co.wethinkcode.examples.toyrobot;

public class Student {

    private String name;
    private String email;
    private int id = -1;

    public Student(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  this.getId() +" "+ this.getName() + " " + this.getEmail();
    }
}
