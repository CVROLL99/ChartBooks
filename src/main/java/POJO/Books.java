package POJO;

/**
 * Created by Misha on 01.06.2017.
 */
public class Books{
    private String name;
    private Integer number;

    public Books (String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public Books(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
