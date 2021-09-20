import biuoop.KeyboardSensor;
import animations.AnimationRunner;
import gamelevels.DirectHit;
import gamelevels.FinalFour;
import gamegeneral.GameFlow;
import gamelevels.Green3;
import gamegeneral.LevelInformation;
import gamelevels.WideEasy;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Tomer Pardilov
 * this class creates a game, initializes it and runs it with the levels
 * that the user decided to play in.
 */
public class Ass6Game {
    /**
     * main method.
     *
     * @param args levels argument. if its empty we are going to run all levels in order.
     */
    public static void main(String[] args) {
        LevelInformation level1 = new DirectHit();
        LevelInformation level2 = new WideEasy();
        LevelInformation level3 = new Green3();
        LevelInformation level4 = new FinalFour();
        AnimationRunner runner = new AnimationRunner();
        KeyboardSensor ks = runner.getGuiKeyboard();
        GameFlow game = new GameFlow(runner, ks);
        List<LevelInformation> levels = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "1":
                    levels.add(level1);
                    break;
                case "2":
                    levels.add(level2);
                    break;
                case "3":
                    levels.add(level3);
                    break;
                case "4":
                    levels.add(level4);
                    break;
                default:
                    break;
            }
        }
        if (levels.isEmpty()) {
            levels.add(level1);
            levels.add(level2);
            levels.add(level3);
            levels.add(level4);
        }
        game.runLevels(levels);
    }
}
