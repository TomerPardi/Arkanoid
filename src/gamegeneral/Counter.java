package gamegeneral;
// ID: 316163922
/**
 * @author Tomer Pardilov
 * this class is used for counting things.
 */
public class Counter {
    private int counter;
    /**
     * constructor.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * increase method -- add number to current count.
     * @param number stands for the parameter we want to add to counter.
     */
    public void increase(int number) {
        this.counter = counter + number;
    }
    /**
     * decrease method -- subtract number from current count.
     * @param number stands for the parameter we want to subtract from counter.
     */
    public void decrease(int number) {
        this.counter = counter - number;
    }
    /**
     * getValue method -- get current count.
     * @return counter;
     */
    public int getValue() {
        return counter;
    }
}
