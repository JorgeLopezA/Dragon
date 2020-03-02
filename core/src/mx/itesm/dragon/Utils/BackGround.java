package mx.itesm.dragon.Utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BackGround {
    private Sprite imagenA;
    private Sprite imagenB;

    public BackGround() {
    }

    public BackGround(Texture texture) {
        imagenA = new Sprite(texture);
        imagenA.setPosition(0,0);
        imagenB = new Sprite(texture);
        imagenB.setPosition(0,imagenA.getHeight());
    }

    public BackGround(Texture texturaA, Texture texturaB) {
        imagenA = new Sprite(texturaA);
        imagenA.setPosition(0,0);
        imagenB = new Sprite(texturaB);
        imagenB.setPosition(0,imagenA.getHeight());
    }

    public void mover(float dy) {
        imagenA.setY(imagenA.getY() - dy * 500);
        imagenB.setY(imagenB.getY() - dy * 500);

        // Actualizar posiciones.
        if (imagenA.getY() <= -imagenA.getHeight()) {
            imagenA.setY(imagenB.getY() + imagenB.getHeight());
        } else if (imagenB.getY() <= -imagenB.getHeight()) {
            imagenB.setY(imagenA.getY() + imagenA.getHeight());
        }
    }

    public void render(SpriteBatch batch) {
        imagenA.draw(batch);
        imagenB.draw(batch);
    }

    public void dispose() {
        imagenA.getTexture().dispose();
    }
}
