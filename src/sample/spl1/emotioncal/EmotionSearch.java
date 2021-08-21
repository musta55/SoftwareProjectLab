package sample.spl1.emotioncal;

import sample.spl1.Operations;

import java.io.*;
import java.util.ArrayList;

public class EmotionSearch {

    public String[] emotion = new String[85000];
    public String[] word = new String[85000];

    int i = 0;
    int Frequency[] = new int[8];
    public ArrayList<String> emotionList = new ArrayList<String>();      // Emotion Word List
    public ArrayList<String> wordList = new ArrayList<String>();         //Finding emotion from word
    int exClaimCount = 0;
    int intensity;



    public void emotionSearch()
    {

        //get the file
        //  System.out.print("After Lemmatization : ");
        for (i = 0; i < Operations.outList.size(); i++) {
            //  System.out.print(Operations.outList.get(i) + " ");
            if (Operations.outList.get(i).contains("!")) {
                exClaimCount++;
            }
            if (Operations.outList.get(i).contains("?")) {
                exClaimCount++;
            }
        }

        File files = new File("src/sample/spl1/emotion.txt");

        //   FileReader fr = new FileReader(files);
        //   BufferedReader br = new BufferedReader(fr);

        try (BufferedReader br = new BufferedReader(new FileReader(files))) {              //Exception With Resources


            String currentLine = "";


            int p = 0;
            int bool;
            while ((currentLine = br.readLine()) != null) {

                String[] firstSplit = currentLine.split("\t", 0);
                //  System.out.println(firstSplit[0]);

                if (firstSplit[2].matches("1")) {
                    if (!(firstSplit[1].matches("positive"))) {
                        if (!(firstSplit[1].matches("negative"))) {
                            //  System.out.println(firstSplit[0] + "\t" + firstSplit[1] );
                            //     newDb.write(firstSplit[0] + "\t" + firstSplit[1] + "\n");

                            wordList.add(firstSplit[0]);
                            emotionList.add(firstSplit[1]);
                            p++;


                        }
                    }

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Boolean found = false;
        for (int w = 0; w < 8; w++) Frequency[w] = 0;



        for (i = 0; i < Operations.outList.size(); ) {

            found = false;
            NEW:

            if (Operations.outList.get(i).equals("so") || Operations.outList.get(i).equals("more") || Operations.outList.get(i).equals("most") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("just") || Operations.outList.get(i).equals("almost") || Operations.outList.get(i).equals("too") || Operations.outList.get(i).equals("enough") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("terribly") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("wonderfully") || Operations.outList.get(i).equals("insanely")) {

                int q = 0;
                //   System.out.println("intensity");
                while (q < 5) {

                    // i++;
                    System.out.println(Operations.outList.get(i));

                    int j = 0, a = 0;
                    for (; j < wordList.size(); j++) {

                        if (Operations.outList.get(i).equals(wordList.get(j))) {
                            System.out.print("word :" + wordList.get(j) + " ");
                            System.out.print("Emotion :" + emotionList.get(j) + " ");
                            //  fw.write(emotionList.get(j)+" ");

                            //     System.out.println("Intensity");
                            if (emotionList.get(j).equalsIgnoreCase("joy")) {
                                Frequency[0]++;
                                intensity += 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("surprise")) {
                                Frequency[1]++;
                                intensity += 3;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("fear")) {
                                Frequency[2]++;
                                intensity -= 1;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("sadness")) {
                                Frequency[3]++;
                                intensity -= 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("trust")) {
                                Frequency[4]++;
                                intensity += 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("disgust")) {
                                Frequency[5]++;
                                intensity -= 4;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("anticipation")) {
                                Frequency[6]++;
                                intensity += 2;
                                //  System.out.println("INtensity is" + intensity);
                            }
                            if (emotionList.get(j).equalsIgnoreCase("anger")) {
                                Frequency[7]++;
                                intensity -= 3;
                            }


                            found = true;
                            //  break NEW;
                        } else {
                            a++;
                        }
                    }
                    if (a == j) {
                        i++;
                        q++;
                    } else break;
                }

            } else if (Operations.outList.get(i).equals("never") || Operations.outList.get(i).equals("rarely") || Operations.outList.get(i).equals("not") || Operations.outList.get(i).equals("no") || Operations.outList.get(i).equals("rare") || Operations.outList.get(i).equals("scarcely") || Operations.outList.get(i).equals("seldom") || Operations.outList.get(i).equals("few") || Operations.outList.get(i).equals("little") || Operations.outList.get(i).equals("bad") || Operations.outList.get(i).equals("sad") || Operations.outList.get(i).equals("dont") || Operations.outList.get(i).equals("") || Operations.outList.get(i).equals("poor")) {
                //   System.out.println("negative");
                int q = 0;
                while (q < 5) {


                    // i++;
                    //  NEWS:

                    int j = 0, a = 0;
                    for (; j < wordList.size(); j++) {

                        if (Operations.outList.get(i).equals(wordList.get(j))) {

                            System.out.print("Word :" + wordList.get(j));
                            System.out.print("Emotion :" + emotionList.get(j) + " ");
//                            //     System.out.println("negative");
                            if (emotionList.get(j).equalsIgnoreCase("joy")) {
                                Frequency[5]++;
                                intensity -= 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("surprise")) {
                                Frequency[2]++;
                                intensity -= 3;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("fear")) {
                                Frequency[1]++;
                                intensity += 1;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("sadness")) {
                                Frequency[6]++;
                                intensity += 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("trust")) {
                                Frequency[7]++;
                                intensity -= 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("disgust")) {
                                Frequency[0]++;
                                intensity += 4;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("anticipation")) {
                                Frequency[3]++;
                                intensity -= 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("anger")) {
                                Frequency[4]++;
                                intensity += 3;
                            }


                            found = true;
                            //     break NEWS;


                        } else {
                            a++;
                            q++;
                        }
                    }
                    if (a == j) {
                        i++;
                    } else break;
                }
            } else {

//                    if (!(Operations.outList.get(i).equals("never") || Operations.outList.get(i).equals("rarely") || Operations.outList.get(i).equals("not") || Operations.outList.get(i).equals("no") || Operations.outList.get(i).equals("notonly") || Operations.outList.get(i).equals("scarcely") || Operations.outList.get(i).equals("seldom") || Operations.outList.get(i).equals("few") || Operations.outList.get(i).equals("little") || Operations.outList.get(i).equals("bad") || Operations.outList.get(i).equals("sad") || Operations.outList.get(i).equals("dont") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("") || Operations.outList.get(i).equals("poor"))) {
//
//
//                        if (!(
//                                Operations.outList.get(i).equals("so") || Operations.outList.get(i).equals("more") || Operations.outList.get(i).equals("most") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("just") || Operations.outList.get(i).equals("almost") || Operations.outList.get(i).equals("too") || Operations.outList.get(i).equals("enough") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("terribly") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("wonderfully") || Operations.outList.get(i).equals("insanely"))) {
//

                for (int j = 0; j < wordList.size(); j++) {
                    if (Operations.outList.get(i).equals(wordList.get(j))) {
                        //   System.out.println("sadsa");
                        System.out.print("Word :" + wordList.get(j) + " ");
                        System.out.print("Emotion :" + emotionList.get(j) + " ");
                        //    fw.write(emotionList.get(j)+" ");
                        //     System.out.println("normal");
                        if (emotionList.get(j).equalsIgnoreCase("joy")) Frequency[0] += 2;
                        if (emotionList.get(j).equalsIgnoreCase("surprise")) Frequency[1] += 3;
                        if (emotionList.get(j).equalsIgnoreCase("fear")) Frequency[2]++;
                        if (emotionList.get(j).equalsIgnoreCase("sadness")) Frequency[3]++;
                        if (emotionList.get(j).equalsIgnoreCase("trust")) Frequency[4]++;
                        if (emotionList.get(j).equalsIgnoreCase("disgust")) Frequency[5] += 2;
                        if (emotionList.get(j).equalsIgnoreCase("anticipation")) Frequency[6]++;
                        if (emotionList.get(j).equalsIgnoreCase("anger")) Frequency[7]++;


                        found = true;
                        break NEW;
                    }
                }

            }
//                }
//            }

            if (!found) {
                //     System.out.print(outList.get(i) + " ");
                //     System.out.print("Emotion : Neutral ");
            }
            i++;
        }
        //   fw.close();
        Operations.outList.clear();

    }






}
