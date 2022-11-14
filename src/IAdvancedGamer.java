public interface IAdvancedGamer {

    int kamien = 1;
    int papier = 2;
    int nozyce = 3;

    String name();

    int fake();

    int play(int fake);

    void result(boolean score, int enemyMove);


}
