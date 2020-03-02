package mx.itesm.dragon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import mx.itesm.dragon.Levels.LevelThree;
import mx.itesm.dragon.Levels.LevelTwo;
import mx.itesm.dragon.Objects.Character;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Main;
import mx.itesm.dragon.Levels.LevelOne;
import mx.itesm.dragon.Utils.AnimatedImage;
import mx.itesm.dragon.Utils.BackGround;

public class LoadingScreen extends GenericScreen {

    private ScreenState gameState;

    private Texture loadingTexture;
    private Texture textureFramesDragon;

    private Character framesCharacter;

    private BackGround backGroundLoading;

    private AnimatedImage dragon;
    private Stage stageLoading;
    private float acumulador;

    public LoadingScreen(Main game, ScreenState gameState) {
        super(game);
        this.gameState = gameState;
    }

    @Override
    public void show() {
        borrarPantalla();
        stageLoading = new Stage(vista);
        loadingTexture = new Texture("backgrounds/loading.png");
        backGroundLoading = new BackGround(loadingTexture);

        textureFramesDragon = new Texture("frames/loading.png");
        framesCharacter = new Character(textureFramesDragon, 448, 179, 5, .15f);
        dragon = new AnimatedImage(framesCharacter.animacion(),framesCharacter.getFrames());

        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, ALTO - 700);

        stageLoading.addActor(dragon);

        load();

