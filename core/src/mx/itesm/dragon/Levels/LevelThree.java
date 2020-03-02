package mx.itesm.dragon.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import mx.itesm.dragon.Main;
import mx.itesm.dragon.Objects.Boss;
import mx.itesm.dragon.Objects.Enemy;
import mx.itesm.dragon.Objects.Fire;
import mx.itesm.dragon.Objects.LifeCharacter;
import mx.itesm.dragon.States.GameState;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.AnimatedImage;
import mx.itesm.dragon.Utils.BackGround;

public class LevelThree extends GenericLevel {

    private float timerProyectil;
    private float timerFlecha;
    private float timerGlobo;
    private float timerJefeFinal;
    private float timerInvincibility; // tiempo que debe transcurrir para que el jugador vuelva a recibir daño.

    // determinan el movimiemto del jefe final
    private boolean jefePos = false;
    private int direccion = 1;

    private Random random;

    private Texture proyectil;
    private Texture proyectilJefeFinal;
    private Texture flecha;
    private Texture globo;



    private ArrayList<Fire> listaProyectil;
    private ArrayList<Fire> listaProyectilJefe;
    private ArrayList<Enemy> listaFlechas;
    private ArrayList<Enemy> listaGlobos;
    private ArrayList<LifeCharacter> listaVidas;

    private BackGround backGround;

    private Boss framesJefeFinal;

    private Preferences score3 = Gdx.app.getPreferences("preferenceSc3");


    // Marcador.
    private int puntosJugador = 0;
    private float timerVida;
    private float timerProyectilJefeFinal;
    private boolean bonusPoints = true;
    protected static int finalScore;
    private Texture textureFramesFinalBoss;
    private Texture textureBackground;


    public LevelThree(Main game, ScreenState lvlThree) {
        super(game);
        screenState = lvlThree;
    }

    @Override
    public void show() {
        borrarPantalla();
        loadResources();
        initialization();
        //create();
        initJuego(); // INICIALIZACIÓN DE COMPONENTES.
    }

    /*
    private void create() {
        // INICIALIZACIÓN DE COMPONENTES.
        initJuego();
    }
    */

    private void initJuego() {
        timerProyectil = 0;
        timerFlecha = 0;
        timerJefeFinal = 0;
        timerProyectilJefeFinal = 0;

        listaProyectil = new ArrayList<Fire>();
        listaFlechas = new ArrayList<Enemy>();
        listaGlobos = new ArrayList<Enemy>();
        listaVidas = new ArrayList<LifeCharacter>();
        listaProyectilJefe = new ArrayList<Fire>();

        random = new Random();

        textureBackground = assetManager.get("backgrounds/level3.png");
        backGround = new BackGround(textureBackground);

        proyectil = assetManager.get("textures/fireBall.png");
        proyectilJefeFinal = assetManager.get("textures/ladrillo.png");
        flecha = assetManager.get("textures/raindrop.png");
        lifeCharacter = new LifeCharacter(texturePotion);
        globo = assetManager.get("textures/EnemigoGlobo.png");

        textureFramesFinalBoss = assetManager.get("frames/finalBoss3.png");
        framesJefeFinal  = new Boss(textureFramesFinalBoss,516,425,2,.125f);
        boss = new AnimatedImage(framesJefeFinal.animacion());

        boss.setPosition(0 - boss.getWidth()*2, ALTO);
        stageJuego.addActor(boss);
        // Se anexan las Escenas al Multiplexor.
    }

    @Override
    public void render(float delta) {
        switch (gameState) {
            case JUGANDO:
                actualizarObjetos(delta);
                home();
                back();
                batch.begin();
                backGround.render(batch);
                //Marcador
                text.mostrarMensaje(batch,letras,ANCHO - ANCHO/8, ALTO);
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador), ANCHO - ANCHO/8, ALTO-50);

                for (Fire p: listaProyectil) {
                    //fuego.play();
                    p.render(batch);
                }
                for (Fire pjf: listaProyectilJefe) {
                    pjf.render(batch);
                }
                for (LifeCharacter v : listaVidas){
                    v.render(batch);
                }

                for (Enemy e: listaFlechas) {
                    //flecha_s.play();
                    e.render(batch);
                }
                for (Enemy g: listaGlobos){
                    g.render(batch);
                }
                batch.end();
                stageJuego.draw();

