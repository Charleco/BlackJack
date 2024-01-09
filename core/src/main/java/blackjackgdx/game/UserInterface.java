package blackjackgdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class UserInterface {
    private final FitViewport viewport;
    private final OrthographicCamera cam;
    private final BlackJack game;
    private Stage stage;
    private TextButton hit;
    private TextButton stay;
    private TypingLabel label;
    public UserInterface(BlackJack game)
    {
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280, 920, cam);
        viewport.apply();
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        cam.update();
        this.game = game;
        VisUI.load();
    }
    public void create()
    {
        Skin skin = VisUI.getSkin();
        stage = new Stage(viewport, game.getBoardRend().getBatch());
        Table root = new Table();
        root.setFillParent(true);
        Table table = new Table();
        table.setFillParent(false);
        hit = new TextButton("Hit",skin);
        table.add(hit).growX().space(10).padLeft(5).bottom().center().row();
        stay = new TextButton("Stay",skin);
        table.add(stay).growX().space(10).padLeft(5).row();
        label = new TypingLabel("{EASE}Welcome to BlackJack!{ENDEASE}",skin);
        table.add(label).height(30).row();
        root.add(table).growX().expandY().bottom().left();
        stage.addActor(root);
    }
    public void render()
    {
        if(hit.isPressed()&&game.getDealer().getPlayerTurn())
        {
            game.getDealer().hit();
            game.getDealer().setPlayerTurn(false);
        }
        else if(stay.isPressed()&&game.getDealer().getPlayerTurn())
        {
            game.getDealer().setPlayerStay(true);
            game.getUserInterface().getLabel().setText("Player stays");
        }
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
    public void resize(int width, int height)
    {
        viewport.update(width, height,true);
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
    }
    public void dispose()
    {
        stage.dispose();
    }
    public OrthographicCamera getCam()
    {
        return cam;
    }
    public Stage getStage()
    {
        return stage;
    }
    public TypingLabel getLabel()
    {
        return label;
    }
}
