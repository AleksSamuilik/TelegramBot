package by.sam.botontravelsolutions;

public interface CommandProvider {

    String getNameCommand();

    String executeCommand() ;

    void setAttribute(String value);

    String getDescriptionCommand();

}
