package mx.itesm.dragon.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {

    private Sprite sprite;
    private static final int SPEED = 10;

    public Enemy() {
        sprite = new Sprite();
    }

    public Enemy(Texture textura) {
        sprite = new Sprite(textura);
    }

    public Enemy(Texture textura, float x, float y) {
        sprite = new Sprite(textura);
        sprite.setPosition(x,y);
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void mover() {
        sprite.setY(sprite.getY() - SPEED);
    }

    public void moverX(){
        sprite.setX(sprite.getX() - SPEED / 5);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
