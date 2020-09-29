package chatrooms.model.botmanager.bots;

import chatrooms.model.botmanager.Bot;

public class NormalBot extends Bot {

    public NormalBot(String name, boolean isLocalBot) {
        super(name, isLocalBot);
    }

    @Override
    public String nextString() {
        return ("I am totally normal - " + name);
    }

    /*
    private final static int nrWords = 5;
    private final static int lenghtSentence = 10;
    private final static String SPACE = " ";
    private final static String PERIOD = ".";

    static Random r = new Random();
    public String RandomSentence(){
	String Lines = messageFeed.getPreviousMessages(rand());
	String[] Words = Lines.split(" ", Lines.length());	
        String sentence;
	sentence = Words[rand()];
        char c = sentence.charAt(0);
        sentence = sentence.replace( c, Character.toUpperCase(c) );
    
	for (int i = 0; i < lenghtSentence; i++){
        sentence += SPACE + Words[rand()] ;
	}

        sentence += PERIOD;
        return sentence ;
	sentence = "";

    }
    static int rand(){
        int ri = r.nextInt() % nrWords;
        if ( ri < 0 )
            ri += nrWords;
        return ri;
    }
    @Override 
     public void run() {
        try {
            out.println("I am - " + name + "I shall talk your ear off with random nonsense . Enter BYE to exit");
	    String reply = RandomSentence ;
            String line;
            while ((line = in.readLine()) != null && !line.trim().equalsIgnoreCase("BYE")) {
			 messageFeed.setMessage(line);               
			 send(reply);
            }
        } 
     }

     */
}
