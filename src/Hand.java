
public class Hand {
    public static final int DIAMONDS = 0;
    public static final int CLUBS = 1;
    public static final int HEARTS = 2;
    public static final int SPADES = 3;

    public static final int TWO=0;
    public static final int THREE=1;
    public static final int FOUR=2;
    public static final int FIVE=3;
    public static final int SIX=4;
    public static final int SEVEN=5;
    public static final int EIGHT=6;
    public static final int NINE=7;
    public static final int TEN=8;
    public static final int JACK=9;
    public static final int QUEEN=10;
    public static final int KING=11;
    public static final int ACE=12;

    private boolean[][] hand = new boolean[4][13];

    public int size = 0;

    public void addCard(int rank, int suit){
        if (!hand[suit][rank]){
            hand[suit][rank] = true;
            size++;
        }
    }

    public boolean contains(int rank, int suit){
        return hand[suit][rank];
    }

    public void removeCard(int rank, int suit){
        if (hand[suit][rank]){
            hand[suit][rank] = false;
            size--;
        }
    }

    //returns 1 if this hand beats the given hand
    //returns 0 if this hand ties the given hand
    //returns -1 if this hand loses to the given hand
    public int beats(Hand hand){
        //straight flushes
        int straight_flush1 = this.straight_flush();
        int straight_flush2 = hand.straight_flush();
        if (straight_flush1 > straight_flush2){
            return 1;
        }else if (straight_flush1 < straight_flush2){
            return -1;
        }else if (straight_flush1 != -1){
            return 0;
        }

        //four of a kind
        int four_1 = this.four_of_a_kind();
        int four_2 = hand.four_of_a_kind();
        if (four_1 > four_2){
            return 1;
        }else if (four_1 < four_2){
            return -1;
        }else if (four_1 != -1){
            return 0;
        }

        //full house
        int[] full_house1 = this.full_house();
        int[] full_house2 = hand.full_house();
        if (full_house1[0] > 0 && full_house1[1] > 0){
            if (full_house2[0] > 0 && full_house2[1] > 0){
                if (full_house1[0] > full_house2[0]){
                    return 1;
                }else if (full_house1[0] < full_house2[0]){
                    return -1;
                }
                if (full_house1[1] > full_house2[1]){
                    return 1;
                }else if (full_house1[1] < full_house2[1]){
                    return -1;
                }
                return 0;
            }else{
                return 1;
            }
        } else if (full_house2[0] > 0 && full_house2[1] > 0){
            return -1;
        }

        //flush
        int[] flush1 = this.flush();
        int[] flush2 = hand.flush();
        if (flush1[4] > 0){
            if (flush2[4] > 0){
                for (int i = 0; i < 5; i++){
                    if (flush1[i] > flush2[i]){
                        return 1;
                    }else if (flush1[i] < flush2[i]){
                        return -1;
                    }
                }
                return 0;
            }else{
                return 1;
            }
        }else if (flush2[4] > 0){
            return -1;
        }

        //straight
        int straight1 = this.straight();
        int straight2 = hand.straight();
        if (straight1 > straight2){
            return 1;
        }else if (straight1 < straight2){
            return -1;
        }else if (straight1 != straight2){
            return 0;
        }

        //three of a kind
        if (full_house1[0] > 0){
            if (full_house2[0] > 0){
                if (full_house1[0] > full_house2[0]){
                    return 1;
                }else if (full_house1[0] < full_house2[0]){
                    return -1;
                }
                return 0;
            }else{
                return 1;
            }
        } else if (full_house2[0] > 0){
            return -1;
        }

        //two pair
        int[] two_pair1 = this.two_pair();
        int[] two_pair2 = hand.two_pair();
        if (two_pair1[0] > 0 && two_pair1[1] > 0){
            if (two_pair2[0] > 0 && two_pair1[2] > 0){
                if (two_pair1[0] > two_pair2[0]){
                    return 1;
                }else if (two_pair1[0] < two_pair2[0]){
                    return -1;
                }else if (two_pair1[1] > two_pair2[1]){
                    return 1;
                }else if (two_pair1[1] < two_pair2[1]){
                    return -1;
                }else if (two_pair1[2] > two_pair2[2]){
                    return 1;
                }else if (two_pair1[2] < two_pair2[2]){
                    return -1;
                }
                return 0;
            }else{
                return 1;
            }
        }else if (two_pair2[0] > 0 && two_pair1[2] > 0){
            return -1;
        }

        //pair
        if (full_house1[1] > 0){
            if (full_house2[1] > 0){
                if (full_house1[1] > full_house2[1]){
                    return 1;
                }else if (full_house1[1] < full_house2[1]){
                    return -1;
                }
                return 0;
            }else{
                return 1;
            }
        } else if (full_house2[1] > 0){
            return -1;
        }

        //high card
        int high1 = this.high_card();
        int high2 = hand.high_card();
        if (high1 > high2){
            return 1;
        }else if (high1 < high2){
            return -1;
        }
        return 0;
    }

