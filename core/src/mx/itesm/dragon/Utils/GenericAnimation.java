package mx.itesm.dragon.Utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GenericAnimation {

    private Texture textura;
    private TextureRegion[] frames;
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> animacion;
    private int tileWidth, tileHeight, textureRegion;
    private float speed;

    public GenericAnimation(Texture textura, int tileWidth, int tileHeight, int textureRegion, float speed) {
        this.textura = textura;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.textureRegion = textureRegion;
        this.speed = speed;
    }

    public Animation<TextureRegion> animacionHorizontal() {
        TextureRegion[][] tmp = TextureRegion.split(textura, tileWidth, tileHeight);
        frames = new TextureRegion[textureRegion];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = tmp[0][i];
        }
        animacion = new Animation<TextureRegion>(speed, frames);
        return animacion;
    }

    public Animation<TextureRegion> animacionVertical() {
        TextureRegion[][] tmp = TextureRegion.split(textura, tileWidth, tileHeight);
        frames = new TextureRegion[textureRegion];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = tmp[i][0];
        }
        animacion = new Animation<TextureRegion>(speed, frames);
        return animacion;
    }

    public TextureRegion[] getFrames() {
        return frames;
    }
}
