
public class TestGamer implements IAdvancedGamer {

    private String name;
    private boolean score;
    private int enemyMove;

    public TestGamer(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int fake() {
        return (int) (1 + (Math.random() * 3));
    }

    @Override
    public int play(int fake) {
        return (int) (1 + (Math.random() * 3));
    }

    @Override
    public void result(boolean score, int enemyMove) {
        this.score = score;
        this.enemyMove = enemyMove;

    }
}
