package action;

public class MotionAction extends Action
{
    int x_speed;
    int y_speed;


    @Override
    public void performAction ()
    {
        //Call Physics Engine with x and y speed

        System.out.println(myFighter.getName() + " is moving " +
                           Integer.toString(x_speed) +
                           " in the x direction and " +
                           Integer.toString(y_speed) + " in the y direction.");
    }

}
