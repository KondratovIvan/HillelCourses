public class Cat extends Participant{
    public int canRun=1200;
    public double canJump=1.8;
    public void run(String blockType){
        if (super.blockType.equals("wall")){
            System.out.println("Котом біжучи подолана стіна");
        }
        else {
            System.out.println("Котом біжучи подолана бігова доріжка");
        }

    }
    public void jump(String blockType){
        if (super.blockType.equals("wall")){
            System.out.println("Котом стрибаючи подолана стіна");
        }
        else {
            System.out.println("Котом стрибаючи подолана бігова доріжка");
        }

    }
}
