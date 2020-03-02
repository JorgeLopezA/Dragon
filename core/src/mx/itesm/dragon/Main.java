package mx.itesm.dragon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;

import mx.itesm.dragon.Screens.SplashScreen;

public class Main extends Game {

    private final AssetManager assetManager = new AssetManager();
    private Preferences preferences;

    @Override
    public void create() {
        // Pone la pantalla inicial.
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);

        preferences = Gdx.app.getPreferences("Preferences");

        Preferences prefs = Gdx.app.getPreferences("preferenceS");
        Preferences prefm = Gdx.app.getPreferences("preferenceM");
        Preferences prefprog = Gdx.app.getPreferences("preferenceProg");
        //Preferences prefcom = Gdx.app.getPreferences("preferenceC");
        Preferences prefsc1 = Gdx.app.getPreferences("preferenceSc1");
        Preferences prefsc2 = Gdx.app.getPreferences("preferenceSc2");
        Preferences prefsc3 = Gdx.app.getPreferences("preferenceSc3");
        prefprog.getInteger("progress", 1);
        prefsc1.getInteger("sc1",0);
        prefsc2.getInteger("sc2",0);
        prefsc2.getInteger("sc3",0);
        prefs.getBoolean("onSound", true);
        prefm.getBoolean("onMusic", true);
        //prefcom.getBoolean("comicSeen", true);
        prefprog.flush();
        //prefcom.flush();
        prefs.flush();
        prefm.flush();
        prefsc1.flush();
        prefsc2.flush();
        prefsc3.flush();
        setScreen(new SplashScreen(this));

    }



    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}

