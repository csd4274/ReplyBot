import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.getNumericValue;

//Signleton Class
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
    //Finds next int in a String, returns null if nothing was found
    public static Integer getNextNumber(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }

        return null;
    }

    public void ReadTheFiles() {
        try {
            // Read ChatBotAnswers.txt from resources folder
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream("ChatBotAnswers.txt"), StandardCharsets.UTF_8))) {
                if (br == null) {
                    throw new IOException("Resource ChatBotAnswers.txt not found");
                }
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.length() <= 0) {
                        continue;
                    }
                    if (line.charAt(0) == '\'') {
                        int i = 1;
                        StringBuilder text = new StringBuilder();
                        while (i < line.length()) {
                            if (line.charAt(i) == '\'') {
                                break;
                            }
                            text.append(line.charAt(i));
                            i++;
                        }
                        if (i >= line.length() || i + 2 >= line.length()) {
                            System.err.println("Bad input file ChatBotAnswers.txt");
                            throw new IOException();
                        }
                        // skip the space
                        i += 2;

                        Answers.put(getNextNumber(line.substring(i)), text.toString());

                    } else {
                        System.err.println("Bad input file ChatBotAnswers.txt");
                        throw new IOException();
                    }
                }
            }

            // Read ChatBotQuestions.txt from resources folder
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream("ChatBotQuestions.txt"), StandardCharsets.UTF_8))) {
                if (br == null) {
                    throw new IOException("Resource ChatBotQuestions.txt not found");
                }
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.length() <= 0) {
                        continue;
                    }
                    if (line.charAt(0) == '\'') {
                        int i = 1;
                        StringBuilder text = new StringBuilder();
                        while (i < line.length()) {
                            if (line.charAt(i) == '\'') {
                                break;
                            }
                            text.append(line.charAt(i));
                            i++;
                        }
                        if (i >= line.length() || i + 1 >= line.length()) {
                            System.err.println("Bad input file ChatBotQuestions.txt");
                            throw new IOException();
                        }
                        // skip the space
                        i += 2;
                        Questions.put(text.toString(), getNextNumber(line.substring(i)));

                    } else {
                        System.err.println("Bad input file ChatBotQuestions.txt");
                        throw new IOException();
                    }
                }
            }
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
