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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.dragon.Main;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.BackGround;

public class SettingsScreen extends GenericScreen {

    // Escena para el menu.
    private Stage stageConfiguracion;

    private ImageButton btnRegresar;
    private ImageButton btnSFX;
    private ImageButton btnNoSFX;
    private ImageButton btnMusic;
    private ImageButton btnNoMusic;
    private ImageButton btnReset;

    // BackGround.
    private BackGround backGround;
    private Texture textureBackground;

    private Texture textureBtnReturn;
    private Texture textureBtnPressReturn;
    private Texture textureBtnSFX;
    private Texture textureBtnPressSFX;
    private Texture textureBtnMusic;
    private Texture textureBtnPressMusic;
    private Texture textureBtnReset;
    private Texture textureBtnPressReset;

    //Musica y sonidos
    private Music musicSettings;
    private Sound soundReturn;

    //Preferencia de sonido y musica.
    private Preferences sonido = Gdx.app.getPreferences("preferenceS");
    private Preferences musica = Gdx.app.getPreferences("preferenceM");
    private Preferences progress = Gdx.app.getPreferences("preferenceProg");
    private Preferences score1 = Gdx.app.getPreferences("preferenceSc1");
    private Preferences score2 = Gdx.app.getPreferences("preferenceSc2");
    private Preferences score3 = Gdx.app.getPreferences("preferenceSc3");


    SettingsScreen(Main game) {
        super(game);
    }

    @Override
    public void show() {
        borrarPantalla();
        crearConfiguracion();
    }

    private void crearConfiguracion() {
        // Creación escena GenericScreen configuración.
        stageConfiguracion = new Stage(vista);

        //
        textureBackground = assetManager.get("backgrounds/settings.png");
        backGround = new BackGround(textureBackground);
        textureBtnReturn = assetManager.get("buttons/return.png");
        textureBtnPressReturn = assetManager.get("buttons/returnPressed.png");
        textureBtnSFX = assetManager.get("buttons/sfx.png");
        textureBtnPressSFX = assetManager.get("buttons/sfxPressed.png");
        textureBtnMusic = assetManager.get("buttons/music.png");
        textureBtnPressMusic = assetManager.get("buttons/musicPressed.png");
        textureBtnReset = assetManager.get("buttons/reset.png");
        textureBtnPressReset = assetManager.get("buttons/resetPressed.png");

        btnRegresar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReturn)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressReturn)));
        btnSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnSFX)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressSFX)));
        btnNoSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressSFX)));
        btnMusic = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMusic)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMusic)));
        btnNoMusic = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMusic)));
        btnReset = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReset)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressReset)));


        musicSettings = assetManager.get("music/preconfig.mp3");
        soundReturn = assetManager.get("music/regresar.wav");

        boolean musicaActiva = musica.getBoolean("onMusic");
        if(musicaActiva){
            musicSettings.setVolume(1);
            musicSettings.play();
            musicSettings.setLooping(true);
        }

        final boolean sonidoActivo = sonido.getBoolean("onSound");

        final int progReset = progress.getInteger("progress");

        // Posición de los botones.
        btnReset.setPosition(ANCHO / 2 - btnReset.getWidth() / 2, ALTO / 2 + btnReset.getHeight() / 2);
        btnRegresar.setPosition(ANCHO - btnRegresar.getWidth() - 20,20);
        btnMusic.setPosition(40, ALTO / 3.5f);
        btnNoMusic.setPosition(40, ALTO / 3.5f);
        btnSFX.setPosition(ANCHO - btnSFX.getWidth() * 1.3f, btnMusic.getY());
        btnNoSFX.setPosition(ANCHO - btnSFX.getWidth() * 1.3f, btnMusic.getY());

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


        btnSFX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sonido.putBoolean("onSound", false);
                sonido.flush();
                btnSFX.remove();
                stageConfiguracion.addActor(btnNoSFX);
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
            }
        });

        btnNoSFX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sonido.putBoolean("onSound", true);
                sonido.flush();
                btnNoSFX.remove();
                stageConfiguracion.addActor(btnSFX);
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
            }
        });

        btnMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                musica.putBoolean("onMusic", false);
                musica.flush();
                musicSettings.stop();
                btnMusic.remove();
                stageConfiguracion.addActor(btnNoMusic);
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
            }
        });

        btnNoMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                musica.putBoolean("onMusic", true);
                musica.flush();
                musicSettings.play();
                musicSettings.play();
                musicSettings.setLooping(true);
                btnNoMusic.remove();
                stageConfiguracion.addActor(btnMusic);
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
            }
        });

        btnReset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambia de pantalla, solo puede hacerlo 'game'.
                if (progReset > 1){
                    progress.putInteger("progress", 1);

                    progress.flush();
                }
                score1.putInteger("sc1",0);
                score2.putInteger("sc2",0);
                score3.putInteger("sc3",0);
                score1.flush();
                score2.flush();
                score3.flush();

            }
        });

        // Se agregan elementos a la GenericScreen Configuración.
        stageConfiguracion.addActor(btnRegresar);
        if (musicaActiva){
            stageConfiguracion.addActor(btnMusic);
        } else{
            stageConfiguracion.addActor(btnNoMusic);
        }

        if (sonidoActivo){
            stageConfiguracion.addActor(btnSFX);
        }else{
            stageConfiguracion.addActor(btnNoSFX);
        }


        stageConfiguracion.addActor(btnReset);


        // Indica quién escucha y atiende eventos.
        Gdx.input.setInputProcessor(stageConfiguracion);
    }

    protected void back() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new LoadingScreen(game, ScreenState.MENU));
        }
    }

    @Override
    public void render(float delta) {
        back();
        // DIBUJAR.
        batch.begin();
            // Dibujar elementos de la pantalla.
            backGround.render(batch);
        batch.end();
        stageConfiguracion.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stageConfiguracion.dispose();
        batch.dispose();
        assetManager.unload("backgrounds/settings.png");
        assetManager.unload("buttons/return.png");
        assetManager.unload("buttons/returnPressed.png");
        assetManager.unload("buttons/sfx.png");
        assetManager.unload("buttons/sfxPressed.png");
        assetManager.unload("buttons/music.png");
        assetManager.unload("buttons/musicPressed.png");
        assetManager.unload("buttons/reset.png");
        assetManager.unload("buttons/resetPressed.png");
        assetManager.unload("music/preconfig.mp3");
        assetManager.unload("music/regresar.wav");
    }
}
