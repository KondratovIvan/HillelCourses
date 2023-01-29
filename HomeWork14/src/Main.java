import java.util.Scanner;

public class Main {
    static String line="Hello Hilliel";
    static char symbol='l';
    static String source="Ukraine";
    static String target="krai";
    static String line2="Hello";
    static String line3="ollo";

    public void guessWord(String[] words){
        int keyIndex= (int) (Math.random() * 24);
        String keyWord=words[keyIndex];


        Scanner sc=new Scanner(System.in);

        while (sc.hasNext()){
            if(sc.nextLine().equals(keyWord)){
                System.out.println("Вы угадали слово!");
                break;
            }
            else {
                char[]wordLetters1=keyWord.toCharArray();
                char[]wordLetters2=sc.nextLine().toCharArray();
                char[]wordLetters3=new char[wordLetters1.length];
                System.arraycopy(wordLetters2,0,wordLetters3,0,wordLetters2.length);
                char[]guessLine={'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'};
                for (int i=0;i<wordLetters1.length;i++){
                   if (wordLetters1[i]==wordLetters3[i]){
                       guessLine[i]=wordLetters1[i];
                   }
                }
                System.out.println("Вы пока не угадали слово");
                System.out.println(guessLine);
            }
        }
    }

    public boolean isPalindrome(String line3){
        StringBuilder sb=new StringBuilder(line3);
        if (line3.equals(sb.reverse().toString())){
            return true;
        }
        else {
            return false;
        }
    }
    public StringBuilder stringReverse(String line2){
        StringBuilder sb=new StringBuilder(line2);
        return sb.reverse();
    }
    public int findWordPosition(String source,String target){
        if(source.contains(target)){
            return source.indexOf(target.charAt(0));
        }
        else {
            return -1;
        }
    }
    public int findSymbolOccurance(String line,char symbol){
        int counter=0;
        char[] chars=line.toCharArray();
        for (int i=0;i< chars.length;i++){
            if (chars[i]==symbol){
                counter++;
            }
        }
        return counter;
    }
    public static void main(String[] args) {
        Main m=new Main();

        System.out.println(m.findSymbolOccurance(line,symbol));
        System.out.println(m.findWordPosition(source,target));
        System.out.println(m.stringReverse(line2));
        System.out.println(m.isPalindrome(line3));

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado" , "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", " pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        m.guessWord(words);
    }
}
