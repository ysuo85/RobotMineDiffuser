import java.lang.Math;

/**
 * \class Robot
 * \brief A robot class to keep track of the physical state information of the
 * robot.
 *
 * This class is the how the android phone is going to store and access the 
 * state information. The state information will then be used to mark the 
 * nodes on the cartesian grid for the backtracking algorithm.
 */
public class Robot {
    private float[] dist_k;   /*< distance in the x and y directions w.r.t.
                                  the initial position in cm */
    private float[] veloc_k;  /*< velocity of the robot in the x and y 
                                  directions in cm/s */
    private float theta_bot_k;  /*< angle theta w.r.t. the x axis in radians */

    private float omega_bot_k;  /*< angular velocity of the robot in rad/s */

    private float arm_height_k; /*< the height of the arm from the ground in cm */

    private float enc_trust;  /*< how much the data from the encoder is trusted 
                                  on a scale of 0 to 1 */ 
    private float time_k;  /*< last time of update to determine what the 
                              integration timestep should be in microseconds */

    Robot(float init_encoder_trust, float init_arm_height) {
        dist_k = new float[2];
        veloc_k = new float[2];
        theta_bot_k = 0;
        omega_bot_k = 0;
        arm_height_k = init_height; /* TODO: Find initial */
        enc_trust = enc_trust;
        time_k = -1; /* initialize to -1 then changes to actual */
    }

    /* TODO: Figure out how accurate the encoders are */
    public static Robot init_routine(float init_encoder_trust, 
                                     float init_arm_height) {
        Robot mine_bot = new Robot(init_encoder_trust, init_arm_height);
        long test_start = 0;

        /* send initial 

        test_start = System.currentTimeMillis();
        /* initialize sensor inputs for offsets with a 5 second init routine */
        while(System.currentTimeMillis() - test_start < 5000) {

            
        }
        }

    }

    /**
     * \fn parse_json
     * \brief Parses the JSON string that is sent to the android phone.
     * 
     * This function parses the JSON string sent to the android phone and 
     * calls the respective functions to update the states of the system.
     * It takes the JSON string as an argument and returns an integer.
     * @param json_str A JSON string that contains all of the sensor data.
     * @return 0 on success and defined error codes (see Error.java).
     */
    static int parse_json(String json_str) {
        int distance;
        int delta_theta_z;
        int delta_theta_y;

        /* TODO: find format of string and external lib */

        /* compare timestamp and current time to get timestep */

        /* set the new timestamp for the next update*/

        /* parse the json string */
        
        /* store the data in arrays of floats */

        /* call each function with the correct arrays */

        return 0;
    }

    /**
     * \fn update_pos
     * \brief Updates the current position of the robot on the coordinate grid.
     *
     * A function that updates the x_dist and y_dist of the robot on the 
     * coordinate grid. It implements a complementary filter to get a more 
     * accurate estimate. This function takes two floats as an argument and 
     * returns an integer error code.
     * @param dist The distance traveled by the robot since the last update.
     * @param delta_t The time between updates of the robot in microseconds.
     * @return 0 on success and defined error codes (see Error.java).
     */
    int update_pos(float dist, float delta_t) {

        /* TODO: Error check */

        /* complementary filter with encoder distance and previous velocities */
        float x_dist_k1 = dist_k[0] + 
                          (enc_trust * dist * Math.cos(theta_bot_k) + 
                          (1 - enc_trust) * veloc_k[0] * delta_t / 1.0e6;
        float y_dist_k1 = dist_k[1] + 
                          enc_trust * dist * Math.sin(theta_bot_k) +
                          (1 - enc_trust) * veloc_k[1] * delta_t / 1.0e6;

        /* update hidden velocity state estimates */
        veloc_k[0] = (x_dist_k1 - dist_k[0]) / delta_t;
        veloc_k[1] = (y_dist_k1 - dist_k[1]) / delta_t;

        /* update coordinates of the robot */
        dist_k[0] = x_dist_k1;
        dist_k[1] = y_dist_k1;

        return 0;
    }

    /**
     * \fn update_angles
     * \brief updates the angular position of the robot and arm
     *
     * A function that updates the angle of the robot arm and the angle of
     * the robot on the coordinate grid. This function takes an array of
     * floats as an argument and returns an integer.
     * @param omegas An array of floats that contains the x, y, and z angular velocities.
     * @return 0 on success, and other error codes on failure (see Error.java).
     */
    int update_angles(float[] omegas) {

        /* TODO: can't really do this until the gyro/accelerometer is mounted */

        return 0;
    }

    /**
     * \fn test_robot
     * \brief A function meant to test the Robot class
     *
     * This function runs the tests developed to test all of the functionality 
     * of the Robot class. It does not take any arguments and return an int.
     * @return 0 on success, and other error codes on failure (see Error.java).
     */
    int test_robot() {

        /* TODO: This will be done once all the other functions are done */

        return 0;
    }
    
    public static void main(String[] args) {
        int err;

        /* TODO: Test class and error check */

    }
}