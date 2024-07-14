package mvp.presenter.read;

import java.util.Scanner;

public class Read implements InterfaceForRead{
private final Scanner scanner;

    public Read() {
        this.scanner =new Scanner(System.in);
    }


    @Override
    public String read() {
        return scanner.nextLine();
    }
    @Override
    public String readWord() {
        return scanner.next();
    }






}
