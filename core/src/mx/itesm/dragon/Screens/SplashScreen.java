package mx.itesm.dragon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import mx.itesm.dragon.Main;
import mx.itesm.dragon.States.ScreenState;

public class SplashScreen extends GenericScreen {

    private Texture textureLogo;
    private Texture comic1;
    private Texture comic2;

    private Preferences comic = Gdx.app.getPreferences("preferenceC");

    public SplashScreen(Main game) {
        super(game);
    }

    @Override
    public void show() {
        loadResources();
        textureLogo = new Texture("backgrounds/loadingLogo.png");
        comic1 = new Texture("backgrounds/comic1.png");
        comic2 = new Texture("backgrounds/comic2.png");
    }

    private void loadResources() {
        assetManager.load("textures/tecLogo.jpg", Texture.class);
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
    }


    @Override
    public void render(float delta) {
        batch.begin();
            batch.draw(textureLogo, ANCHO / 2 - textureLogo.getWidth() / 2, ALTO / 2 - textureLogo.getHeight() / 2);
            borrarPantalla();
            /*boolean first = comic.getBoolean("comicSeen");
            if (first){
                batch.draw(comic1, ANCHO / 2 - comic1.getWidth() / 2, ALTO / 2 - comic1.getHeight() / 2);
                borrarPantalla();
                batch.draw(comic2, ANCHO / 2 - comic2.getWidth() / 2, ALTO / 2 - comic2.getHeight() / 2);
                borrarPantalla();
                comic.putBoolean("comicSeen", false);
                comic.flush();
            }*/
        batch.end();
        updateLoad();
    }

    private void updateLoad() {
        if (assetManager.update()) {
                game.setScreen(new MenuScreen(game, ScreenState.MENU));
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
        textureLogo.dispose();
    }
}
