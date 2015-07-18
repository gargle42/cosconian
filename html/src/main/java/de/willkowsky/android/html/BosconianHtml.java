package de.willkowsky.android.html;

import de.willkowsky.android.core.Bosconian;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class BosconianHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Bosconian();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
