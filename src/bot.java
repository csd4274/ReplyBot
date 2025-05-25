import java.util.HashMap;

public class bot {
    private static final bot instance = new bot();
    private HashMap<Integer, String> Answers = new HashMap<>();
    private HashMap<String, Integer> Questions = new HashMap<>();

    private bot (){
        Answers = READFILE.getInstance().getAnswers();
        Questions = READFILE.getInstance().getQuestions();
    }

    public static bot getInstance(){
        return instance;
    }

    public String Reply(String question){
        question = question.toLowerCase();
        for (String phrase : Questions.keySet()) {
            if (question.contains(phrase.toLowerCase())) {
                int replyNum = Questions.get(phrase);
                return Answers.get(replyNum);
            }
        }


        return "I am not sure I understand...";
    }

}