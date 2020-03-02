package mx.itesm.dragon.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import mx.itesm.dragon.Utils.GenericAnimation;

public class Character {

    private static final int TILEWIDTH = 384;
    private static final int TILEHEIGHT = 202;
    private static final int TEXTURE_REGION = 8;
    private static final float SPEED = 0.125f;


    private GenericAnimation genericAnimation;

    public Character(Texture texture) {
        genericAnimation = new GenericAnimation(texture, TILEWIDTH, TILEHEIGHT, TEXTURE_REGION, SPEED);
    }

    public Character(Texture texture, int tileWidth, int tileHeight, int textureRegion, float speed) {
        genericAnimation = new GenericAnimation(texture, tileWidth, tileHeight, textureRegion, speed);

    }
    public Animation animacion() {
        return genericAnimation.animacionHorizontal();
    }

    public TextureRegion[] getFrames() {
        return genericAnimation.getFrames();
    }
}