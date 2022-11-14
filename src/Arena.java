
public class Arena {
    private Gamer gracz1;
    private TestGamer gracz2;
    private int scoreGracz1 = 0;
    private int scoreGracz2 = 0;
    private int scoreRemis = 0;

    Arena(Gamer gracz1, TestGamer gracz2) {
        this.gracz1 = gracz1;
        this.gracz2 = gracz2;
    }

    public void fight(int liczbaRund) {

        for (int i = 0; i < liczbaRund; i++) {
            //1
            int fakeGracz1 = gracz1.fake();
            int fakeGracz2 = gracz2.fake();

            //2
            int zagranieGracz1 = gracz1.play(fakeGracz2);
            int zagranieGracz2 = gracz2.play(fakeGracz1);

            //3
            int wygrana = whoWon(zagranieGracz1, zagranieGracz2);

            switch (wygrana) {
                case 0 -> {
                    gracz1.result(false, zagranieGracz2);
                    gracz2.result(false, zagranieGracz1);
                    scoreRemis++;
                }
                case 1 -> {
                    gracz1.result(true, zagranieGracz2);
                    gracz2.result(false, zagranieGracz1);
                    scoreGracz1++;
                }
                case 2 -> {
                    gracz1.result(false, zagranieGracz2);
                    gracz2.result(true, zagranieGracz1);
                    scoreGracz2++;
                }
            }
        }


        System.out.println("************************************************");
        System.out.println(gracz1.name() + " wygral: " + scoreGracz1 + " rund");
        System.out.println(gracz2.name() + " wygral: " + scoreGracz2 + " rund");
        System.out.println("Remis wystapil: " + scoreRemis + " razy");
        System.out.print("Ostatecznie wygrywa: ");
        if (scoreGracz1 > scoreGracz2)
            System.out.println(gracz1.name());
        else if (scoreGracz2 > scoreGracz1)
                System.out.println(gracz2.name());
            else
                System.out.println("Remis");
        System.out.println("************************************************");
    }

    private int whoWon(int zagranieGracz1, int zagranieGracz2) {
        if (zagranieGracz2 == zagranieGracz1)
            return 0;
        if (zagranieGracz1 == 1 && zagranieGracz2 == 3 || zagranieGracz1 == 2 && zagranieGracz2 == 1 || zagranieGracz1 == 3 && zagranieGracz2 == 2)
            return 1;
        return 2;
    }


}

