package es.seastorm.lucy.gameobjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dragongames.base.gameobject.AbstractGameObject;

public class SimpleTextGameObject extends AbstractGameObject{
    BitmapFont font;
    String text = "";

    public void setText(String text) {
        this.text = text;
    }


    public SimpleTextGameObject(BitmapFont font){
        this.font = font;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.draw(batch, text, position.x, position.y);
    }
}
