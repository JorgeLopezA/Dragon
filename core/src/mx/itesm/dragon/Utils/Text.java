package mx.itesm.dragon.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {

    private BitmapFont font;
    private GlyphLayout glyph;
    private String mensaje;

    public Text(){
        font = new BitmapFont(Gdx.files.internal("fonts/fuenteScore.fnt"));
        glyph = new GlyphLayout();

    }

    public Text(String fuente){
        font = new BitmapFont(Gdx.files.internal(fuente));
        glyph = new GlyphLayout();
    }

    public void mostrarMensaje(SpriteBatch batch, String mensaje, float x, float y){
        glyph.setText(font, mensaje);
        this.mensaje = mensaje;
        font.draw(batch, glyph, x - glyph.width / 2, y);

    }



    public BitmapFont getFont() {
        return font;
    }

    public GlyphLayout getGlyph() {
        return glyph;
    }


}
