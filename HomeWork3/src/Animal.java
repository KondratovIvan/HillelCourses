abstract public class Animal {
}
class Dog extends Animal{
    private String dogName;
    private int dogRunBarrierLength;
    private int dogSwimBarrierLength;
    public static int count;
    public Dog(String dogName) {
        this.dogName = dogName;
        count++;
    }

    public void run(int dogRunBarrierLength){
        if(dogRunBarrierLength<=500 && dogRunBarrierLength>=0) {
            System.out.println(dogName + " пробіг " + dogRunBarrierLength + "м");
        }
        else {
            System.out.println(dogName+" не може стільки бігти");
        }
    }
    public void swim(int dogSwimBarrierLength){
        if(dogSwimBarrierLength<=10 && dogSwimBarrierLength>=0) {
            System.out.println(dogName + " проплив " + dogSwimBarrierLength + "м");
        }
        else {
            System.out.println(dogName+" не може стільки плисти");
        }
    }
    public void results(){
        System.out.println("Собак створено:"+(count-1));
    }
}

class Cat extends Animal{
    private String catName;
    private int catRunBarrierLength;
    private int catSwimBarrierLength;
    public static int count;

    public Cat(String catName) {
        this.catName = catName;
        count++;
    }

    public void run(int catRunBarrierLength){
        if(catRunBarrierLength<=200 && catRunBarrierLength>=0) {
            System.out.println(catName + " пробіг " + catRunBarrierLength + "м");
        }
        else {
            System.out.println(catName+" не може стільки бігти");
        }
    }
    public void swim(int catSwimBarrierLength){
        if(catSwimBarrierLength==0) {
            System.out.println(catName + " проплив " + catSwimBarrierLength + "м");
        }
        else {
            System.out.println(catName+" не може стільки плисти");
        }
    }
    public void results(){
        System.out.println("Котів створено:"+(count-1));
    }
}
class Main {
    public static void main(String[] args) {
        System.out.println("Собаки:");
        Dog dog1= new Dog("Білка");
        dog1.run(300);
        dog1.swim(5);

        Dog dog2= new Dog("Стрілка");
        dog2.run(600);
        dog2.swim(-1);

        Dog dogResults=new Dog("Екземпляри");
        dogResults.results();

        System.out.println("Коти:");
        Cat cat1= new Cat("Мурзик");
        cat1.run(100);
        cat1.swim(0);

        Cat cat2= new Cat("Рамзез II");
        cat2.run(-5);
        cat2.swim(3);

        Cat catResults=new Cat("Екземпляри");
        catResults.results();

        System.out.println("Створено тварин:"+((Dog.count+Cat.count)-2));
    }
}

