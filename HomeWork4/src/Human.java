public class Human extends Participant{
    public int canRun=800;
    public double canJump=1.3;
    public void run(String blockType){
        if (super.blockType.equals("wall")){
            System.out.println("Людиною біжучи подолана стіна");
        }
        else {
            System.out.println("Людиною біжучи подолана бігова доріжка");
        }

    }
    public void jump(String blockType){
        if (super.blockType.equals("wall")){
            System.out.println("Людиною стрибаючи подолана стіна");
        }
        else {
            System.out.println("Людиною стрибаючи подолана бігова доріжка");
        }

    }
}
