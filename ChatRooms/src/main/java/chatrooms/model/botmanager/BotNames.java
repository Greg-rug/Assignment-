package chatrooms.model.botmanager;

import java.util.Random;

public enum BotNames {

    Albert,
    Allen,
    Bert,
    Bob,
    Cecil,
    Clarence,
    Elliot,
    Elmer,
    Ernie,
    Eugene,
    Fergus,
    Ferris,
    Frank,
    Frasier,
    Fred,
    George,
    Graham,
    Harvey,
    Irwin,
    Larry,
    Lester,
    Marvin,
    Neil,
    Niles,
    Oliver,
    Opie,
    Ryan,
    Toby,
    Ulric,
    Ulysses,
    Uri,
    Waldo,
    Wally,
    Walt,
    Wesley,
    Yanni,
    Yogi,
    Yuri,
    Alfred,
    Bill,
    Brandon,
    Calvin,
    Dean,
    Dustin,
    Ethan,
    Harold,
    Henry,
    Irving,
    Jason,
    Jenssen,
    Josh,
    Martin,
    Nick,
    Norm,
    Orin,
    Pat,
    Perry,
    Ron,
    Shawn,
    Tim,
    Will,
    Wyatt;

    private static final BotNames[] VALUES = values();
    private static final Random random = new Random();

    public static String getRandomName()  {
        return VALUES[random.nextInt(VALUES.length)].toString();
    }
}
