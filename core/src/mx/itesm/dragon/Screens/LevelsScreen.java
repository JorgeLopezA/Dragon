package mx.itesm.dragon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.dragon.Levels.LevelOne;
import mx.itesm.dragon.Levels.LevelThree;
import mx.itesm.dragon.Levels.LevelTwo;
import mx.itesm.dragon.Main;
import mx.itesm.dragon.States.GameState;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.BackGround;
import mx.itesm.dragon.Utils.Text;

public class LevelsScreen extends GenericScreen{

    private ScreenState screenState;

    private Texture textureBackground;
    private Texture textureBtnLvl1;
    private Texture textureBtnPressLvl1;
    private Texture textureBtnLvl2;
    private Texture textureBtnPressLvl2;
    private Texture textureBtnLvl3;
    private Texture textureBtnPressLvl3;
    private Texture textureBtnReturn;
    private Texture textureBtnReturnPressed;

    private ImageButton btnLvl1;
    private ImageButton btnLvl2;
    private ImageButton btnLvl3;
    private ImageButton btnReturn;


    private Preferences progress = Gdx.app.getPreferences("preferenceProg");
    private Preferences score1 = Gdx.app.getPreferences("preferenceSc1");
    private Preferences score2 = Gdx.app.getPreferences("preferenceSc2");
    private Preferences score3 = Gdx.app.getPreferences("preferenceSc3");

    private BackGround backGround;

    private Stage stageLevelsSreen;

    public LevelsScreen(Main game, ScreenState screenState) {
        super(game);
        this.screenState = screenState;
    }

    @Override
    public void show() {
        borrarPantalla();
        loadResources();
    }

    private void loadResources() {
        stageLevelsSreen = new Stage(vista);

        textureBackground = assetManager.get("backgrounds/levelSelect.png");
        backGround = new BackGround(textureBackground);

        textureBtnLvl1 = assetManager.get("buttons/botonNivel1.png");
        textureBtnPressLvl1 = assetManager.get("buttons/level1Button.png");
        textureBtnLvl2 = assetManager.get("buttons/botonNivel2.png");
        textureBtnPressLvl2 = assetManager.get("buttons/level2Button.png");
        textureBtnLvl3 = assetManager.get("buttons/botonNivel3.png");
        textureBtnPressLvl3 = assetManager.get("buttons/level3Button.png");
        textureBtnReturn = assetManager.get("buttons/return.png");
        textureBtnReturnPressed = assetManager.get("buttons/returnPressed.png");

        btnLvl1 = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnLvl1)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressLvl1)));

        btnLvl2 = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnLvl2)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressLvl2)));

        btnLvl3 = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnLvl3)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressLvl3)));

        btnReturn = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReturn)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReturnPressed)));

        btnLvl1.setPosition(ANCHO / 2 - btnLvl1.getWidth() / 2,ALTO - 300);
        btnLvl2.setPosition(ANCHO / 2 - btnLvl2.getWidth() / 2,ALTO - 600);
        btnLvl3.setPosition(ANCHO / 2 - btnLvl3.getWidth() / 2,ALTO - 900);
        btnReturn.setPosition(20, ALTO - btnReturn.getHeight() - 20);

        btnLvl1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.LVL_ONE));
            }
        });

        btnLvl2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.LVL_TWO));
            }
        });

        btnLvl3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.LVL_THREE));
            }
        });

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });

        stageLevelsSreen.addActor(btnLvl1);

        int levelProg = progress.getInteger("progress");
        if (levelProg == 2 || levelProg == 3){
            stageLevelsSreen.addActor(btnLvl2);
        }

        if (levelProg == 3){
            stageLevelsSreen.addActor(btnLvl3);
        }
        /*stageLevelsSreen.addActor(btnLvl2);
        stageLevelsSreen.addActor(btnLvl3);*/
        stageLevelsSreen.addActor(btnReturn);

        marcador = new Text("fonts/fuenteMini.fnt");
        letras = "Score";
        scoreLvl1 = new Text("fonts/fuenteMini.fnt");
        scoreLvl2 = new Text("fonts/fuenteMini.fnt");
        scoreLvl3 = new Text("fonts/fuenteMini.fnt");

        Gdx.input.setInputProcessor(stageLevelsSreen);
    }

    protected void back() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new LoadingScreen(game, ScreenState.MENU));
        }
    }

    @Override
    public void render(float delta) {
        back();

        batch.begin();
        backGround.render(batch);
        int sco1 = score1.getInteger("sc1");
        int sco2 = score2.getInteger("sc2");
        int sco3 = score3.getInteger("sc3");
        marcador.mostrarMensaje(batch, letras,btnLvl1.getX() + btnLvl1.getWidth()/3,btnLvl1.getY()-10);
        marcador.mostrarMensaje(batch, letras,btnLvl2.getX() + btnLvl2.getWidth()/3,btnLvl2.getY()-10);
        marcador.mostrarMensaje(batch, letras,btnLvl3.getX() + btnLvl3.getWidth()/3,btnLvl3.getY()-10);
        scoreLvl1.mostrarMensaje(batch, Integer.toString(sco1),btnLvl1.getX() + 170,btnLvl1.getY()-10);
        scoreLvl2.mostrarMensaje(batch, Integer.toString(sco2),btnLvl2.getX() + 170,btnLvl2.getY()-10);
        scoreLvl3.mostrarMensaje(batch, Integer.toString(sco3),btnLvl3.getX()+ 170,btnLvl3.getY()-10);
        batch.end();
        stageLevelsSreen.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        assetManager.unload("backgrounds/levelSelect.png");
        assetManager.unload("buttons/botonNivel1.png");
        assetManager.unload("buttons/botonNivel2.png");
        assetManager.unload("buttons/botonNivel3.png");
        assetManager.unload("buttons/return.png");
        assetManager.unload("buttons/returnPressed.png");
    }
}
