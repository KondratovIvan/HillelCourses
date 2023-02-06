public class Main {
    public void getSquareSum(){
        double squareSum = Circle.square+Triangle.square+Square.square;

        System.out.println("Сумарна площа всіх фігур="+squareSum);
    }
    public void allVariants(Participant[] participants,Block[] blocks){
        RunningTrack runningTrack=new RunningTrack();
        Wall wall=new Wall();
        Human human=new Human();
        Cat cat=new Cat();
        Robot robot=new Robot();

        for (int i=0;i<participants.length;i++){
            for(int j=0;j<blocks.length;j++){
               if(i==0 && j==0){
                   if (runningTrack.length<human.canRun) {
                       System.out.println("Учасник людина пройшла бігову доріжку на дистанції " + runningTrack.length + "м");
                   }
                   else {
                       System.out.println("Учасник людина не пройшла бігову доріжку на дистанції " + runningTrack.length + "м");
                   }
               }
               if(i==1 && j==0){
                   if (runningTrack.length<cat.canRun) {
                       System.out.println("Учасник кіт пройшов бігову доріжку на дистанції " + runningTrack.length + "м");
                   }
                   else {
                       System.out.println("Учасник кіт не пройшов бігову доріжку на дистанції " + runningTrack.length + "м");
                   }
               }
               if(i==2 && j==0){
                   if (runningTrack.length<robot.canRun) {
                       System.out.println("Учасник робот пройшов бігову доріжку на дистанції " + runningTrack.length + "м");
                   }
                   else {
                       System.out.println("Учасник робот не пройшов бігову доріжку на дистанції " + runningTrack.length + "м");
                   }
               }
               if(i==0 && j==1){
                   if (wall.height<human.canJump) {
                       System.out.println("Учасник людина пройшла стіну на дистанції " + wall.height + "м");
                   }
                   else {
                       System.out.println("Учасник людина не пройшла стіну на дистанції " + wall.height + "м");
                   }
               }
               if(i==1 && j==1){
                   if (wall.height<cat.canJump) {
                       System.out.println("Учасник кіт пройшов стіну на дистанції " + wall.height + "м");
                   }
                   else {
                       System.out.println("Учасник кіт не пройшов стіну на дистанції " + wall.height + "м");
                   }
               }
               if(i==2 && j==1){
                   if (wall.height<robot.canJump) {
                       System.out.println("Учасник робот пройшов стіну на дистанції " + wall.height + "м");
                   }
                   else {
                       System.out.println("Учасник робот не пройшов стіну на дистанції " + wall.height + "м");
                   }
               }
            }
        }
    }
    public static void main(String[] args) {
        Main m=new Main();
        Circle circle=new Circle();
        Triangle triangle=new Triangle();
        Square square=new Square();

        circle.getSquare();
        triangle.getSquare();
        square.getSquare();

        Figure[] figures=new Figure[]{circle,triangle,square};

        m.getSquareSum();

        Human human=new Human();
        Cat cat=new Cat();
        Robot robot=new Robot();

        Participant[] participants=new Participant[]{human,cat, robot};

        RunningTrack runningTrack=new RunningTrack();
        Wall wall=new Wall();

        Block[] blocks=new Block[]{runningTrack,wall};

        m.allVariants(participants,blocks);
    }
}
