package sample.spl1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OperationsBangla {
    public ArrayList<String> baseList = new ArrayList<String>();      // Emotion Word List
    public ArrayList<String> wordList = new ArrayList<String>();         //Finding emotion from word
    public ArrayList<String> nl = new ArrayList<String>();
    public String[] words = new String[10000]; //to store the base words
    public String[][] word = new String[20000][5]; //to store the base words
    public String[] base = new String[10000]; //to store the base words
    public ArrayList<String> aList = new ArrayList<>(85000);//to store all the words

    int i = 0;

    public OperationsBangla() {

    }

    public void splitInputBangla() {

        // File file = new File("src/spl1/BengaliLemmatizer.txt");
        File file = new File("src/sample/spl1/BengaliLemmatizer.txt");
        FileWriter lemmaBan = null;
        try {
            lemmaBan = new FileWriter("lemma.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;


        Scanner sc;

        {
            try {
                String[] words = new String[10000]; //to store the base words

                int i = 0, m = 0, n = 0;
                sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    words[i] = sc.nextLine();  // file theke kortese
//                             "করবেন": "কর",
//                            "করেছেন": "কর",
//                            "করেছিস": "কর",

                    String[] firstSplit = words[i].split("\"", 0);
                    // System.out.println(firstSplit[1]+" "+firstSplit[3]);
                    //    lemmaBan.write(firstSplit[1] + " -> " + firstSplit[3] + "\n");
                    try {
                        wordList.add(firstSplit[1]);
                        baseList.add(firstSplit[3]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Vai ki somossha tr??");
                    }

                    //          System.out.println(word[i][0]+" -> "+word[i][1]);
                    i++;
                }
                sc.close();
//                for(int k=0;k<wordList.size();k++){
//                    System.out.println(wordList.get(k) + "////////" + baseList.get(k));
//                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    //to search
    public String searchBan(String str)  {

        String str2=str;


        int c=0;
        int j = 0;
        for (; j < wordList.size(); j++) {
            if (wordList.get(j).equals(str)) {
                    System.out.print("Word Paisi eta lemma theke"+baseList.get(j) + " ");

                return baseList.get(j);

            }
            else
            c++;
        }
        if(c==j) {
          //  System.out.println("Lemma theke pai nai "+str2);
            return str2;
        }
else
return str2;
    }
}

