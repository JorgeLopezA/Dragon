package mx.itesm.dragon.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Fire {

    private static final int TILEWIDTH = 38;
    private static final int TILEHEIGHT = 76;
    private static final int SPEED = 20;
    private static final int SPEED_PROYECTIL = 10;

    private Sprite sprite;
    private TextureRegion[] frames;
    private Animation animacion;
    private float elapsedTime;

    public Fire(Texture textura, float x, float y) {
        sprite = new Sprite(textura);
        sprite.setPosition(x, y);

        //animacion();

    }


    public void render(SpriteBatch batch/*, float delta, float x, float y*/) {
        //elapsedTime += delta;
        //batch.draw((TextureRegion) animacion.getKeyFrame(elapsedTime,true),x,y);
        sprite.draw(batch);
    }

    public void mover() {
        sprite.setY(sprite.getY() + SPEED);
    }

    public void moverAbajo() {
        sprite.setY(sprite.getY() - SPEED_PROYECTIL);
    }

    /*public void animacion() {
        TextureRegion[][] tmp = TextureRegion.split(sprite.getTexture(),TILEWIDTH,TILEHEIGHT);
        frames = new TextureRegion[3];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = tmp[0][i];
        }
        animacion = new GenericAnimation(0.125f,frames);
    }*/

    public Sprite getSprite() {
        return sprite;
    }
}