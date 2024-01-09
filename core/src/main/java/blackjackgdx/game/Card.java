package blackjackgdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Card {
    private final int value;
    private boolean faceUp;
    private final Sprite sprite;
    public Card(int value, boolean faceUp, Sprite sprite)
    {
        this.value = value;
        this.faceUp = faceUp;
        this.sprite = sprite;
    }
    public int getValue()
    {
        return value;
    }
    public boolean getFaceUp()
    {
        return faceUp;
    }
    public Sprite getSprite()
    {
        return sprite;
    }
    public void setFaceUp(boolean face)
    {
        faceUp=face;
    }
}
