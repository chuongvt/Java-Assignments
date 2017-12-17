/*
#Chuong Viet Truong
#CruzID: Chvtruon
#PA 1
*/

import java.io.*;
import java.util.*;

public class Lex{
    public static void main(String[] args) throws IOException{
        // Check if command line args is 2. If not, quit and print error
        if(args.length == 2){
        	Scanner in = new Scanner(new File(args[0]));
        	PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        	ArrayList<String> inList = new ArrayList<String>();
        	int numOfLines = 0;
        	List list = new List();
            list.append(0);

            // Count # of lines & add to ArrayList
            while (in.hasNextLine()){
                numOfLines++;
                inList.add(in.nextLine());
            }

            // Sort integer List
            for(int i=1; i<numOfLines; i++){
                list.moveBack();
                String s = inList.get(i);
                for(int j = i - 1; j>=0 && s.compareTo(inList.get(list.get()))<0; j--){
                    list.movePrev();
                }
                if(list.index()>=0){
                    list.insertAfter(i);
                }
                else{
                    list.prepend(i);
                }
            }

            // Print array in alphabetical order to File
            list.moveFront();
            while(list.index() >= 0){
                out.println(inList.get(list.get()));
                list.moveNext();
            }
            in.close();
            out.close();
        }
        else{
            System.err.println("Usage: FileIO infile outfile");
            System.exit(1);
        }
    }
}
