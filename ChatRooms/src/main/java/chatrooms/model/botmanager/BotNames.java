package chatrooms.model.botmanager;

import chatrooms.model.Feed;

import java.util.stream.IntStream;

/**
 * Enum of bot names
 */
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

    /**
     * @return collection of all bot names in the enum
     */
    public static Feed<String> getAllBotNames() {
        Feed<String> names = new Feed<>();
        IntStream.range(0, values().length).forEach(i -> names.add(values()[i].toString()));
        return names;
    }
}
