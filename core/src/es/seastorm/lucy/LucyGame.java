package es.seastorm.lucy;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;

import java.util.Random;

import es.seastorm.lucy.screens.history.HistoryScreen;
import es.seastorm.lucy.screens.menu.MenuScreen;
import es.seastorm.lucy.assets.GameAssets;
import es.seastorm.lucy.screens.play.PlayScreen;


public class LucyGame extends Game {
	private MenuScreen menuScreen = null;
	private PlayScreen playScreen = null;

	public boolean music = true;
	public boolean sound = true;
	public boolean easy = false;
	public int maxLevel = 1;


	public MenuScreen getMenuScreen() {
		if (menuScreen == null){
			menuScreen = new MenuScreen(this);
		}
		return menuScreen;
	}


	public PlayScreen getPlayScreen() {
		if (playScreen == null){
			playScreen = new PlayScreen(this);
		}
		return playScreen;
	}


	@Override
	public void create() {
		// Set Libgdx log level
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		// Load rest of assets
		GameAssets.instance.init(new AssetManager());

		// Start game at splash screen
		loadMenuScreen();
	}


	public void loadMenuScreen(){
		playScreen = null;
		setScreen(getMenuScreen());
	}

	public void loadHistoryScreen(){
		playScreen = null;
		setScreen(new HistoryScreen(this));
	}

	public void loadPlayScreen(int level){
		boolean crumble = ! easy;
		switch (level){
			case 0:
				Random rnd = new Random();
				String theme = getPlayScreen().THEMES[rnd.nextInt(3)];
				getPlayScreen().setLevelSettings(theme, true, false, -1, 0);
				break;
			case 1:
				getPlayScreen().setLevelSettings(getPlayScreen().THEMES[0], false, false, 8192, 1);
				break;
			case 2:
				getPlayScreen().setLevelSettings(getPlayScreen().THEMES[1], false, crumble, 12288, 2);
				break;
			case 3:
				getPlayScreen().setLevelSettings(getPlayScreen().THEMES[2], true, false, 15250, 3);
				break;
			case 4:
				getPlayScreen().setLevelSettings(getPlayScreen().THEMES[3], true, crumble, 20480, 4);
				break;
		}

		setScreen(getPlayScreen());
	}

	public void setMaxLevel(int maxLevel){
		this.maxLevel = maxLevel;
		Preferences prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
		prefs.putInteger("level", maxLevel);
		prefs.flush();
	}
}
