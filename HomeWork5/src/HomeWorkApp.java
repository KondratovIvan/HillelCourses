public class HomeWorkApp {
    public boolean isYearLeap(int year){

        if (year%4==0&&(year%100!=0||year%400==0)){
            return true;
        }
        else {
            return false;
        }
    }
    public void stringOutput(String line,int linesAmount){
        for(int i=1;i<=linesAmount;i++){
            System.out.println(line);
        }
    }
    public boolean nemuberPositivityChecker(int fourthCheck){
        if (fourthCheck>=0){
            return true;
        }
        else {
            return false;
        }
    }
    public void numberSignChecker(int thirdCheck){
        if (thirdCheck>=0){
            System.out.println("Число дадатнє");
        }
        else {
            System.out.println("Число від'ємне");
        }
    }
    public boolean numberSumChecker(int firstCheck,int secondCheck){
        if(firstCheck+secondCheck>=10&&firstCheck+secondCheck<=20){
            return true;
        }
        else {
            return false;
        }
    }
    public void compareNumbers(){
        int a=1;
        int b=7;
        if (a>=b){
            System.out.println("a>=b");
        }
        else {
            System.out.println("a<b");
        }
    }
    public void printColor(){
        int value=55;
        if(value<=0){
            System.out.println("Червний");
        }
        else if(value>0&&value<=100){
            System.out.println("Жовтий");
        }
        else {
            System.out.println("Зелений");
        }
    }
    public void checkSumSign(){
        int a=2;
        int b=5;

        if(a+b>=0){
            System.out.println("Сума позитивна");
        }
        else {
            System.out.println("Сума негативна");
        }
    }
    public void printThreeWords(){
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void main(String[] args) {
        HomeWorkApp hw=new HomeWorkApp();
        hw.printThreeWords();
        hw.checkSumSign();
        hw.printColor();
        hw.compareNumbers();
        boolean firstOut=hw.numberSumChecker(7, 12);
        System.out.println(firstOut);
        hw.numberSignChecker(1);
        boolean secondout=hw.nemuberPositivityChecker(-3);
        System.out.println(secondout);
        hw.stringOutput("Hello Hillel",3);
        boolean thirdOut=hw.isYearLeap(500);
        System.out.println(thirdOut);
    }
}
