import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReferenceFiles{
    public static <E> void inputFile(File file, E input){
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(input + "\n");
            writer.close();
        }catch(IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    public static ArrayList<String> outputFile(File file, int[] numRef){
        ArrayList<String> toRead = new ArrayList<>();
        try{
            Scanner reader = new Scanner(file);
            while(file.exists() && reader.hasNext()){
                for(int i=0; i<toRead.size(); i++){
                    toRead.add(reader.nextLine());
                    System.out.println(reader.nextLine());
                }
            }
            reader.close();
        }catch(IOException e){
            System.out.println("Error");
        }
        return sortArrayList(toRead,numRef);
    }

    private static ArrayList<String> sortArrayList(ArrayList<String> toSort, int[] refNums){
        ArrayList<String> sorted = new ArrayList<>();
        for(int k= toSort.size()-1; k>=0; k++){
            char firstLetter = toSort.get(k).charAt(0);
            if(k != refNums[k]){
                String temp = toSort.get(k);
                toSort.set(k, toSort.get(k+1));
                toSort.set(k+1, temp);
            }
        }
        return sorted;
    }
}
