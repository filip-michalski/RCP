import java.util.Objects;
import java.util.Vector;

public class Gamer implements IAdvancedGamer {

    //=======================POLA==========================//
    private Vector<Integer> lastFakes = new Vector<>();
    private Vector<Integer> lastMoves = new Vector<>();
    private boolean lastScore;
    private boolean firstTactic = true;
    private double numberOfWins = 0;
    private double numberOfDefeats = 0;
    private int numberOfRounds = 0;
    private int rockCount, paperCount, scissorsCount = 0;
    private static int schemeUpdate = 0;
    private boolean schemat = false;
    private static int[] tab = new int[5];
    //========================================================//



    //======================NAME==============================//
    @Override
    public String name() {
        return "Kalafior";
    }
    //========================================================//




    //========================FAKE============================//
    @Override
    public int fake() {
        //Strategia 1: kopiujaca markowania przeciwnika
        if (lastFakes.size() >= 1)
            switch (lastFakes.get(lastFakes.size() - 1)) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
            }

        //Markowanie pierwszorundowe
        return 3;
    }
    //========================================================//




    //=======================PLAY=============================//
    @Override
    public int play(int fake) {
        lastFakes.add(fake);

        //Sprawdzenie czy wygamana jest zmiana
        changeTactic();

        if (firstTactic) {
            //Taktyka 1 (zalozenie prawda/falsz)
//            System.out.println("taktyka 1");
            {
                //Podtaktyka 1: Jesli przeciwnik przegral, moze grac defensywnie
                if (lastScore) {
                    switch (fake) {
                        case 1:
                            //Jesli najczesciej gra papierem
                            if (paperCount > rockCount && paperCount > scissorsCount) {
                                return 3;
                            }
                            //Jesli najczesciej gra nozycami
                            if (scissorsCount > rockCount && scissorsCount > paperCount) {
                                return 1;
                            }
                        case 2:
                            //Jesli najczesciej gra kamieniem
                            if (rockCount > paperCount && rockCount > scissorsCount) {
                                return 2;
                            }
                            //Jesli najczesciej gra nozycami
                            if (scissorsCount > rockCount && scissorsCount > paperCount) {
                                return 1;
                            }
                        case 3:
                            //Jesli najczesciej gra kamieniem
                            if (rockCount > paperCount && rockCount > scissorsCount) {
                                return 3;
                            }
                            //Jesli najczesciej gra papierem
                            if (paperCount > rockCount && paperCount > scissorsCount) {
                                return 3;
                            }
                    }
                }

                //Podtaktyka 2: Jesli wygral, moze grac ofensywnie, stosujac strategie prawdy
                else {
                    switch (fake) {
                        case 1:
                            return 2;
                        case 2:
                            return 3;
                        case 3:
                            return 1;
                    }
                }
            }

        } else {

            //Taktyka 2: schematy
            {
                //Podtaktyka 2.1: Trzy ostatnie ruchy takie same
                {
                    if (Objects.equals(lastMoves.get(lastMoves.size() - 1), lastMoves.get(lastMoves.size() - 2)) && Objects.equals(lastMoves.get(lastMoves.size() - 2), lastMoves.get(lastMoves.size() - 3))) {
//                        System.out.println("taktyka 2.1");
                        switch (lastMoves.lastElement()) {
                            case 1:
                                return 2;
                            case 2:
                                return 3;
                            case 3:
                                return 1;
                        }
                    }
                }

                //Podtaktyka 2.2: jesli robi rotacje
                {
                    if (lastMoves.get(lastMoves.size() - 1) == 2 && lastMoves.get(lastMoves.size() - 2) == 1 && lastMoves.get(lastMoves.size() - 3) == 3) {
//                        System.out.println("podtaktyka 2.2");
                        return 1;
                    }
                    if (lastMoves.get(lastMoves.size() - 1) == 1 && lastMoves.get(lastMoves.size() - 2) == 3 && lastMoves.get(lastMoves.size() - 3) == 2) {
//                        System.out.println("podtaktyka 2.2");
                        return 3;
                    }
                    if (lastMoves.get(lastMoves.size() - 1) == 3 && lastMoves.get(lastMoves.size() - 2) == 2 && lastMoves.get(lastMoves.size() - 3) == 1) {
//                        System.out.println("podtaktyka 2.2");
                        return 2;
                    }
                }

                //Podtaktyka 2.3: jesli robi odwrotna rotacje
                {
                    if (lastMoves.get(lastMoves.size() - 1) == 2 && lastMoves.get(lastMoves.size() - 2) == 3 && lastMoves.get(lastMoves.size() - 3) == 1) {
//                        System.out.println("podtaktyka 2.3");
                        return 2;
                    }
                    if (lastMoves.get(lastMoves.size() - 1) == 1 && lastMoves.get(lastMoves.size() - 2) == 2 && lastMoves.get(lastMoves.size() - 3) == 3) {
//                        System.out.println("podtaktyka 2.3");
                        return 1;
                    }
                    if (lastMoves.get(lastMoves.size() - 1) == 3 && lastMoves.get(lastMoves.size() - 2) == 1 && lastMoves.get(lastMoves.size() - 3) == 2) {
//                        System.out.println("podtaktyka 2.3");
                        return 3;
                    }
                }

                //Podtaktyka 2.0 - szukanie 5 takich samych zagran
                {
                    //Jesli schemat zawiodl, resetujemy schemat
                    if (schemat && !lastScore) {
                        schemat = false;
                        schemeUpdate = 0;
                    }

                    //Pola
                    int a = lastMoves.lastElement();
                    int b = lastMoves.get(lastMoves.size() - 2);
                    int c = lastMoves.get(lastMoves.size() - 3);
                    int d = lastMoves.get(lastMoves.size() - 4);
                    int e = lastMoves.get(lastMoves.size() - 5);

                    //szukanie 5-wyrazowego schematu
                    for (int i = 4; i < lastMoves.size(); i++) {
                        if (lastMoves.get(i) == a)
                            if (lastMoves.get(i - 1) == b)
                                if (lastMoves.get(i - 2) == c)
                                    if (lastMoves.get(i - 3) == d)
                                        if (lastMoves.get(i - 4) == e)
                                            if (!schemat)
                                                schemat = true;
                                            else
                                                break;
                    }

                    //Zapisywanie zmiennych do stalej tablicy
                    if (schemat && schemeUpdate == 0) {
//                        System.out.println("taktyka 2.0a");
                        tab[0] = e;
                        tab[1] = d;
                        tab[2] = c;
                        tab[3] = b;
                        tab[4] = a;
                    }

                    //Strzelanie w schemat
                    if (schemat && schemeUpdate == 0) {
//                        System.out.println("taktyka 2.0b");
                        schemeUpdate++;
                        switch (tab[0]) {
                            case 1:
                                return 2;
                            case 2:
                                return 3;
                            case 3:
                                return 1;
                        }
                    }

                    if (schemat && schemeUpdate == 1) {
//                        System.out.println("taktyka 2.0c");
                        schemeUpdate++;
                        switch (d) {
                            case 1:
                                return 2;
                            case 2:
                                return 3;
                            case 3:
                                return 1;
                        }
                    }

                    if (schemat && schemeUpdate == 2) {
//                        System.out.println("taktyka 2.0d");
                        schemeUpdate++;
                        switch (c) {
                            case 1:
                                return 2;
                            case 2:
                                return 3;
                            case 3:
                                return 1;
                        }
                    }

                    if (schemat && schemeUpdate == 3) {
//                        System.out.println("taktyka 2.0e");
                        schemeUpdate++;
                        switch (b) {
                            case 1:
                                return 2;
                            case 2:
                                return 3;
                            case 3:
                                return 1;
                        }
                    }

                    if (schemat && schemeUpdate == 4) {
//                        System.out.println("taktyka 2.0f");
                        //Jesli schemat zostal wykonany pomyslnie, zerujemy schemat
                        schemeUpdate = 0;
                        schemat = false;

                        switch (a) {
                            case 1:
                                return 2;
                            case 2:
                                return 3;
                            case 3:
                                return 1;
                        }
                    }
                }
            }
        }


        //Zagranie awaryjne/pierwszorundowe
//        System.out.println("awaryjne zagranie");
        return 3;
    }

    private void changeTactic() {

        //Co 75 rund sprawdzam czy taktyka sie sprawdza, jesli nie, to zmieniam
        if (numberOfRounds % 75 == 0) {
//            System.out.println("Liczba wygranych:" + numberOfWins);
//            System.out.println("Liczba przegranych:" + numberOfDefeats);

            if (numberOfWins < numberOfDefeats / 2) {
                firstTactic = !firstTactic;
            }

        }
    }
    //========================================================//




    //=====================RESULT=============================//
    @Override
    public void result(boolean score, int enemyMove) {
        lastScore = score;
        lastMoves.add(enemyMove);
        numberOfRounds++;

        //Liczymy ile razy co wyrzucil
        switch (enemyMove) {
            case 1:
                rockCount++;
            case 2:
                paperCount++;
            case 3:
                scissorsCount++;
        }

        //Liczenie naszych wygranych i przegranych
        if (score)
            numberOfWins++;
        else
            numberOfDefeats++;
    }
    //========================================================//

}
