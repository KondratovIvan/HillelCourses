public class Robot extends Participant{
    public int canRun=2000;
    public double canJump=0.5;
    public void run(String blockType){
        if (super.blockType.equals("wall")){
            System.out.println("Роботом біжучи подолана стіна");
        }
        else {
            System.out.println("Роботом біжучи подолана бігова доріжка");
        }

    }
    public void jump(String blockType){
        if (super.blockType.equals("wall")){
            System.out.println("Роботом стрибаючи подолана стіна");
        }
        else {
            System.out.println("Роботом стрибаючи подолана бігова доріжка");
        }

    }
}
