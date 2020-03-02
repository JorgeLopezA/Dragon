package mx.itesm.dragon.Utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedImage extends Image {

    protected Animation animation = null;
    protected TextureRegion[] frames;
    private float stateTime = 0;

    public AnimatedImage(Animation animation) {
        super((TextureRegion) animation.getKeyFrame(.1f,true));
        this.animation = animation;

    }

    public AnimatedImage(Animation animation, TextureRegion[] frames) {
        super((TextureRegion) animation.getKeyFrame(.1f,true));
        this.animation = animation;
        this.frames = frames;
    }

    @Override
    public void act(float delta) {
        ((TextureRegionDrawable)getDrawable()).setRegion((TextureRegion) animation.getKeyFrame(stateTime+=delta, true));
        super.act(delta);
    }

    public TextureRegion[] getFrames() {
        return frames;
    }

    public Animation getAnimation() {
        return animation;
    }

    public TextureRegion get(int i) {
        for (int j = 0; j < frames.length; j++){
            if (i == j) {
                return frames[i];
            }
        }
        return null;
    }

}
