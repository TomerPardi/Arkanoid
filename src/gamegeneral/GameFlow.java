package gamegeneral;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import java.util.List;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is in charge of adding support for moving from one level to the next.
 */
public class GameFlow {
    //fields
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;

    /**
     * Constructor.
     * @param ar stands for the AnimationRunner parameter.
     * @param ks stands for the keyboard sensor parameter.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = new Counter();
    }

    /**
     * run methos - initializes and runs each level that in the array by its order.
     * @param levels array of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        int listLen = levels.size();
        int i = 0;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, animationRunner, score);
            level.initialize();

            while (level.haveBalls() && level.haveBlocks()) {
                level.run();
            }

            if (!level.haveBalls()) {
                this.animationRunner.run(new KeyPressStoppableAnimation(animationRunner.getGuiKeyboard(),
                        KeyboardSensor.SPACE_KEY, new EndScreen(animationRunner.getGuiKeyboard(),
                        score.getValue(), false)));
                animationRunner.closeGui();
            }
            if (i + 1 == listLen && !level.haveBlocks()) {
                this.animationRunner.run(new KeyPressStoppableAnimation(animationRunner.getGuiKeyboard(),
                        KeyboardSensor.SPACE_KEY, new EndScreen(animationRunner.getGuiKeyboard(),
                        score.getValue(), true)));
                animationRunner.closeGui();
            }
            i++;
        }
    }
}