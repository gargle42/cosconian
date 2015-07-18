package de.willkowsky.android.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by weg on 18.07.15.
 */
public class GameObject {
    public Texture texture;
    public Direction direction = null;
    public int xPos;
    public int yPos;

    public GameObject(String fileName, int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        texture = new Texture(Gdx.files.internal(fileName));

    }

    public GameObject(String fileName, int xPos, int yPos, Direction direction) {
        this(fileName, xPos, yPos);
        this.direction = direction;
    }
}
