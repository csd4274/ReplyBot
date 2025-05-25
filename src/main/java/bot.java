import java.util.HashMap;
import java.util.Random;
//Singleton Class
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
        Random r= new Random();
        question = question.toLowerCase();
        for (String phrase : Questions.keySet()) {
            if (question.contains(phrase.toLowerCase())) {
                int replyNum = Questions.get(phrase);
                return Answers.get(replyNum);
            }
        }


        int random = r.nextInt(7);
        switch (random) {
            case 0: return "I am not sure I understand...";
            case 1: return "You need to be more specific.";
            case 2: return "I don't know.";
            case 3: return "Can't answer that.";
            case 4: return "Come again?";
            case 5: return "I am so confused.";
            default: return "Ask me something else.";
        }

    }

}