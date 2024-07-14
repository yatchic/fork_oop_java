package mvp.view.errors;

public class Errors implements InterfaceForErrors {
      private String errors;

    public Errors() {
        this.errors ="";
    }

    @Override
    public void showError(String error) {

        this.errors=error;
        toString();
    }

    @Override
    public String toString() {
        System.out.println(errors);
        return null;
    }
}
