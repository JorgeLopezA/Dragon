package mx.itesm.dragon.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LifeCharacter {

    private static final float SPEED = 8.58f;
    private int vidas;
    private Sprite sprite;

    public LifeCharacter(Texture textura) {
        vidas = 4;
        sprite = new Sprite(textura);
    }

    public LifeCharacter(Texture texture, int x, float y) {
        vidas = 4;
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
    }

    public void mover() {
        sprite.setY(sprite.getY() - SPEED);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}
