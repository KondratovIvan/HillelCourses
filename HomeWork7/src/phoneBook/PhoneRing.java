package phoneBook;

import java.util.ArrayList;

public class PhoneRing {
    static ArrayList<Notation> notes = new ArrayList<>();
    public void findAll(){
        for (Notation note: notes){
            if(note.name.equals("Vasiliy Ivanov")){
                System.out.println(note.name+" "+note.phonenumber);
            }
        }
    }
    public void find(){
        for (Notation note: notes){
                if(note.name.equals("Vasiliy Ivanov")){
                    System.out.println(note.name+" "+note.phonenumber);
                    break;
                }
            }
        }
    public void add(){
        Notation note1=new Notation("Vasiliy Ivanov","+380672999103");
        Notation note2=new Notation("Petr Smirnov","+380993545796");
        Notation note3=new Notation("Kolya Petrov","+380737531264");
        Notation note4=new Notation("Mikhailo Sobolev","+380952532228");
        Notation note5=new Notation("Vasiliy Ivanov","+380955552130");
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);
        notes.add(note5);

        for (Notation note: notes) {
            System.out.println("Найменування: "+note.name+", номер телефону:"+note.phonenumber);
        }
    }
    public static void main(String[] args) {
        PhoneRing pr=new PhoneRing();
        pr.add();
        pr.find();
        pr.findAll();
    }
}
