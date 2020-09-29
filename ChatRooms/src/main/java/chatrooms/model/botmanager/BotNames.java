package chatrooms.model.botmanager;

import java.util.ArrayList;
import java.util.stream.IntStream;

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

    public static ArrayList<String> getAllBotNames() {
        ArrayList<String> names = new ArrayList<>();
        IntStream.range(0, values().length).forEach(i -> names.add(values()[i].toString()));
        return names;
    }
}
