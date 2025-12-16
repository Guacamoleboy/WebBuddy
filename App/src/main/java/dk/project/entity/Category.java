package dk.project.entity;

public class Category {

    // Attributes
    private int id;
    private String name;

    // ____________________________________________________

    public Category(){} // Unit Test

    // ____________________________________________________

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    // ____________________________________________________

    public int getId(){
        return this.id;
    }

    // ____________________________________________________

    public void setId(int id){
        this.id = id;
    }

    // ____________________________________________________

    public String getName(){
        return this.name;
    }

    // ____________________________________________________

    public void setName(String name){
        this.name = name;
    }

}