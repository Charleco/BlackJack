package blackjackgdx.game;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Collections;

public class Dealer {
    private final ArrayList<Card> deck;
    private final ArrayList<Card> playerHand;
    private final ArrayList<Card> dealerHand;
    private boolean playerTurn=false;
    private boolean newGame=true;
    private boolean playerStay=false;
    private boolean dealerStay=false;
    private boolean gameOver=false;
    private float sum=1;
    private final BlackJack game;

    public Dealer(BlackJack game)
    {
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        this.game = game;
    }
    public void loop()
    {
        sum+= Gdx.graphics.getDeltaTime();
        if(gameOver)
            return;
        if (sum>1)
        {
            sum = 0;
            if(newGame)
            {
                deal();
                newGame = false;
            }
            if(HandScore(playerHand)>21|| HandScore(dealerHand)>21)
            {
                gameOver = true;
                return;
            }
            if(playerTurn&&!playerStay)
            {
                return;
            }
            if(!dealerStay)
            {
                dealerTurn();
                return;
            }
            if(playerStay)
            {
                gameOver = true;
            }
        }
    }
    public void deal()
    {
        Collections.shuffle(deck);
        CardToPlayer(0);
        deck.get(0).setFaceUp(false);
        CardToDealer(0);
        CardToPlayer(0);
        CardToDealer(0);
        playerTurn = true;
    }
    public void CardToPlayer(int index)
    {
        playerHand.add(deck.get(index));
        deck.remove(index);
    }
    public void CardToDealer(int index)
    {
        dealerHand.add(deck.get(index));
        deck.remove(index);
    }
    public int HandScore(ArrayList<Card> hand)
    {
        int score = 0;
        for(Card card: hand) {
            score+=card.getValue();
        }
        return score;
    }
    public void hit()
    {
        CardToPlayer(0);
        game.getUserInterface().getLabel().setText("Player Hits");
    }
    public void dealerTurn()
    {
        if(HandScore(dealerHand)<17)
        {
            CardToDealer(0);
            game.getUserInterface().getLabel().setText("Dealer Hits");
        }
        else
        {
            dealerStay = true;
            game.getUserInterface().getLabel().setText("Dealer Stays");
        }
        playerTurn = true;
    }
    public void reset()
    {
        if (gameOver) {
            if(!dealerHand.get(0).getFaceUp())
            {
                dealerHand.get(0).setFaceUp(true);
                game.getBoardRend().render();
                return;
            }
            if(HandScore(playerHand)>21)
            {
                game.getUserInterface().getLabel().setText("{COLOR=RED}{SHAKE}Player Bust{ENDSHAKE} {CLEARCOLOR}"+HandScore(playerHand)+" vs "+HandScore(dealerHand));
            }
            else if(HandScore(dealerHand)>21)
            {
                game.getUserInterface().getLabel().setText("{COLOR=Yellow}{SHAKE}Dealer Bust{ENDSHAKE} {CLEARCOLOR}"+HandScore(playerHand)+" vs "+HandScore(dealerHand));
            }
            else if(HandScore(playerHand)>HandScore(dealerHand))
            {
                game.getUserInterface().getLabel().setText("{COLOR=Yellow}{SHAKE}Player Wins{ENDSHAKE} {CLEARCOLOR}"+HandScore(playerHand)+" vs "+HandScore(dealerHand));
            }
            else if(HandScore(playerHand)<HandScore(dealerHand))
            {
                game.getUserInterface().getLabel().setText("{COLOR=RED}{SHAKE}Dealer Wins{ENDSHAKE} {CLEARCOLOR}"+HandScore(playerHand)+" vs "+HandScore(dealerHand));
            }
            else if (HandScore(playerHand)==HandScore(dealerHand))
            {
                game.getUserInterface().getLabel().setText("Tie, {COLOR=RED}{SHAKE}Dealer Wins{ENDSHAKE} {CLEARCOLOR}");
            }
            deck.addAll(playerHand);
            deck.addAll(dealerHand);
            playerHand.clear();
            dealerHand.clear();
            playerTurn = false;
            newGame = true;
            playerStay = false;
            dealerStay = false;
            gameOver = false;
            sum = 1;
        }
    }
    public ArrayList<Card> getDeck() {
        return deck;
    }
    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }
    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }
    public boolean getPlayerTurn() {
        return playerTurn;
    }
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
    public void setPlayerStay(boolean playerStay) {
        this.playerStay = playerStay;
    }
}
