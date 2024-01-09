package blackjackgdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BlackJack extends ApplicationAdapter {
    private BoardRender boardRend;
    private UserInterface ui;
    private Dealer dealer;

    @Override
    public void create() {
        dealer = new Dealer(this);
        ui = new UserInterface(this);
        boardRend = new BoardRender(this);
        ui.create();
    }

    @Override
    public void render() {
        Gdx.input.setInputProcessor(ui.getStage());
        boardRend.render();
        dealer.loop();
        ui.render();
        dealer.reset();
    }

    @Override
    public void resize(int width, int height) {
        ui.resize(width, height);
    }

    @Override
    public void dispose() {
        ui.dispose();
        boardRend.dispose();
    }

    public BoardRender getBoardRend()
    {
        return boardRend;
    }
    public UserInterface getUserInterface()
    {
        return ui;
    }
    public Dealer getDealer()
    {
        return dealer;
    }
}
