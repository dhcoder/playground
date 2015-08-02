package dhcoder.sandbox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import dhcoder.libgdx.input.VirtualThumbstick;
import dhcoder.support.annotations.NotNull;

// TODO: Add Input timer and reset touch point if < episilon for > cutoff seconds

public class MyGdxApp extends ApplicationAdapter {

    public static final String TAG = "SANDBOX";
    private Camera myCamera;
    private ShapeRenderer myShapeRenderer;
    private MyInputHandler myInputProcessor;
    private Vector2 myPos = new Vector2();
    private Vector2 myHeading = new Vector2();
    private Vector2 myVel = new Vector2();

    @Override
    public void create() {
        myCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        myShapeRenderer = new ShapeRenderer();
        myShapeRenderer.setProjectionMatrix(myCamera.combined);

        myInputProcessor = new MyInputHandler();
        Gdx.input.setInputProcessor(myInputProcessor);
    }

    @Override
    public void render() {
        float playerSize = Gdx.graphics.getWidth() * 0.03f;
        float triangleSize = playerSize / 4f;
        float halfTriangleSize = triangleSize / 2f;

        update(Gdx.graphics.getRawDeltaTime());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        myShapeRenderer.begin(ShapeType.Filled);
        myShapeRenderer.identity();
        myShapeRenderer.translate(myPos.x, myPos.y, 0f);
        myShapeRenderer.rotate(0f, 0f, 1f, myHeading.angle());
        myShapeRenderer.setColor(Color.BLUE);
        myShapeRenderer.circle(0, 0, playerSize);
        myShapeRenderer.setColor(Color.RED);
        myShapeRenderer.triangle(playerSize - halfTriangleSize, triangleSize, playerSize, 0, playerSize - halfTriangleSize, -triangleSize);
        myShapeRenderer.identity();
        myShapeRenderer.end();

        myInputProcessor.debugRender(myShapeRenderer);

    }

    private void update(float deltaTime) {
        myInputProcessor.update(deltaTime);
        Vector2 dragged = myInputProcessor.getDragged();
        myVel.setZero();
        if (!dragged.isZero()) {
            myVel.set(dragged);
            myHeading.set(myVel);
        }

        myPos.add(myVel);
    }

    private class MyInputHandler extends InputAdapter {

        private VirtualThumbstick myThumbL;
        private VirtualThumbstick myThumbR;
        private Vector3 myTouch3d = new Vector3();
        private Vector2 myTouch = new Vector2();
        private Vector2 myDragged = new Vector2();

        public MyInputHandler() {
            float thumbSize = Gdx.graphics.getWidth() * 0.05f;

            myThumbL = new VirtualThumbstick(thumbSize);
            myThumbR = new VirtualThumbstick(thumbSize);
        }

        public Vector2 getDragged() {
            return myDragged;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            updateMyTouch(screenX, screenY);
            if (pointer == 0) {
                myThumbL.begin(myTouch);
            }
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            if (pointer == 0) {
                myThumbL.end();
                myDragged.setZero();
            }

            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            updateMyTouch(screenX, screenY);
            if (pointer == 0) {
                myThumbL.drag(myTouch);
                myDragged.set(myThumbL.getDrag()).nor();
            }

            return true;
        }

        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.ESCAPE) {
                Gdx.app.log(TAG, "Quitting");
                Gdx.app.exit();
                return true;
            }

            return false;
        }

        public void update(float deltaTime) {
            if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(0)) {
                return;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                myDragged.x = -1f;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                myDragged.x = 1f;
            }
            else {
                myDragged.x = 0f;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                myDragged.y = 1f;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                myDragged.y = -1f;
            }
            else {
                myDragged.y = 0f;
            }
            myDragged.nor();

        }

        // Call this and then myTouch vec will have screen coordinates
        private void updateMyTouch(int screenX, int screenY) {
            myTouch3d.set(screenX, screenY, 0f);
            myCamera.unproject(myTouch3d);
            myTouch.set(myTouch3d.x, myTouch3d.y);
        }

        public void debugRender(@NotNull ShapeRenderer shapeRenderer) {
            shapeRenderer.setColor(Color.RED);
            myThumbL.debugRender(shapeRenderer);
        }
    }
}
