
public class Main {
    public static void main(String[] args) {

        TestGamer g2 = new TestGamer("Testowy");
        Gamer filip = new Gamer();
        Arena arena = new Arena(filip, g2);

        arena.fight(4000);


    }
}
