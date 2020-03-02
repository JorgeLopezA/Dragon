package mx.itesm.dragon.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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
import mx.itesm.dragon.Objects.LifeCharacter;
import mx.itesm.dragon.Screens.GenericScreen;
import mx.itesm.dragon.Screens.LoadingScreen;
import mx.itesm.dragon.States.GameState;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.AnimatedImage;
import mx.itesm.dragon.Utils.BackGround;
import mx.itesm.dragon.Utils.Text;

public abstract class GenericLevel extends GenericScreen {

    protected static final float ALTO_MAPA = 2560;

    protected float timerVida;

    protected Stage stageJuego;
    protected Stage stagePausa;
    protected Stage stageGanar;
    protected Stage stagePerder;

    protected GameState gameState;
    protected ScreenState screenState;

    protected BackGround backGround;
    protected BackGround backGroundPausa;
    protected BackGround backGroundGanar;
    protected BackGround backGroundPerder;

    protected LifeCharacter lifeCharacter;
    protected Character framesCharacter;

    protected AnimatedImage dragon;
    protected AnimatedImage boss;
    protected Image barraVida;
    protected Image v1,v2,v3,v4;
    protected ImageButton btnPausa;
    protected ImageButton btnReanudar;
    protected ImageButton btnMusica;
    protected ImageButton btnNoMusic;
    protected ImageButton btnMenu;
    protected ImageButton btnSFX;
    protected ImageButton btnNoSFX;
    protected ImageButton btnReiniciar;
    protected ImageButton btnMenuPerder;
    protected ImageButton btnMenuGanar;
    protected ImageButton btnNextLvl;

    //Sonido y musica
    protected Music music;
    protected Music music2;
    protected Music music3;
    protected Sound collision;
    protected Sound impact;
    protected Sound fire;
    protected Sound arrow;
    protected Sound resume;
    protected Sound pause;
    protected Sound heal;

    //Preferencias.
    protected Preferences sonido = Gdx.app.getPreferences("preferenceS");
    protected Preferences musica = Gdx.app.getPreferences("preferenceM");


    // Marcador.//
    protected int puntosJugador = 0;
    protected int bonusRemainingHealth = 0;
    protected String letras;
    protected String bonusLvl;
    protected String bonusLife;
    protected String totScore;
    protected String bonusLvlComplete;
    protected Text puntos;
    protected Text text;
    protected Text bonusLvlText;
    protected Text bonusHeartsText;
    protected Text newScore;
    protected Text bonusHeartsNum;
    protected Text bonusLvlNum;


    protected Texture textureLife;
    protected Texture textureBarLife;
    protected Texture textureBtnPause;
    protected Texture textureFramesDragon;
    protected Texture texturePotion;
    protected Texture textureBackgroundPause;
    protected Texture textureBtnResume;
    protected Texture textureBtnPressResume;
    protected Texture textureBtnMusic;
    protected Texture textureBtnPressMusic;
    protected Texture textureBtnSFX;
    protected Texture textureBtnPressSFX;
    protected Texture textureBtnMenu;
    protected Texture textureBtnPressMenu;
    protected Texture textureBackgroundWin;
    protected Texture textureBackgroundLose;
    protected Texture textureBtnReset;
    protected Texture textureBtnPressReset;

    boolean musicaActiva = musica.getBoolean("onMusic");
    boolean sonidoActivo = sonido.getBoolean("onSound");


    public GenericLevel(Main game) {
        super(game);
        gameState = GameState.JUGANDO;
    }


    protected void initialization() {
        initGame();
        initPause();
        initMusic();
        initLose();
        initWin();
    }

    protected void loadResources() {
        textureBarLife = assetManager.get("textures/healthBar.png");
        textureLife = assetManager.get("textures/heart.png");
        textureBtnPause = assetManager.get("buttons/pause.png");
        textureFramesDragon = assetManager.get("frames/dragon.png");
        texturePotion = assetManager.get("textures/potion.png");
        textureBackgroundPause = assetManager.get("backgrounds/pause.png");
        textureBtnResume = assetManager.get("buttons/resume.png");
        textureBtnPressResume = assetManager.get("buttons/resumePressed.png");
        textureBtnMusic = assetManager.get("buttons/music.png");
        textureBtnPressMusic = assetManager.get("buttons/musicPressed.png");
        textureBtnSFX = assetManager.get("buttons/sfx.png");
        textureBtnPressSFX = assetManager.get("buttons/sfxPressed.png");
        textureBtnMenu = assetManager.get("buttons/mainMenu.png");
        textureBtnPressMenu = assetManager.get("buttons/mainMenuPressed.png");
        textureBackgroundWin = assetManager.get("backgrounds/win.png");
        textureBackgroundLose = assetManager.get("backgrounds/gameOver.png");
        textureBtnReset = assetManager.get("buttons/reset.png");
        textureBtnPressReset = assetManager.get("buttons/resetPressed.png");

        // Música y SFX

        if(screenState.equals(ScreenState.LVL_ONE)){
            music = assetManager.get("music/lvl1.mp3");
        } else if (screenState.equals(ScreenState.LVL_TWO)){
            music2 = assetManager.get("music/lvl2.mp3");
        } else if (screenState.equals(ScreenState.LVL_THREE)){
            music3 = assetManager.get("music/lvl3.mp3");
        }
        arrow = assetManager.get("music/flecha.wav");
        collision = assetManager.get("music/colision.wav");
        fire = assetManager.get("music/fuego.wav");
        pause = assetManager.get("music/pausa.wav");
        resume = assetManager.get("music/reanudar.wav");
        impact = assetManager.get("music/impacto.wav");
        heal = assetManager.get("music/heal.wav");
    }