    //returns 2 integers if a straight flush exists, the first of which is the high card, the second the suit.
    //always returns the best straight flush
    //if no straight flush exists, returns -1 and -1
    public int straight_flush() {
        int streak = 0;
        int high = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (streak == 0) {
                    if (hand[i][j]) {
                        streak++;
                    }
                } else {
                    if (hand[i][j]) {
                        streak++;
                    } else {
                        streak = 0;
                    }
                }
                if (streak >= 5) {
                    if (j > high) {
                        high = j;
                    }
                }
            }
        }
        return high;
    }

    //returns the rank of the best four of a kind, if it exists. Otherwise, returns -1.
    public int four_of_a_kind(){
        int result = -1;
        for (int i = 0; i < 13; i++){
            boolean flag = true;
            for (int j = 0; j < 4; j++){
                flag &= hand[j][i];
            }
            if (flag){
                result = i;
            }
        }
        return result;
    }

    //returns the best triple and best double from a hand, assuming they exist
    public int[] full_house(){
        int best_double = -1;
        int best_triple = -1;
        for (int i = 0; i < 13; i++){
            boolean flag = true;
            int amount = 0;
            for (int j = 0; j < 4; j++){
                if (hand[j][i]){
                    amount++;
                }
            }
            if (amount == 3){
                if (i > best_triple){
                    best_triple = i;
                }
            }else if (amount == 2){
                if (i > best_double){
                    best_double = i;
                }
            }
        }
        return new int[]{best_triple,best_double};
    }

    //returns the five ranks in the best flush, assuming a flush exists. Otherwise, it returns
    public int[] flush() {
        int[] best_flush = new int[5];
        for (int i = 0; i < 5; i++){
            best_flush[i] = -1;
        }
        for (int i = 0; i < 4; i++) {
            int count = 0;
            int[] flush = new int[5];
            for (int j = 12; j >= 0; j--) {
                if (hand[i][j]){
                    flush[count] = j;
                    count++;
                }
                if (count == 5){
                    break;
                }
            }
            if (count == 5){
                for (int j = 0; j < 5; j++){
                    if (flush[j] > best_flush[j]){
                        best_flush = flush;
                        break;
                    }else if (flush[j] < best_flush[j]){
                        break;
                    }
                }
            }
        }
        return best_flush;
    }


    //returns the rank of the high card in the best straight, or -1 if no straight exists
    public int straight(){
        int high = -1;
        int streak = 0;
        for (int i = 0; i < 13; i++){
            boolean found = false;
            for (int j = 0; j < 4; j++){
                if (hand[j][i]){
                    streak++;
                    found = true;
                    break;
                }
                if (streak >= 5) {
                    if (j > high) {
                        high = j;
                    }
                }
            }
            if(!found){
                streak = 0;
            }
        }
        return high;
    }

    //returns the best two pair. The three integers returned are the high pair, low pair, and the kicker.
    public int[] two_pair(){
        int high_pair = -1;
        int low_pair = -1;
        int kicker = -1;
        for (int i = 0; i < 13; i++){
            boolean flag = true;
            int amount = 0;
            for (int j = 0; j < 4; j++){
                if (hand[j][i]){
                    amount++;
                }
            }
            if (amount == 2){
                low_pair = high_pair;
                high_pair = i;
            }else if (amount == 1){
                kicker = i;
            }
        }
        return new int[]{high_pair,low_pair,kicker};
    }

    //returns the high card rank
    public int high_card(){
        int result = -1;
        for (int i = 0; i < 13; i++){
            for (int j = 0; j < 4; j++){
               if (hand[j][i]){
                   result = i;
               }
            }
        }
        return result;
    }

    public String printHand(){
        String result = "";
        for (int i = 0; i < 13; i++){
            for (int j = 0; j < 4; j++){
                if (hand[j][i]){
                    switch (i){
                        case Hand.TWO:
                            result += "2";
                            break;
                        case Hand.THREE:
                            result += "3";
                            break;
                        case Hand.FOUR:
                            result += "4";
                            break;
                        case Hand.FIVE:
                            result += "5";
                            break;
                        case Hand.SIX:
                            result += "6";
                            break;
                        case Hand.SEVEN:
                            result += "7";
                            break;
                        case Hand.EIGHT:
                            result += "8";
                            break;
                        case Hand.NINE:
                            result += "9";
                            break;
                        case Hand.TEN:
                            result += "T";
                            break;
                        case Hand.JACK:
                            result += "J";
                            break;
                        case Hand.QUEEN:
                            result += "Q";
                            break;
                        case Hand.KING:
                            result += "K";
                            break;
                        case Hand.ACE:
                            result += "A";
                            break;
                        default:
                            break;
                    }
                    switch (j){
                        case Hand.SPADES:
                            result += "S";
                            break;
                        case Hand.HEARTS:
                            result += "H";
                            break;
                        case Hand.DIAMONDS:
                            result += "D";
                            break;
                        case Hand.CLUBS:
                            result += "C";
                            break;
                    }
                    result += " ";
                }
            }
        }
        return result;
    }

    public int[] numSuits(){
        int[] array = new int[4];
        for (int i = 0; i < 13; i++){
            for (int j = 0; j < 4; j++){
                if (hand[j][i]){
                    array[j]++;
                }
            }
        }
        return array;
    }

}
