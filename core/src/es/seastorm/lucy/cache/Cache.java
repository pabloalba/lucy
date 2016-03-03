package es.seastorm.lucy.cache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import dragongames.base.gameobject.AbstractGameObject;
import dragongames.base.gameobject.SimpleGameObject;

public class Cache {
    // Font
    public static BitmapFont font = new BitmapFont(Gdx.files.internal("font/BitmapFont.fnt"), false);
    public static AbstractGameObject backgroundMenu = new SimpleGameObject("play/bg_menu.jpg");
}
