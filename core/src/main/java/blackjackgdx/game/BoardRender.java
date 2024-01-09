package blackjackgdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BoardRender {
    private final SpriteBatch batch;
    private final BlackJack game;
    private final Sprite faceDown;
    private final Texture Clubs;
    private final Texture Diamonds;
    private final Texture Hearts;
    private final Texture Spades;
    public BoardRender(BlackJack game)
    {
        this.game = game;
        batch = new SpriteBatch();
        faceDown = new Sprite(new Texture("card_back.png"));
        batch.setProjectionMatrix(game.getUserInterface().getCam().combined);
        Clubs = new Texture("Clubs-88x124.png");
        Diamonds = new Texture("Diamonds-88x124.png");
        Hearts = new Texture("Hearts-88x124.png");
        Spades = new Texture("Spades-88x124.png");
        MakeCards(game.getDealer());
    }
    public void render()
    {
        Gdx.gl.glClearColor(64 / 255f, 128 / 255f, 128 / 255f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        int i = 0;
        int center = (Gdx.graphics.getWidth()/2)-100;
        int height = (Gdx.graphics.getHeight()/2)-100;
        for(Card card: game.getDealer().getPlayerHand())
        {
            card.getSprite().setPosition(center+i*100, height);
            card.getSprite().draw(batch);
            i++;
        }
        i=0;
        for(Card card: game.getDealer().getDealerHand())
        {
            if(card.getFaceUp())
            {
                card.getSprite().setPosition(center+i*100, height+200);
                card.getSprite().draw(batch);
            }
            else
            {
                faceDown.setPosition(center+i*100, height+200);
                faceDown.draw(batch);
            }
            i++;
        }
        batch.end();
    }
    public void MakeCards(Dealer dealer)
    {

        Texture[] textures = {Clubs, Diamonds, Hearts, Spades};
        for(Texture text: textures)
        {
            TextureRegion[][] split = TextureRegion.split(text,text.getWidth()/5, text.getHeight()/3);
            int value = 1;
            for(int i = 0;i<3; i++)
            {
                for(int j = 0; j<5; j++)
                {
                    if(i==2 && (j==4||j==3))
                    {
                        continue;
                    }
                    else if(i==2)
                    {
                        dealer.getDeck().add(new Card(10, true, new Sprite(split[i][j])));
                        continue;
                    }
                    dealer.getDeck().add(new Card(value, true, new Sprite(split[i][j])));
                    value++;
                }
            }
        }
    }
    public void dispose()
    {
        batch.dispose();
        Clubs.dispose();
        Diamonds.dispose();
        Hearts.dispose();
        Spades.dispose();
    }
    public Batch getBatch()
    {
        return batch;
    }
}
