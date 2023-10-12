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
    public static <E> ArrayList<E> outputFile(File file){
        ArrayList<E> toRead = new ArrayList<>();
        try{
            Scanner reader = new Scanner(file);
            while(file.exists() && reader.hasNext()){
                E toAdd = (E)reader.next();
                for(int i=0; i<toRead.size(); i++){
                    toRead.add(toAdd);
                }
            }
            reader.close();
        }catch(IOException e){
            System.out.println("Error");
        }
        return toRead;
    }
}
