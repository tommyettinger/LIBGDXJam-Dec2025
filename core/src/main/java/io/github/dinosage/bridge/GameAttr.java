package io.github.dinosage.bridge;

public class GameAttr {

    public static float SCORE_MULTIPLIER = 100f;
    // Bridge Attributes
    public static int START_PLANK_COUNT = 100;


    // Obstacle Attributes
    public static float OBSTACLE_WIDTH = 3f;
    public static float OBSTACLE_HEIGHT = 3f;
    public static float OBSTACLE_VEL_X = -2f;
    public static float OBSTACLE_INTERVAL = 5f;

    // Plank Attributes
    public static float PLANK_WIDTH = 1f;
    public static float PLANK_HEIGHT = 1f;
    public static float PLANK_VEL_X = -2f;
    public static float PLANK_INTERVAL = 2f;
    public static int PLANK_POWER_BOOST = 10;

    // Bridge Attributes
    public static float BRIDGE_WIDTH = 3f;
    public static float BRIDGE_HEIGHT = 0.5f;
    public static float BRIDGE_VEL_X = -10f;

    // Misc Attributes
    public static float SHAPE_BORDER = 0.1f;
    public static float OOB_BUFFER = 5f; // out of bounds buffer
    public static boolean DEBUG_UI = false;
}
