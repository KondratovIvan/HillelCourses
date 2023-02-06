import java.util.*;

public class FileNavigator {
    Map<String, ArrayList<FileData>> navigation=new HashMap<>();

    static List<FileData> firstFileList= Arrays.asList(new FileData("FirstApp.java",20,"/path/to/FirstGroup.java"),new FileData("SecondApp.java",15,"/path/to/FirstGroup.java"),new FileData("ThirdApp.java",10,"/path/to/FirstGroup.java"));
    static List<FileData> secondFileList= Arrays.asList(new FileData("FourthApp.java",25,"/path/to/SecondGroup.java"),new FileData("FifthApp.java",30,"/path/to/SecondGroup.java"),new FileData("SixthApp.java",35,"/path/to/SecondGroup.java"));
    static List<FileData> thirdFileList= Arrays.asList(new FileData("SeventhApp.java",40,"/path/to/ThirdGroup.java"),new FileData("EighthApp.java",45,"/path/to/ThirdGroup.java"),new FileData("NinethApp.java",50,"/path/to/ThirdGroup.java"));

    static ArrayList<FileData> firstFileListRemake=new ArrayList<>(firstFileList);
    static ArrayList<FileData> secondFileListRemake=new ArrayList<>(secondFileList);
    static ArrayList<FileData> thirdFileListRemake=new ArrayList<>(thirdFileList);

    static String firstPath="/path/to/FirstGroup.java";
    static String secondPath="/path/to/SecondGroup.java";
    static String thirdPath="/path/to/ThirdGroup.java";
    static String mapPath="/path/to/FirstAddGroup.java";
    static String pathToSearch="/path/to/ThirdGroup.java";
    static int sizeLimit=15;
    static String pathToDelete="/path/to/SecondGroup.java";
    static ArrayList<FileData> fileListRemake=new ArrayList<>();
    static ArrayList<FileData> foundList=new ArrayList<>();

    public void sortBySize(ArrayList<FileData> firstFileListRemake){
        firstFileListRemake.sort(new Comparator<FileData>() {
            @Override
            public int compare(FileData o1, FileData o2) {
                if (o1.getSize() == o2.getSize()) return 0;
                else if (o1.getSize()> o2.getSize()) return 1;
                else return -1;
            }
        });
        for (FileData sizeSortedOutput : firstFileListRemake){
            System.out.println("Ім'я відсортованого файлу: "+sizeSortedOutput.getName()+" розмір відсортованого файлу:"+sizeSortedOutput.getSize()+" шлях до відсортованого файлу:"+sizeSortedOutput.getPath());
        }
    }
    public void remove(String pathToDelete){
        if(navigation.containsKey(pathToDelete)){
            navigation.remove(pathToDelete);
            System.out.println("Всі файли зі шляхом "+pathToDelete+" видалено");
        }
        else {
            System.out.println("Нема файлів з вказаним шляхом для видалення");
        }
        System.out.println("================================================================================");
    }
    public void filterBySize(int sizeLimit,ArrayList<FileData> firstFileListRemake){
        for (FileData sizeFilter : firstFileListRemake) {
            if(sizeFilter.getSize()<=sizeLimit){
                System.out.println("Ім'я відфільтрованого за величиною файлу: "+sizeFilter.getName()+" розмір відфільтрованого за величиною файлу:"+sizeFilter.getSize()+" шлях до відфільтрованого за величиною файлу:"+sizeFilter.getPath());
            }
        }
        System.out.println("================================================================================");
    }

    public void find(String pathToSearch,ArrayList<FileData> foundList){
        if(navigation.containsKey(pathToSearch)) {
            foundList = navigation.get(pathToSearch);
            for (FileData foundOutput : foundList) {
                System.out.println("Ім'я знайденого файлу: " + foundOutput.getName() + " розмір знайденого файлу:" + foundOutput.getSize() + " шлях до знайденого файлу:" + foundOutput.getPath());
            }
            System.out.println("================================================================================");
        }
        else {
            System.out.println("За заданим шляхом нічого не знайдено");
            System.out.println("================================================================================");
        }
    }

    public void add(String mapPath,ArrayList<FileData> fileListRemake) throws Exception{
        if (!navigation.containsKey(mapPath)) {
            fileListRemake.add(new FileData("FirstAddApp.java",55,"/path/to/FirstAddGroup.java"));
            fileListRemake.add(new FileData("SecondAddApp.java",60,"/path/to/FirstAddGroup.java"));
            fileListRemake.add(new FileData("ThirdAddApp.java",65,"/path/to/FirstAddGroup.java"));
            navigation.put(mapPath, fileListRemake);
            for (FileData addPOutput: fileListRemake) {
                System.out.println("Ім'я файлу: "+addPOutput.getName()+" розмір файлу:"+addPOutput.getSize()+" шлях до файлу:"+addPOutput.getPath());
            }
            System.out.println("================================================================================");
        }
        else {
            ArrayList<FileData> oldFiles=navigation.get(mapPath);
            oldFiles.add(new FileData("NewApp.java",70,mapPath));
            navigation.put(mapPath, oldFiles);
        }
        for(FileData equality: fileListRemake) {
            if (mapPath !=equality.getPath()){
                throw new Exception("Шлях до файлу та шлях-ключ різні!");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FileNavigator fn=new FileNavigator();

        fn.navigation.put(firstPath,firstFileListRemake);
        fn.navigation.put(secondPath,secondFileListRemake);
        fn.navigation.put(thirdPath,thirdFileListRemake);

        fn.add(mapPath, fileListRemake);

        for (FileData firstOutput: firstFileListRemake) {
            System.out.println("Ім'я файлу: "+firstOutput.getName()+" розмір файлу:"+firstOutput.getSize()+" шлях до файлу:"+firstOutput.getPath());
        }
        System.out.println("================================================================================");
        for (FileData secondOutput: secondFileListRemake) {
            System.out.println("Ім'я файлу: "+secondOutput.getName()+" розмір файлу:"+secondOutput.getSize()+" шлях до файлу:"+secondOutput.getPath());
        }
        System.out.println("================================================================================");
        for (FileData thirdOutput: thirdFileListRemake) {
            System.out.println("Ім'я файлу: "+thirdOutput.getName()+" розмір файлу:"+thirdOutput.getSize()+" шлях до файлу:"+thirdOutput.getPath());
        }
        System.out.println("================================================================================");

        fn.find( pathToSearch,foundList);
        fn.filterBySize(sizeLimit, firstFileListRemake);
        fn.remove(pathToDelete);
        fn.sortBySize(firstFileListRemake);
    }
}
