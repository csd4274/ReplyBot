import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static java.lang.Character.getNumericValue;

public class READFILE{

    private BufferedReader br;
    private static final READFILE instance = new READFILE();
    private HashMap<Integer, String> Answers = new HashMap<>();
    private HashMap<String, Integer> Questions = new HashMap<>();

    private READFILE(){
        ReadTheFiles();
    }

    public static READFILE getInstance(){
        return instance;
    }

    private void ReadTheFiles(){
        // the logic behind the file is one line = one input and must be similar to:
        // "answer/question" 1
        //where answer/question is the text of the answer or question and 1 is the corresponding answer or question code ie. what question was asked to produce that answer
        try  {
            br = new BufferedReader(new FileReader("ChatBotAnswers.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.length() <= 0 ){
                    continue;
                }
                if(line.charAt(0) == '"'){
                    int i = 1;
                    String text = "";
                    while(i < line.length()){
                        if(line.charAt(i) == '"'){
                            break;
                        }
                        text += line.charAt(i);
                        i++;
                    }
                    if(i >= line.length() || i +1 >= line.length()){
                        System.err.println("Bad input file ChatBotAnswers.txt");
                        throw new IOException();
                    }
                    //skip the space
                    i += 2;
                    Answers.put(getNumericValue(line.charAt(i)),text);


                }
                else{
                    System.err.println("Bad input file ChatBotAnswers.txt");
                    throw new IOException();
                }
            }
            br.close();

            //Now read the questions

            br = new BufferedReader(new FileReader("ChatBotQuestions.txt"));
            while ((line = br.readLine()) != null) {
                if(line.length() <= 0 ){
                    continue;
                }
                if(line.charAt(0) == '"'){
                    int i = 1;
                    String text = "";
                    while(i < line.length()){
                        if(line.charAt(i) == '"'){
                            break;
                        }
                        text += line.charAt(i);
                        i++;
                    }
                    if(i >= line.length() || i +1 >= line.length()){
                        System.err.println("Bad input file ChatBotQuestions.txt");
                        throw new IOException();
                    }
                    //skip the space
                    i += 2;
                    Questions.put(text,getNumericValue(line.charAt(i)));


                }
                else{
                    System.err.println("Bad input file ChatBotAnswers.txt");
                    throw new IOException();
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printAnswers() {
        if (Answers.isEmpty()) {
            System.out.println("The Answers map is empty.");
            return;
        }

        System.out.println("Contents of the Answers map:");
        System.out.println("============================");
        for (Integer key : Answers.keySet()) {
            String value = Answers.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }
        System.out.println("============================");
    }

    public void printQuestions() {
        if (Questions.isEmpty()) {
            System.out.println("The Questions map is empty.");
            return;
        }

        System.out.println("Contents of the Questions map:");
        System.out.println("============================");
        for (String key : Questions.keySet()) {
            Integer value = Questions.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }
        System.out.println("============================");
    }

    public HashMap<Integer, String> getAnswers() {
        return Answers;
    }
    public HashMap<String, Integer> getQuestions() {
        return Questions;
    }

}
