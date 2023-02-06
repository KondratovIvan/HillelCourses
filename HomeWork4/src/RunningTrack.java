public class RunningTrack extends Block{
    public int length=1000;
    public void overcome(String movement){
        if (super.movement.equals("run")){
            System.out.println("Бігова доріжка подолана біжучи");
        }
        else {
            System.out.println("Бігова доріжка подолана стрибаючи");
        }

    }
}
