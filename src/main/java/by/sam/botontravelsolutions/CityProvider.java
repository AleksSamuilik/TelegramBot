package by.sam.botontravelsolutions;

public class CityProvider {
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public CityProvider(String name){
        this.name=name;
    }
}