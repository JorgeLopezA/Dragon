package mx.itesm.dragon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.dragon.Main;
import mx.itesm.dragon.Objects.Character;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.AnimatedImage;
import mx.itesm.dragon.Utils.BackGround;

/**
 * Created by jorge on 10/05/2018.
 */

public class
TutorialScreen extends GenericScreen {

    // Escena para el menu.
    private Stage stageTutorial;
    private ImageButton btnRegresar;

    // BackGround.
    private BackGround backGround;
    private Texture textureBackground;
    private Texture textureDragon;
    private Texture textureBtnReturn;
    private Texture textureBtnPressReturn;

    private AnimatedImage dragon;

    //Preferencias.
    private Preferences sonido = Gdx.app.getPreferences("preferenceS");
    private Preferences musica = Gdx.app.getPreferences("preferenceM");

    // Musica de backGround y sonidos
    private Music musicTutorial;
    private Sound soundReturn;
    private Character framesCharacter;
    private com.badlogic.gdx.graphics.Texture textureFramesDragon;

    public TutorialScreen(Main game) {
        super(game);
    }

    @Override
    public void show() {
        borrarPantalla();
        crearTutorial();
    }

    private void crearTutorial() {
        // Creación escena Acerca De.
        stageTutorial = new Stage(vista);

        // Creacion del backGround.
        textureBackground = assetManager.get("backgrounds/tutorial.png");
        backGround = new BackGround(textureBackground);

        // Creación de los botones del menú.
        textureBtnReturn = assetManager.get("buttons/return.png");
        textureBtnPressReturn = assetManager.get("buttons/returnPressed.png");
        textureFramesDragon = assetManager.get("frames/dragon.png");


        // Creación de los botones a la GenericScreen Acerca De.
        btnRegresar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReturn)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressReturn)));

        // Creacion de musica y sonido
        musicTutorial =  assetManager.get("music/preacerca.mp3");
        soundReturn =  assetManager.get("music/regresar.wav");

        framesCharacter = new Character(textureFramesDragon);
        dragon = new AnimatedImage(framesCharacter.animacion(),framesCharacter.getFrames());

        // Reproduccion de la musica de backGround
        boolean musicaActiva = musica.getBoolean("onMusic");
        if (musicaActiva){
            musicTutorial.setVolume(1);
            musicTutorial.play();
            musicTutorial.setLooping(true);
        }

        final boolean sonidoActivo = sonido.getBoolean("onSound");

        // Posición de los botones.

        btnRegresar.setPosition(ANCHO - btnRegresar.getWidth() - 20,20);
        dragon.setPosition(100, ALTO - 480);

        // Detecta si el usuario hace click en algún actor.
        btnRegresar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (sonidoActivo){
                    soundReturn.play();
                }
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });

        dragon.addListener(new DragListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camara.unproject(v);
                dragon.setPosition(v.x - dragon.getWidth() / 2, v.y - dragon.getHeight() / 2);

            }
        });

        // Se agregan elementos a la GenericScreen Acerca De.
        stageTutorial.addActor(btnRegresar);
        stageTutorial.addActor(dragon);

        // Indica quién escucha y atiende eventos.
        Gdx.input.setInputProcessor(stageTutorial);
    }

    protected void back() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new LoadingScreen(game, ScreenState.MENU));
        }
    }

    @Override
    public void render(float delta) {
        back();
        dragon.act(delta);
        // DIBUJAR.
        batch.begin();
        // Dibujar elementos de la pantalla.
        backGround.render(batch);
        batch.end();
        stageTutorial.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stageTutorial.dispose();
        batch.dispose();
        assetManager.unload("backgrounds/tutorial.png");
        assetManager.unload("frames/dragon.png");
        assetManager.unload("buttons/return.png");
        assetManager.unload("buttons/returnPressed.png");
        assetManager.unload("music/preacerca.mp3");
        assetManager.unload("music/regresar.wav");
    }





}
