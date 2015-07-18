package de.willkowsky.android.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Bosconian extends com.badlogic.gdx.Game implements ApplicationListener {

    public static final int SPEED = 1;
    
    SpriteBatch batchShip;
    
    float elapsed;

    private List<GameObject> theOthers = new ArrayList<GameObject>();

    private GameObject ship;
    private GameObject shot;

    @Override
    public void create() {
        batchShip = new SpriteBatch();
        theOthers.add(new GameObject("enemy1.png", 200, 200));
        theOthers.add(new GameObject("enemy2.png", 260, 200));
       
        ship = new GameObject("ship.png", 250, 250, Direction.S);
        
        Gdx.input.setInputProcessor(new InputMultiplexer());
        ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(new GameInputAdapter());

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batchShip.begin();

        batchShip.draw(ship.texture, ship.xPos, ship.yPos);

        for (GameObject gameObject : theOthers) {
            batchShip.draw(gameObject.texture, gameObject.xPos, gameObject.yPos);
        }

        batchShip.end();

        for (GameObject gameObject : theOthers) {
            moveObject(gameObject, SPEED, ship.direction);
        }

        if (shot != null) {
            moveObject(shot, 3 * SPEED, shot.direction);
        }
        
    }

    private void moveObject(GameObject gameObject, int speed, Direction direction) {
        switch (direction) {
            case N:
                gameObject.yPos -= speed;
                break;
            case S:
                gameObject.yPos += speed;
                break;
            case E:
                gameObject.xPos -= speed;
                break;
            case W:
                gameObject.xPos += speed;
                break;
        }
    }

    class GameInputAdapter extends InputAdapter {
        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.ESCAPE:
                case Input.Keys.Q:
                    System.exit(0);
                case Input.Keys.UP:
                    ship.direction = Direction.N;
                    break;
                case Input.Keys.DOWN:
                    ship.direction = Direction.S;
                    break;
                case Input.Keys.RIGHT:
                    ship.direction = Direction.E;
                    break;
                case Input.Keys.LEFT:
                    ship.direction = Direction.W;
                    break;
                case Input.Keys.SPACE:
                    performShot();
                    break;
            }

            return super.keyDown(keycode);
        }
    }

    private void performShot() {
        if (shot != null) {
            theOthers.remove(shot);
        }
        
        shot = new GameObject("shot.png", ship.xPos, ship.yPos, ship.direction.invert());
        theOthers.add(shot);
    }
}
