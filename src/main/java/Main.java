import Bot.READFILE;
import Bot.bot;

//Main class Is only used for testing purposes
public class Main {
    public static void main(String[] args) {
        READFILE rf = READFILE.getInstance();
        bot robot = bot.getInstance();
        robot.Reply(args[1]);

    }
}