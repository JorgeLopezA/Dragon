package mx.itesm.dragon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.dragon.Main;
import mx.itesm.dragon.Objects.Character;
import mx.itesm.dragon.States.GameState;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.AnimatedImage;
import mx.itesm.dragon.Utils.BackGround;

public class AboutScreen extends GenericScreen {

    // Escena para el menu.
    private Stage stageAcercaDe;

    private ImageButton btnRegresar;
    private ImageButton btnAna;
    private ImageButton btnCharlotte;
    private ImageButton btnJorge;
    private ImageButton btnLuis;
    private ImageButton btnMarco;



    // BackGround.
    private BackGround backGround;
    private Texture textureBackground;

    private Texture textureBtnReturn;
    private Texture textureBtnPressReturn;
    private Texture textureBtnAna;
    private Texture textureBtnCharlotte;
    private Texture textureBtnJorge;
    private Texture textureBtnMarco;
    private Texture textureBtnLuis;

    //Pressed person buttons-
    private Texture textureBtnAnaPressed;
    private Texture textureBtnCharlottePressed;
    private Texture textureBtnJorgePressed;
    private Texture textureBtnMarcoPressed;
    private Texture textureBtnLuisPressed;



    //Preferencias.
    private Preferences sonido = Gdx.app.getPreferences("preferenceS");
    private Preferences musica = Gdx.app.getPreferences("preferenceM");

    // Musica de backGround y sonidos
    private Music musicAbout;
    private Sound soundReturn;

    public AboutScreen(Main game) {
       super(game);
    }

    @Override
    public void show() {
        borrarPantalla();
        crearAcercaDe();
    }

    private void crearAcercaDe() {
        // Creación escena Acerca De.
        stageAcercaDe = new Stage(vista);

        // Creacion del backGround.
        textureBackground = assetManager.get("backgrounds/about.jpg");
        backGround = new BackGround(textureBackground);

        // Creación de los botones del menú.
        textureBtnReturn = assetManager.get("buttons/return.png");
        textureBtnPressReturn = assetManager.get("buttons/returnPressed.png");
        textureBtnAna = assetManager.get("buttons/aboutAna.png");
        textureBtnCharlotte = assetManager.get("buttons/aboutCharlotte.png");
        textureBtnJorge = assetManager.get("buttons/aboutJorge.png");
        textureBtnLuis = assetManager.get("buttons/aboutLuis.png");
        textureBtnMarco = assetManager.get("buttons/aboutMarco.png");

        // Creación de botones con información de persona-
        textureBtnAnaPressed = assetManager.get("buttons/aboutAnaPressed.png");
        textureBtnCharlottePressed = assetManager.get("buttons/aboutCharlottePressed.png");
        textureBtnJorgePressed = assetManager.get("buttons/aboutJorgePressed.png");
        textureBtnMarcoPressed = assetManager.get("buttons/aboutMarcoPressed.png");
        textureBtnLuisPressed = assetManager.get("buttons/aboutLuisPressed.png");



        // Creación de los botones a la GenericScreen Acerca De.

        //Se agreguó la textura de boton presionado para todas las personas-
        btnRegresar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReturn)),
                new TextureRegionDrawable( //en esta parte-
                        new TextureRegion(
                                textureBtnPressReturn)));
        btnAna = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnAna)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnAnaPressed)));
        btnCharlotte = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnCharlotte)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnCharlottePressed)));
        btnJorge = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnJorge)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnJorgePressed)));
        btnLuis = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnLuis)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnLuisPressed)));
        btnMarco = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMarco)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMarcoPressed)));

        // Creacion de musica y sonido
        musicAbout =  assetManager.get("music/preacerca.mp3");
        soundReturn =  assetManager.get("music/regresar.wav");

        // Reproduccion de la musica de backGround
        boolean musicaActiva = musica.getBoolean("onMusic");
        if (musicaActiva){
            musicAbout.setVolume(1);
            musicAbout.play();
            musicAbout.setLooping(true);
        }

        final boolean sonidoActivo = sonido.getBoolean("onSound");

        // Posición de los botones.
        btnLuis.setPosition(85,900);
        btnCharlotte.setPosition(350,650);
        btnJorge.setPosition(85,400);
        btnAna.setPosition(350,200);
        btnMarco.setPosition(85,50);
        btnRegresar.setPosition(ANCHO - btnRegresar.getWidth() - 20,20);

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

        // Detecta si el usuario hace click en el boton de la persona-

        btnLuis.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        btnCharlotte.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        btnJorge.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        btnAna.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        btnMarco.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        // TODO FALTA HABILITAR LOS BOTONES CON SU RESPECTIVA DESCRIPCION.

        // Se agregan elementos a la GenericScreen Acerca De.
        stageAcercaDe.addActor(btnRegresar);
        /*
        stageAcercaDe.addActor(btnAna);
        stageAcercaDe.addActor(btnCharlotte);
        stageAcercaDe.addActor(btnJorge);
        stageAcercaDe.addActor(btnLuis);
        stageAcercaDe.addActor(btnMarco);
*/



        // Indica quién escucha y atiende eventos.
        Gdx.input.setInputProcessor(stageAcercaDe);
    }

    protected void back() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new LoadingScreen(game, ScreenState.MENU));
        }
    }

    @Override
    public void render(float delta) {
        // DIBUJAR.
        back();
        batch.begin();
            // Dibujar elementos de la pantalla.
            backGround.render(batch);
        batch.end();
        stageAcercaDe.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stageAcercaDe.dispose();
        batch.dispose();
        assetManager.unload("backgrounds/about.jpg");
        assetManager.unload("buttons/return.png");
        assetManager.unload("buttons/returnPressed.png");
        assetManager.unload("buttons/aboutAna.png");
        assetManager.unload("buttons/aboutCharlotte.png");
        assetManager.unload("buttons/aboutJorge.png");
        assetManager.unload("buttons/aboutLuis.png");
        assetManager.unload("buttons/aboutMarco.png");
        assetManager.unload("music/preacerca.mp3");
        assetManager.unload("music/regresar.wav");

        //Y se va-
        assetManager.unload("buttons/aboutAnaPressed.png");
        assetManager.unload("buttons/aboutCharlottePressed.png");
        assetManager.unload("buttons/aboutJorgePressed.png");
        assetManager.unload("buttons/aboutMarcoPressed.png");
        assetManager.unload("buttons/aboutLuisPressed.png");

    }
}
