package mvp.view.messages;



public class Messages implements InterfaceForMessages  {
     private String message;


    public Messages() {
        this.message ="";
    }

    @Override
    public void showMessage(String message) {
        this.message=message;
        toString();
    }






    @Override
    public String toString() {
        System.out.println("сообщение:"+this.message);
        return null;
    }
}
