
public class Main {

    public static void main(String[] args) {
        final int trials = 10000;

        Hand hand1 = new Hand();
        //hand1.addCard(Hand.DIAMONDS,Hand.ACE);
        //hand1.addCard(Hand.DIAMONDS,Hand.KING);
        //hand1.addCard(Hand.DIAMONDS,Hand.QUEEN);
        //hand1.addCard(Hand.DIAMONDS,Hand.JACK);
        //hand1.addCard(Hand.DIAMONDS,Hand.TEN);
        //hand1.addCard(Hand.DIAMONDS,Hand.TWO);
        //hand1.addCard(Hand.DIAMONDS,Hand.THREE);
        //hand1.addCard(Hand.DIAMONDS,Hand.FOUR);
        //hand1.addCard(Hand.DIAMONDS,Hand.FIVE);
        //hand1.addCard(Hand.CLUBS,Hand.SIX);
        //hand1.addCard(Hand.SIX,Hand.HEARTS);
        //hand1.addCard(Hand.SIX,Hand.CLUBS);
        //hand1.addCard(Hand.SIX,Hand.SPADES);
        //System.out.println(hand1.size);
        //System.out.println(hand1.full_house()[0] + " " + hand1.full_house()[1]);
        int num_pairs = 0;
        int num_triples = 0;
        int num_four = 0;
        int straight_flush = 0;
        int full_house = 0;
        int flush = 0;
        int straight = 0;
        int two_pair = 0;
        int num_hearts = 0;
        int num_spades = 0;
        int num_clubs = 0;
        int num_diamonds = 0;
        for (int i = 0; i < trials; i++){
            Deck deck = new Deck();
            Hand hand = deck.draw(7);
            //System.out.print(hand.printHand());
            if (hand.full_house()[0] > 0){
                num_triples++;
                //System.out.print(" Triple");
            }
            if (hand.full_house()[1] > 0){
                num_pairs++;
                //System.out.print(" Pair");
            }
            if (hand.four_of_a_kind() > 0){
                num_four++;
                //System.out.print(hand.printHand());
                //System.out.println(" Four of a kind");
            }
            if (hand.straight_flush() > 0){
                straight_flush++;
                System.out.print(hand.printHand());
                System.out.println(" Straight flush");
            }
            if (hand.two_pair()[0] > 0 && hand.two_pair()[1] > 0){
                two_pair++;
                //System.out.print(hand.printHand());
                //System.out.println(" Two pair");
            }
            if (hand.straight() > 0){
                straight++;
                //System.out.print(hand.printHand());
                //System.out.println(" Straight ");
            }
            if (hand.flush()[4] > 0){
                flush++;
                //System.out.print(hand.printHand());
                //System.out.println(" Flush");
            }
            if (hand.full_house()[0] > 0 && hand.full_house()[1] > 0){
                full_house++;
                //System.out.print(hand.printHand());
                //System.out.println(" Full house;");
            }
            num_clubs += hand.numSuits()[Hand.CLUBS];
            num_diamonds += hand.numSuits()[Hand.DIAMONDS];
            num_spades += hand.numSuits()[Hand.SPADES];
            num_hearts += hand.numSuits()[Hand.HEARTS];
        }
        System.out.println();
        System.out.println();

        System.out.println("Clubs: " + num_clubs);
        System.out.println("Diamonds: " + num_diamonds);
        System.out.println("Spades: " + num_spades);
        System.out.println("Hearts: " + num_hearts);

        System.out.println();
        System.out.println();

        System.out.println("Pairs: " + num_pairs + " (" + num_pairs*100/(float)trials + "%)");
        System.out.println("Two pair: " + two_pair + " (" + two_pair*100/(float)trials + "%)");
        System.out.println("Triples: " + num_triples + " (" + num_triples*100/(float)trials + "%)");
        System.out.println("Straight: " +straight + " (" + straight*100/(float)trials + "%)");
        System.out.println("Flush: " + flush + " (" + flush*100/(float)trials + "%)");
        System.out.println("Full house: " + full_house + " (" + full_house*100/(float)trials + "%)");
        System.out.println("Four of a kind: " + num_four + " (" + num_four*100/(float)trials + "%)");
        System.out.println("Straight flush: " + straight_flush + " (" + straight_flush*100/(float)trials + "%)");

    }
}
