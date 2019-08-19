import java.util.Random;


public class Deck extends Hand {

    public Deck(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 13; j++){
                addCard(j,i);
            }
        }
    }

    public Hand draw(int num_cards){
        Hand hand = new Hand();
        for (int i = 0; i < num_cards; i++){
            if (size <= 0){
                System.out.println("Not enough cards in deck");
                return hand;
            }
            Random rand = new Random();
            int n = rand.nextInt(size);
            for (int j = 0; j < 4; j++){
                for (int k = 0; k < 13; k++){
                    if (n == 0){
                        hand.addCard(k,j);
                        j = k = 13;
                    }else if (contains(k,j)){
                        n--;
                    }
                }
            }
        }
        return hand;
    }
}