    protected void back() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            Gdx.input.setInputProcessor(stagePausa);
            gameState = GameState.PAUSA;
            //escenaPausa = new EscenaPausa(vistaHUD, batch);

        }
    }

    protected void home() {
        if(Gdx.input.isKeyPressed(Input.Keys.HOME)) {
            //Gdx.input.setInputProcessor(stagePausa);
            gameState = GameState.PAUSA;

        }
    }

    private void initGame() {
        stageJuego = new Stage(vista);

        timerVida = 0;

        letras = "Score";
        bonusLvlComplete = "3000";
        bonusLvl = "Level Complete Bonus";
        bonusLife = "Remaining Hearts Bonus";
        totScore = "Total Score";
        puntos = new Text();
        text = new Text();
        bonusLvlText = new Text();
        bonusHeartsText = new Text();
        newScore = new Text();
        bonusLvlNum = new Text();
        bonusHeartsNum = new Text();

        barraVida = new Image(textureBarLife);
        v1 = new Image(textureLife);
        v2 = new Image(textureLife);
        v3 = new Image(textureLife);
        v4 = new Image(textureLife);

        btnPausa = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPause)));

        framesCharacter = new Character(textureFramesDragon);
        dragon = new AnimatedImage(framesCharacter.animacion(),framesCharacter.getFrames());

        lifeCharacter = new LifeCharacter(texturePotion);

        // Posisción inicial de los elementos
        btnPausa.setPosition(10,ALTO - btnPausa.getHeight() - 10);
        barraVida.setPosition( ANCHO / 2 - barraVida.getWidth() / 2, ALTO - barraVida.getHeight() - barraVida.getHeight() / 2);
        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, dragon.getY() + dragon.getHeight());

        v1.setPosition(barraVida.getX(),barraVida.getY());
        v2.setPosition(barraVida.getX() + v1.getWidth() + 8, barraVida.getY());
        v3.setPosition(v2.getX() + v2.getWidth() + 8, barraVida.getY());
        v4.setPosition(v3.getX() + v3.getWidth() + 8, barraVida.getY());

        btnPausa.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameState = GameState.PAUSA;
                Gdx.input.setInputProcessor(stagePausa);
            }
        });

        dragon.addListener(new ClickListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camara.unproject(v);
                if (v.y <= ALTO - btnPausa.getHeight() - dragon.getImageHeight() / 2) {
                    dragon.setPosition(v.x - dragon.getWidth() / 2  ,v.y - dragon.getHeight() / 2);
                } else {
                    dragon.setX(v.x - dragon.getImageWidth() / 2);
                }
            }
        });


        // Se anexan los Actores a la Escena.
        stageJuego.addActor(btnPausa);
        stageJuego.addActor(barraVida);
        stageJuego.addActor(v1);
        stageJuego.addActor(v2);
        stageJuego.addActor(v3);
        stageJuego.addActor(v4);
        stageJuego.addActor(dragon);
        // Se anexan las Escenas al Multiplexor.

        Gdx.input.setInputProcessor(stageJuego);
    }

    private void initPause() {
        stagePausa = new Stage(vista);
        backGroundPausa = new BackGround(textureBackgroundPause);

        btnReanudar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnResume)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressResume)));
        btnMusica = new ImageButton(
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
        btnMenu = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMenu)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMenu)));

        // Posisción inicial de los elementos
        btnReanudar.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f);
        btnMusica.setPosition(ANCHO / 8,btnReanudar.getY() - 300);
        btnNoMusic.setPosition(ANCHO / 8,btnReanudar.getY() - 300);
        btnSFX.setPosition(btnMusica.getX() + 330, btnMusica.getY());
        btnNoSFX.setPosition(btnMusica.getX() + 330, btnMusica.getY());
        btnMenu.setPosition(ANCHO / 3,ALTO / 6);

        btnMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });

        btnReanudar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameState = GameState.JUGANDO;
                Gdx.input.setInputProcessor(stageJuego);
            }
        });

        btnSFX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sonido.putBoolean("onSound", false);
                sonido.flush();
                btnSFX.remove();
                stagePausa.addActor(btnNoSFX);
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
                stagePausa.addActor(btnSFX);
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
            }
        });

        btnMusica.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                musica.putBoolean("onMusic", false);
                musica.flush();
                if(screenState.equals(ScreenState.LVL_ONE)){
                    music.stop();
                } else if (screenState.equals(ScreenState.LVL_TWO)){
                    music2.stop();
                } else if (screenState.equals(ScreenState.LVL_THREE)){
                    music3.stop();
                }
                btnMusica.remove();
                stagePausa.addActor(btnNoMusic);
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
            }
        });

        btnNoMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                musica.putBoolean("onMusic", true);
                musica.flush();
                if(screenState.equals(ScreenState.LVL_ONE)){
                    music.play();
                    music.setLooping(true);
                } else if (screenState.equals(ScreenState.LVL_TWO)){
                    music2.play();
                    music2.setLooping(true);
                } else if (screenState.equals(ScreenState.LVL_THREE)){
                    music3.play();
                    music3.setLooping(true);
                }
                btnNoMusic.remove();
                stagePausa.addActor(btnMusica);
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
            }
        });

        stagePausa.addActor(btnReanudar);

        if (musicaActiva){
            stagePausa.addActor(btnMusica);
        } else{
            stagePausa.addActor(btnNoMusic);
        }


        if (sonidoActivo){
            stagePausa.addActor(btnSFX);
        }else{
            stagePausa.addActor(btnNoSFX);
        }

        stagePausa.addActor(btnMenu);

        // Se anexan las Escenas al Multiplexor.
    }

    private void initMusic() {
        // Reproducir música de backGround

        if (musicaActiva){
            if(screenState.equals(ScreenState.LVL_ONE)){
                music.setVolume(1);
                music.play();
                music.setLooping(true);
            } else if (screenState.equals(ScreenState.LVL_TWO)){
                music2.setVolume(1);
                music2.play();
                music2.setLooping(true);
            } else if (screenState.equals(ScreenState.LVL_THREE)){
                music3.setVolume(1);
                music3.play();
                music3.setLooping(true);
            }
        }


    }

    private void initWin() {
        stageGanar = new Stage(vista);
        backGroundGanar = new BackGround(textureBackgroundWin);
        btnNextLvl = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/resume.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/resumePressed.png"))));
        btnMenuGanar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        textureBtnMenu)),
                new TextureRegionDrawable(new TextureRegion(
                        textureBtnPressMenu)));

        btnMenuGanar.setPosition(ANCHO / 3,ALTO - btnMenuGanar.getHeight() * 2.3f - btnMenuGanar.getHeight() -50);
        btnMenuGanar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });

        btnNextLvl.setPosition(btnMenuPerder.getX(), btnMenuPerder.getY() - btnReiniciar.getHeight() -80);

        btnNextLvl.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(screenState.equals(ScreenState.LVL_ONE)){
                game.setScreen(new LoadingScreen(game, ScreenState.LVL_TWO));}
                else if(screenState.equals(ScreenState.LVL_TWO)){
                    game.setScreen(new LoadingScreen(game, ScreenState.LVL_THREE));}
                else {
                    game.setScreen(new LoadingScreen(game, ScreenState.MENU));
                }
            }
        });

        stageGanar.addActor(btnMenuGanar);
        stageGanar.addActor(btnNextLvl);

    }

    private void initLose() {
        stagePerder = new Stage(vista);
        backGroundPerder = new BackGround(textureBackgroundLose);

        btnReiniciar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReset)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressReset)));

        btnMenuPerder = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMenu)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMenu)));


        btnMenuPerder.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f - btnReiniciar.getHeight() -50);

        btnMenuPerder.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });

        btnReiniciar.setPosition(btnMenuPerder.getX(), btnMenuPerder.getY() - btnReiniciar.getHeight() -80);

        btnReiniciar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, screenState));
            }
        });

        stagePerder.addActor(btnMenuPerder);
        stagePerder.addActor(btnReiniciar);

    }

    @Override
    public void pause() {
        gameState = GameState.PAUSA;
    }

    @Override
    public void resume() {
        gameState = GameState.PAUSA;
    }

    @Override
    public void dispose() {

    }
}
