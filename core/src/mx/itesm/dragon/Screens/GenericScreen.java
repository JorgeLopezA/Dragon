package mx.itesm.dragon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mx.itesm.dragon.Main;
import mx.itesm.dragon.Utils.Text;

public abstract class GenericScreen implements Screen {

    protected final Main game;
    protected final AssetManager assetManager;

    // Dimensiones.
    protected static final float ANCHO = 720;
    protected static final float ALTO = 1280;

    // Atributos disponibles solo en las subclases.
    // Todas las pantallas tienen una cámara y una vista.
    protected OrthographicCamera camara;
    protected Viewport vista;

    // Todas las pantallas dibujan.
    protected SpriteBatch batch;

    protected Text marcador;
    protected String letras;
    protected Text scoreLvl1;
    protected Text scoreLvl2;
    protected Text scoreLvl3;

    public GenericScreen(Main game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
        // Crea la cámara con las dimensiones del mundo.
        camara = new OrthographicCamera(ANCHO, ALTO);
        camara.position.set(ANCHO / 2, ALTO /2,0);
        camara.update();
        // Crea la vista que escala los elementos gráficos.
        vista = new StretchViewport(ANCHO, ALTO, camara);
        // Crea el objeto que administra los trazos gráficos
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camara.combined);
    }

    // Borra la pantalla con fondo negro.
    protected void borrarPantalla() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    // Borra la pantalla con el color RGB (r,g,b)
    protected void borrarPantalla(float r, float g, float b, float a) {
        Gdx.gl.glClearColor(r,g,b,a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void moverCamara() {
        camara.translate(0,1);
        camara.update();
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void hide() {
        // Libera los recursos asignados por cada pantalla.
        // Las subclases están obligadas a sobrescribir el método dispose().
        dispose();
    }
}