        Gdx.input.setInputProcessor(stageLoading);
    }

    private void load() {
        switch (gameState) {
            case MENU:
                assetManager.load("backgrounds/mainMenu.jpg", Texture.class);
                assetManager.load("textures/dragonPlay1.png", Texture.class);
                assetManager.load("buttons/play.png", Texture.class);
                assetManager.load("buttons/playPressed.png", Texture.class);
                assetManager.load("buttons/about.png", Texture.class);
                assetManager.load("buttons/aboutPressed.png", Texture.class);
                assetManager.load("buttons/settings.png", Texture.class);
                assetManager.load("buttons/settingsPressed.png", Texture.class);
                assetManager.load("buttons/info.png", Texture.class);
                assetManager.load("buttons/infoPressed.png", Texture.class);
                assetManager.load("music/premenu.mp3", Music.class);
                assetManager.load("music/jugar.wav", Sound.class);
                assetManager.load("music/config.wav", Sound.class);
                break;
            case SETTINGS:
                assetManager.load("backgrounds/settings.png", Texture.class);
                assetManager.load("buttons/return.png", Texture.class);
                assetManager.load("buttons/returnPressed.png", Texture.class);
                assetManager.load("buttons/sfx.png", Texture.class);
                assetManager.load("buttons/sfxPressed.png", Texture.class);
                assetManager.load("buttons/music.png", Texture.class);
                assetManager.load("buttons/musicPressed.png", Texture.class);
                assetManager.load("buttons/reset.png", Texture.class);
                assetManager.load("buttons/resetPressed.png", Texture.class);
                assetManager.load("music/preconfig.mp3", Music.class);
                assetManager.load("music/regresar.wav", Sound.class);
                break;
            case ABOUT:
                assetManager.load("backgrounds/about.jpg", Texture.class);
                assetManager.load("buttons/return.png", Texture.class);
                assetManager.load("buttons/returnPressed.png", Texture.class);
                assetManager.load("buttons/aboutAna.png", Texture.class);
                assetManager.load("buttons/aboutCharlotte.png", Texture.class);
                assetManager.load("buttons/aboutJorge.png", Texture.class);
                assetManager.load("buttons/aboutLuis.png", Texture.class);
                assetManager.load("buttons/aboutMarco.png", Texture.class);
                assetManager.load("music/preacerca.mp3", Music.class);
                assetManager.load("music/regresar.wav", Sound.class);
                assetManager.load("buttons/aboutAnaPressed.png", Texture.class);
                assetManager.load("buttons/aboutCharlottePressed.png", Texture.class);
                assetManager.load("buttons/aboutJorgePressed.png", Texture.class);
                assetManager.load("buttons/aboutMarcoPressed.png", Texture.class);
                assetManager.load("buttons/aboutLuisPressed.png", Texture.class);
                break;
            case TUTORIAL:
                assetManager.load("backgrounds/tutorial.png", Texture.class);
                assetManager.load("frames/dragon.png", Texture.class);
                assetManager.load("buttons/return.png", Texture.class);
                assetManager.load("buttons/returnPressed.png", Texture.class);
                assetManager.load("music/preacerca.mp3", Music.class);
                assetManager.load("music/regresar.wav", Sound.class);
                break;
            case LEVELS:
                assetManager.load("backgrounds/levelSelect.png", Texture.class);
                assetManager.load("buttons/botonNivel1.png", Texture.class);
                assetManager.load("buttons/level1Button.png", Texture.class);
                assetManager.load("buttons/botonNivel2.png", Texture.class);
                assetManager.load("buttons/level2Button.png", Texture.class);
                assetManager.load("buttons/botonNivel3.png", Texture.class);
                assetManager.load("buttons/level3Button.png", Texture.class);
                assetManager.load("buttons/return.png", Texture.class);
                assetManager.load("buttons/returnPressed.png", Texture.class);
                break;
            case LVL_ONE:
                assetManager.load("backgrounds/level1.png", Texture.class);
                assetManager.load("textures/healthBar.png", Texture.class);
                assetManager.load("textures/heart.png", Texture.class);
                assetManager.load("textures/fireBall.png", Texture.class);
                assetManager.load("textures/rock.png", Texture.class);
                assetManager.load("textures/arrow.png", Texture.class);
                assetManager.load("buttons/pause.png", Texture.class);
                assetManager.load("frames/dragon.png", Texture.class);
                assetManager.load("frames/finalBoss1.png", Texture.class);
                assetManager.load("textures/potion.png", Texture.class);
                assetManager.load("backgrounds/pause.png", Texture.class);
                assetManager.load("buttons/resume.png", Texture.class);
                assetManager.load("buttons/resumePressed.png", Texture.class);
                assetManager.load("buttons/music.png", Texture.class);
                assetManager.load("buttons/musicPressed.png", Texture.class);
                assetManager.load("buttons/sfx.png", Texture.class);
                assetManager.load("buttons/sfxPressed.png", Texture.class);
                assetManager.load("buttons/mainMenu.png", Texture.class);
                assetManager.load("buttons/mainMenuPressed.png", Texture.class);
                assetManager.load("backgrounds/win.png", Texture.class);
                assetManager.load("backgrounds/gameOver.png", Texture.class);
                assetManager.load("buttons/reset.png", Texture.class);
                assetManager.load("buttons/resetPressed.png", Texture.class);
                assetManager.load("music/lvl1.mp3", Music.class);
                assetManager.load("music/flecha.wav", Sound.class);
                assetManager.load("music/colision.wav", Sound.class);
                assetManager.load("music/fuego.wav", Sound.class);
                assetManager.load("music/pausa.wav", Sound.class);
                assetManager.load("music/reanudar.wav", Sound.class);
                assetManager.load("music/impacto.wav", Sound.class);
                assetManager.load("music/heal.wav", Sound.class);
                break;
            case LVL_TWO:
                assetManager.load("backgrounds/level2.png", Texture.class);
                assetManager.load("textures/healthBar.png", Texture.class);
                assetManager.load("textures/heart.png", Texture.class);
                assetManager.load("textures/fireBall.png", Texture.class);
                assetManager.load("textures/carbonBoss.png", Texture.class);
                assetManager.load("textures/carbon.png", Texture.class);
                assetManager.load("buttons/pause.png", Texture.class);
                assetManager.load("frames/dragon.png", Texture.class);
                assetManager.load("textures/EnemigoGlobo.png", Texture.class);
                assetManager.load("frames/finalBoss2.png", Texture.class);
                assetManager.load("textures/potion.png", Texture.class);
                assetManager.load("backgrounds/pause.png", Texture.class);
                assetManager.load("buttons/resume.png", Texture.class);
                assetManager.load("buttons/resumePressed.png", Texture.class);
                assetManager.load("buttons/music.png", Texture.class);
                assetManager.load("buttons/musicPressed.png", Texture.class);
                assetManager.load("buttons/sfx.png", Texture.class);
                assetManager.load("buttons/sfxPressed.png", Texture.class);
                assetManager.load("buttons/mainMenu.png", Texture.class);
                assetManager.load("buttons/mainMenuPressed.png", Texture.class);
                assetManager.load("backgrounds/win.png", Texture.class);
                assetManager.load("backgrounds/gameOver.png", Texture.class);
                assetManager.load("buttons/reset.png", Texture.class);
                assetManager.load("buttons/resetPressed.png", Texture.class);
                assetManager.load("music/lvl2.mp3", Music.class);
                assetManager.load("music/flecha.wav", Sound.class);
                assetManager.load("music/colision.wav", Sound.class);
                assetManager.load("music/fuego.wav", Sound.class);
                assetManager.load("music/pausa.wav", Sound.class);
                assetManager.load("music/reanudar.wav", Sound.class);
                assetManager.load("music/impacto.wav", Sound.class);
                assetManager.load("music/heal.wav", Sound.class);
                break;
            case LVL_THREE:
                assetManager.load("backgrounds/level3.png", Texture.class);
                assetManager.load("textures/healthBar.png", Texture.class);
                assetManager.load("textures/heart.png", Texture.class);
                assetManager.load("buttons/pause.png", Texture.class);
                assetManager.load("frames/dragon.png", Texture.class);
                assetManager.load("textures/EnemigoGlobo.png", Texture.class);
                assetManager.load("textures/fireBall.png", Texture.class);
                assetManager.load("textures/arrow.png", Texture.class);
                assetManager.load("textures/ladrillo.png", Texture.class);
                assetManager.load("textures/raindrop.png", Texture.class);
                assetManager.load("frames/finalBoss3.png", Texture.class);
                assetManager.load("textures/potion.png", Texture.class);
                assetManager.load("backgrounds/pause.png", Texture.class);
                assetManager.load("buttons/resume.png", Texture.class);
                assetManager.load("buttons/resumePressed.png", Texture.class);
                assetManager.load("buttons/music.png", Texture.class);
                assetManager.load("buttons/musicPressed.png", Texture.class);
                assetManager.load("buttons/sfx.png", Texture.class);
                assetManager.load("buttons/sfxPressed.png", Texture.class);
                assetManager.load("buttons/mainMenu.png", Texture.class);
                assetManager.load("buttons/mainMenuPressed.png", Texture.class);
                assetManager.load("backgrounds/win.png", Texture.class);
                assetManager.load("backgrounds/gameOver.png", Texture.class);
                assetManager.load("buttons/reset.png", Texture.class);
                assetManager.load("buttons/resetPressed.png", Texture.class);
                assetManager.load("buttons/botonNivel1.png", Texture.class);
                assetManager.load("buttons/botonNivel2.png", Texture.class);
                assetManager.load("buttons/botonNivel3.png", Texture.class);
                assetManager.load("music/lvl3.mp3", Music.class);
                assetManager.load("music/flecha.wav", Sound.class);
                assetManager.load("music/colision.wav", Sound.class);
                assetManager.load("music/fuego.wav", Sound.class);
                assetManager.load("music/pausa.wav", Sound.class);
                assetManager.load("music/reanudar.wav", Sound.class);
                assetManager.load("music/impacto.wav", Sound.class);
                assetManager.load("music/heal.wav", Sound.class);
                break;
            default:
        }
    }

    @Override
    public void render(float delta) {
        acumulador+=delta;
        updateLoad(acumulador);
        dragon.act(delta);
        batch.begin();
            backGroundLoading.render(batch);
        batch.end();
        stageLoading.draw();
    }

    private void updateLoad(float delta) {
        if (assetManager.update()) {
            if (delta >= 1.5f) {
                acumulador = 0;
                switch (gameState) {
                    case MENU:
                        game.setScreen(new MenuScreen(game, ScreenState.MENU));
                        break;
                    case SETTINGS:
                        game.setScreen(new SettingsScreen(game));
                        break;
                    case ABOUT:
                        game.setScreen(new AboutScreen(game));
                        break;
                    case TUTORIAL:
                        game.setScreen(new TutorialScreen(game));
                        break;
                    case LEVELS:
                        game.setScreen(new LevelsScreen(game, ScreenState.LEVELS));
                        break;
                    case LVL_ONE:
                        game.setScreen(new LevelOne(game, ScreenState.LVL_ONE));
                        break;
                    case LVL_TWO:
                        game.setScreen(new LevelTwo(game, ScreenState.LVL_TWO));
                        break;
                    case LVL_THREE:
                        game.setScreen(new LevelThree(game, ScreenState.LVL_THREE));
                        break;
                    default:
                }
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        loadingTexture.dispose();
    }
}
