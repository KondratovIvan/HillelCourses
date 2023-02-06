import phoneBook.Notation;
import phoneBook.PhoneRing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<String> animanKingdoms= Arrays.asList("animal","bird","fish","insect","bird","bird","animal","fish","insect","insect","animal","animal");
    static String word="insect";
    static Integer [] array= {1,2,3,4,5,6};
    static List<Integer> ints= Arrays.asList(1,2,3,2,2,3,4,6,1,0,1,5,5,0,6);
    static List<Integer> intsReborn=new ArrayList<>(ints);
    static List<String> animalsFirst= Arrays.asList("bird","fox","cat","cat","cat","bird","fox","fox","cat","bird","bird","cat");
    static List<String> animalsSecond= Arrays.asList("bird","fox","cat","cat","cat","bird","fox","fox","cat","bird","bird","cat");
    public void findOccurance(List<String> animalsSecond){
        int birdCount=0;
        int foxCount=0;
        int catCount=0;
        String firstAnimal="bird";
        String secondAnimal="fox";
        String thirdAnimal="cat";
        ArrayList<String> animalsSecondOutput = new ArrayList<>();
        for (int i=0;i<animalsSecond.size();i++){
            if (animalsSecond.get(i).equals("bird")){
                birdCount++;
            }
            else if (animalsSecond.get(i).equals("fox")){
                foxCount++;
            }
            else if (animalsSecond.get(i).equals("cat")){
                catCount++;
            }
        }
        animalsSecondOutput.add("name:"+firstAnimal+", occurence:"+birdCount);
        animalsSecondOutput.add("name:"+secondAnimal+", occurence:"+foxCount);
        animalsSecondOutput.add("name:"+thirdAnimal+", occurence:"+catCount);

        System.out.println(animalsSecondOutput);
    }
    public void  calcOccurance(List<String> animalsFirst){
        int birdCount=0;
        int foxCount=0;
        int catCount=0;
        for (int i=0;i<animalsFirst.size();i++){
            if (animalsFirst.get(i).equals("bird")){
                birdCount++;
            }
            else if (animalsFirst.get(i).equals("fox")){
                foxCount++;
            }
            else if (animalsFirst.get(i).equals("cat")){
                catCount++;
            }
        }
        System.out.println("birds:"+birdCount);
        System.out.println("foxes:"+foxCount);
        System.out.println("cats:"+catCount);
    }
    public void findUnique(List<Integer> intsReborn){
        Collections.sort(intsReborn);
        for (int i = 0; i < intsReborn.size()-1; i++) {
            if (intsReborn.get(i) == intsReborn.get(i+1)) {
                for (int j = 0; j < intsReborn.size()-1; j++){
                     if(intsReborn.get(j)==intsReborn.get(i)){
                         intsReborn.remove(j);
                     }
                }
            }
        }
        System.out.println(intsReborn);
    }
    public static <Integer> List<Integer> convertToList(Integer[] arr)
    {
        List<Integer> list = new ArrayList<>();

        for (Integer i: arr) {
            list.add(i);
        }
        return list;
    }
    public void toList(Integer [] array){
        List<Integer> numbers=convertToList(array);
        System.out.println(numbers);
    }
    public void countOccurance(List<String> animanKingdoms,String word){
        int counter=0;
        for(int i=0;i<animanKingdoms.size();i++){
            if(animanKingdoms.get(i).equals(word)){
                counter++;
            }
        }
        System.out.println("Рядок "+word+" зустрічається в списку "+counter+" разів");
    }

    public static void main(String[] args) {
        Main m=new Main();
        m.countOccurance(animanKingdoms,word);
        m.toList(array);
        m.findUnique(intsReborn);
        m.calcOccurance(animalsFirst);
        m.findOccurance(animalsSecond);

    }
}
