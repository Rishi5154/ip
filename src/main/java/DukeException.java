public class DukeException extends Exception {
    public DukeException(String message){
        super(message);
    }
    @Override
    public String toString() {
        return "---------------------------------------------------"
                + "\nFAILURE: " + super.getMessage();
    }
}