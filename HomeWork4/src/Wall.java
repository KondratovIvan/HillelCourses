public class Wall extends Block{
    public double height=1.2;
    public void overcome(String movement){
        if (super.movement.equals("run")){
            System.out.println("Стіна подолана біжучи");
        }
        else {
            System.out.println("Стіна подолана стрибаючи");
        }

    }
}