                if(lifeCharacter.getVidas() == 0){
                    gameState = GameState.PERDER;
                    Gdx.input.setInputProcessor(stagePerder);
                }

                if(framesJefeFinal.getVida() == -10){
                    gameState = GameState.GANAR;
                    Gdx.input.setInputProcessor(stageGanar);
                }

                break;
            case PAUSA:
                Gdx.input.setInputProcessor(stagePausa);
                batch.begin();
                backGroundPausa.render(batch);
                batch.end();

                stagePausa.draw();
                break;
            case PERDER:
                batch.begin();
                backGroundPerder.render(batch);
                text.mostrarMensaje(batch,letras,ANCHO / 2, ALTO - ALTO / 4-50);
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador), ANCHO / 2, ALTO - ALTO /4 - 100);

                batch.end();
                stagePerder.draw();
                break;
            case GANAR:
                if (bonusPoints) {
                    puntosJugador += 3000;
                    switch (lifeCharacter.getVidas()) {
                        case 1:
                            bonusRemainingHealth += 500;
                            break;
                        case 2:
                            bonusRemainingHealth += 1000;
                            break;
                        case 3:
                            bonusRemainingHealth += 1500;
                            break;
                        case 4:
                            bonusRemainingHealth += 2000;
                            break;
                    }
                    bonusPoints = false;
                }

                batch.begin();
                backGroundGanar.render(batch);
                text.mostrarMensaje(batch,letras,ANCHO / 4, ALTO - ALTO / 4 + 50 );
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador -3000), ANCHO - 130, ALTO - ALTO / 4 + 50 );
                bonusLvlText.mostrarMensaje(batch,bonusLvl,ANCHO / 3, ALTO - ALTO / 4 -10);
                bonusLvlNum.mostrarMensaje(batch,bonusLvlComplete,ANCHO - 130, ALTO - ALTO / 4 -10);
                bonusHeartsText.mostrarMensaje(batch,bonusLife,ANCHO / 3, ALTO - ALTO / 4 - 70);
                bonusHeartsNum.mostrarMensaje(batch,Integer.toString(bonusRemainingHealth), ANCHO - 130, ALTO - ALTO / 4 - 70);
                newScore.mostrarMensaje(batch,totScore,ANCHO / 3, ALTO - ALTO / 4 - 160);
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador + bonusRemainingHealth),ANCHO - 130, ALTO - ALTO / 4 - 160);

                batch.end();
                finalScore = puntosJugador + bonusRemainingHealth;
                score3.putInteger("sc3", finalScore);
                score3.flush();
                stageGanar.draw();
                break;
        }
    }

    private void actualizarObjetos(float delta) {
        actualizarFondo(delta);
        actualizarProyectiles(delta);
        actualizarEnemigos(delta);
        actualizarColisiones(delta);
        actualizarPersonaje(delta);
        actualizarJefeFinal(delta);
        actualizarCamara();
        actualizarVida(delta);
    }

    private void actualizarCamara() {
        // Depende de la posición del personaje. Siempre sigue al personaje
        float y = dragon.getImageY();
        // Primera mitad de la pantalla.
        if (y < ALTO/2 ) {
            camara.position.set(ANCHO / 2, ALTO / 2, 0);
        } else if (y > ALTO_MAPA - ANCHO / 2) {   // Última mitad de la pantalla
            camara.position.set(camara.position.x,ALTO_MAPA - ANCHO/2,0);
        }
        moverCamara();
    }

    private void actualizarPersonaje(float delta) {
        dragon.act(delta);
    }

    private void actualizarJefeFinal(float delta) {
        timerJefeFinal += delta;
        boss.act(delta);
        if (timerJefeFinal >= 30){
            if (jefePos) {
                boss.setPosition(boss.getX() + (3 * direccion), boss.getY());

                if (boss.getX() + boss.getWidth() >= ANCHO) {
                    direccion = -1;
                }
                if (boss.getX() <= 0) {
                    direccion = 1;
                }
            }
            else {
                if (boss.getX() >= ANCHO / 2 - boss.getImageWidth() / 2) {
                    jefePos = true;
                } else {
                    boss.setPosition(boss.getX() + 3, boss.getY() - 1.5f);
                }
            }
        }


    }

    private void actualizarFondo(float delta) {
        backGround.mover(delta);
    }

    private void actualizarColisiones(float delta) {
        timerInvincibility -= delta;
        for (int i = 0; i < listaProyectil.size(); i++) {
            Fire proyectil = listaProyectil.get(i);
            Rectangle rectProyectil = proyectil.getSprite().getBoundingRectangle();

            Rectangle rectJefeFinal = new Rectangle(boss.getX(), boss.getY(), boss.getImageWidth(), boss.getImageHeight());
            if (rectJefeFinal.overlaps(rectProyectil)) {
                boolean sonidoActivo = sonido.getBoolean("onSound");
                if (sonidoActivo){
                    impact.play();
                }
                listaProyectil.remove(i);
                framesJefeFinal.setVida(framesJefeFinal.getVida() - 1);
            }
        }

        for (int i = 0; i < listaVidas.size(); i++) {
            LifeCharacter pocima = listaVidas.get(i);
            Rectangle rectDragon = new Rectangle(dragon.getX() + 151,dragon.getY(),151,dragon.getHeight() / 2);
            Rectangle rectPocima = pocima.getSprite().getBoundingRectangle();
            if (rectDragon.overlaps(rectPocima)) {
                boolean sonidoActivo = sonido.getBoolean("onSound");
                if (sonidoActivo){
                    heal.play();
                }
                listaVidas.remove(pocima);

                switch (lifeCharacter.getVidas()){
                    case 1:
                        lifeCharacter.setVidas(lifeCharacter.getVidas()+1);
                        v2.setVisible(true);
                        break;
                    case 2:
                        lifeCharacter.setVidas(lifeCharacter.getVidas()+1);
                        v3.setVisible(true);
                        break;
                    case 3:
                        lifeCharacter.setVidas(lifeCharacter.getVidas()+1);
                        v4.setVisible(true);
                        break;
                    default:
                        break;
                }
            }

        }
        for (int i = 0; i < listaProyectil.size(); i++) {
            Fire proyectil = listaProyectil.get(i);
            for (int j = 0; j < listaProyectilJefe.size(); j++) {
                Fire proyectilJefe = listaProyectilJefe.get(j);
                Rectangle rectProyectil = proyectil.getSprite().getBoundingRectangle();
                Rectangle rectProyectilJefe = proyectilJefe.getSprite().getBoundingRectangle();
                if (rectProyectil.overlaps(rectProyectilJefe)) {
                    boolean sonidoActivo = sonido.getBoolean("onSound");
                    if (sonidoActivo){
                        fire.play();
                    }
                    listaProyectil.remove(proyectil);
                }
            }
        }

        for (int i = 0; i < listaProyectil.size(); i++) {
            Fire proyectil = listaProyectil.get(i);
            for (int j = 0; j < listaFlechas.size(); j++) {
                Enemy flechas = listaFlechas.get(j);
                Rectangle rectProyectil = proyectil.getSprite().getBoundingRectangle();
                Rectangle rectFlechas = flechas.getSprite().getBoundingRectangle();
                if (rectProyectil.overlaps(rectFlechas)) {
                    puntosJugador += 100;
                    boolean sonidoActivo = sonido.getBoolean("onSound");
                    if (sonidoActivo){
                        collision.play();
                    }
                    listaProyectil.remove(proyectil);
                    listaFlechas.remove(flechas);
                    break;
                }
            }
        }
        for (int i = 0; i < listaFlechas.size(); i++) {
            Enemy flechas = listaFlechas.get(i);
            Rectangle rectDragon = new Rectangle(dragon.getX() + 151,dragon.getY(),151,dragon.getHeight() / 2);
            //Rectangle rectJefeFinal = new Rectangle(boss.getX(), boss.getY(), boss.getImageWidth(), boss.getImageHeight());
            Rectangle rectFlechas = flechas.getSprite().getBoundingRectangle();
            if (rectDragon.overlaps(rectFlechas)) {
                boolean sonidoActivo = sonido.getBoolean("onSound");
                if (sonidoActivo){
                    impact.play();
                }
                if (puntosJugador - 200 >= 0) {
                    puntosJugador -= 200;
                }
                else {
                    puntosJugador = 0;
                }

                switch (lifeCharacter.getVidas()) {
                    case 1:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v1.setVisible(false);
                        break;
                    case 2:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v2.setVisible(false);
                        break;
                    case 3:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v3.setVisible(false);
                        break;
                    case 4:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v4.setVisible(false);
                        break;
                }
                listaFlechas.remove(i);
                break;
            }
        }
        for (int i = 0; i < listaProyectil.size(); i++) {
            Fire proyectil = listaProyectil.get(i);
            for (int j = 0; j < listaGlobos.size(); j++) {
                Enemy globos = listaGlobos.get(j);
                Rectangle rectProyectil = proyectil.getSprite().getBoundingRectangle();
                Rectangle rectGlobo = new Rectangle(globos.getSprite().getX()+9,globos.getSprite().getY()+111,95,91);
                if (rectProyectil.overlaps(rectGlobo)) {
                    puntosJugador += 200;
                    boolean sonidoActivo = sonido.getBoolean("onSound");
                    if (sonidoActivo){
                        collision.play();
                    }
                    listaProyectil.remove(proyectil);
                    listaGlobos.remove(globos);
                    break;
                }
            }
        }
        for (int i = 0; i < listaGlobos.size(); i++) {
            Enemy globos = listaGlobos.get(i);
            Rectangle rectDragon = new Rectangle(dragon.getX() + 151,dragon.getY(),151,dragon.getHeight() / 2);
            Rectangle rectGlobo = new Rectangle(globos.getSprite().getX()+9,globos.getSprite().getY()+58,95,142 );
            if (rectDragon.overlaps(rectGlobo) && timerInvincibility <= 0) {
                boolean sonidoActivo = sonido.getBoolean("onSound");
                if (sonidoActivo){
                    impact.play();
                }
                if (puntosJugador - 200 >= 0) {
                    puntosJugador -= 200;
                }
                else {
                    puntosJugador = 0;
                }

                switch (lifeCharacter.getVidas()) {
                    case 1:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v1.setVisible(false);
                        break;
                    case 2:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v2.setVisible(false);
                        break;
                    case 3:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v3.setVisible(false);
                        break;
                    case 4:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v4.setVisible(false);
                        break;
                }
                timerInvincibility = 1.5f;
                //listaGlobos.remove(i);
                break;
            }
        }

        for (int i = 0; i < listaProyectilJefe.size(); i++) {
            Fire proyectilJefe = listaProyectilJefe.get(i);
            Rectangle rectDragon = new Rectangle(dragon.getX() + 151,dragon.getY(),151,dragon.getHeight() / 2);
            Rectangle rectJefeFinal = proyectilJefe.getSprite().getBoundingRectangle();
            Rectangle rectJefeFinal2 = new Rectangle(boss.getX(),boss.getY(),boss.getImageWidth(),boss.getImageHeight());
            if (rectDragon.overlaps(rectJefeFinal)) {
                boolean sonidoActivo = sonido.getBoolean("onSound");
                if (sonidoActivo){
                    impact.play();
                }
                switch (lifeCharacter.getVidas()) {
                    case 1:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v1.setVisible(false);
                        break;
                    case 2:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v2.setVisible(false);
                        break;
                    case 3:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v3.setVisible(false);
                        break;
                    case 4:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v4.setVisible(false);
                        break;
                }
                listaProyectilJefe.remove(i);
                break;
            }
            if (rectDragon.overlaps(rectJefeFinal2)&& timerInvincibility <= 0) {
                boolean sonidoActivo = sonido.getBoolean("onSound");
                if (sonidoActivo){
                    impact.play();
                }
                switch (lifeCharacter.getVidas()) {
                    case 1:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v1.setVisible(false);
                        break;
                    case 2:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v2.setVisible(false);
                        break;
                    case 3:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v3.setVisible(false);
                        break;
                    case 4:
                        lifeCharacter.setVidas(lifeCharacter.getVidas() - 1);
                        v4.setVisible(false);
                        break;
                }
                timerInvincibility = 1.3f;
                break;
            }
        }

    }

    private void actualizarEnemigos(float delta) {
        timerFlecha += delta;
        int randomX = random.nextInt((int) ANCHO - flecha.getWidth());
        if (timerFlecha >= .75f) {
            listaFlechas.add(new Enemy(flecha, randomX, ALTO));
            timerFlecha = 0;
        }
        for (int i = 0; i < listaFlechas.size(); i++) {
            listaFlechas.get(i).mover();
        }
        for (int i = 0; i < listaFlechas.size(); i++) {
            if (listaFlechas.get(i).getSprite().getHeight() <= 0) {
                listaFlechas.remove(i);
            }
        }
        timerGlobo += delta;
        int randomY = random.nextInt(Math.round(ALTO/2));
        if (timerGlobo >= 3) {
            listaGlobos.add(new Enemy(globo, ANCHO, randomY));
            timerGlobo = 0;
        }
        for (int i = 0; i < listaGlobos.size(); i++) {
            listaGlobos.get(i).moverX();
        }

        for (int i = 0; i < listaGlobos.size(); i++) {
            if (listaGlobos.get(i).getSprite().getX() <= -globo.getWidth()) {
                listaGlobos.remove(i);
            }
        }
    }

    private void actualizarProyectiles(float delta) {
        timerProyectil += delta;
        timerProyectilJefeFinal += delta;
        if (timerProyectil >= .350f){
            listaProyectil.add(new Fire(proyectil, dragon.getX() + dragon.getWidth() / 2 - proyectil.getWidth() / 2, dragon.getY() + dragon.getHeight()));
            timerProyectil = 0;
        }
        for (int i = 0; i < listaProyectil.size(); i++) {
            listaProyectil.get(i).mover();
        }
        for (int i = 0; i < listaProyectil.size(); i++) {
            if (listaProyectil.get(i).getSprite().getY() >= ALTO) {
                listaProyectil.remove(i);
            }
        }
        if (timerProyectilJefeFinal >= 1f) {
            listaProyectilJefe.add(new Fire(proyectilJefeFinal,boss.getX() + boss.getWidth() / 2 - proyectilJefeFinal.getWidth() / 2, boss.getY()));
            timerProyectilJefeFinal = 0;
        }
        for (int i = 0; i < listaProyectilJefe.size(); i++) {
            listaProyectilJefe.get(i).moverAbajo();
        }
        for (int i = 0; i < listaProyectilJefe.size(); i++) {
            if (listaProyectilJefe.get(i).getSprite().getY() <= 0) {
                listaProyectilJefe.remove(i);
            }
        }
    }

    private void actualizarVida(float delta){
        timerVida += delta;
        int randomX = random.nextInt((int) ANCHO -texturePotion.getWidth());
        if (timerVida >= 5){
            listaVidas.add(new LifeCharacter(texturePotion, randomX, ALTO));
            timerVida = 0;
        }
        for (int i = 0; i < listaVidas.size(); i++) {
            listaVidas.get(i).mover();
        }
        for (int i = 0; i < listaVidas.size(); i++) {
            if (listaVidas.get(i).getSprite().getY() >= ALTO) {
                listaVidas.remove(i);
            }
        }
    }

    public static int getScore(){
        return finalScore;
    }

    @Override
    public void dispose() {
        stageJuego.dispose();
        stagePausa.dispose();
        stagePerder.dispose();
        stageGanar.dispose();
        assetManager.unload("textures/healthBar.png");
        assetManager.unload("backgrounds/level3.png");
        assetManager.unload("textures/fireBall.png");
        assetManager.unload("textures/ladrillo.png");
        assetManager.unload("textures/arrow.png");
        assetManager.unload("textures/heart.png");
        assetManager.unload("buttons/pause.png");
        assetManager.unload("textures/EnemigoGlobo.png");
        assetManager.unload("frames/dragon.png");
        assetManager.unload("textures/potion.png");
        assetManager.unload("frames/finalBoss3.png");
        assetManager.unload("backgrounds/pause.png");
        assetManager.unload("buttons/resume.png");
        assetManager.unload("buttons/resumePressed.png");
        assetManager.unload("buttons/music.png");
        assetManager.unload("buttons/musicPressed.png");
        assetManager.unload("buttons/sfx.png");
        assetManager.unload("buttons/sfxPressed.png");
        assetManager.unload("buttons/mainMenu.png");
        assetManager.unload("buttons/mainMenuPressed.png");
        assetManager.unload("backgrounds/win.png");
        assetManager.unload("backgrounds/gameOver.png");
        assetManager.unload("buttons/reset.png");
        assetManager.unload("buttons/resetPressed.png");
        assetManager.unload("music/lvl3.mp3");
        assetManager.unload("music/flecha.wav");
        assetManager.unload("music/colision.wav");
        assetManager.unload("music/fuego.wav");
        assetManager.unload("music/pausa.wav");
        assetManager.unload("music/reanudar.wav");
        assetManager.unload("music/impacto.wav");
        assetManager.unload("music/heal.wav");
    }
}