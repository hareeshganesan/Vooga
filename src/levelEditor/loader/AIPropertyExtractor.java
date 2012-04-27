package levelEditor.loader;

import java.lang.reflect.Field;


public class AIPropertyExtractor
{

    public AIPropertyExtractor ()
    {

    }


    public void getAIProperties (Object agent)
    {
        Class agentClass = agent.getClass();
        Field[] agentFields = agentClass.getDeclaredFields();
        for (Field f : agentFields)
        {
            System.out.println(f.getName());
        }

    }


    public static void main (String[] args)
    {
        AITemp ai = new AITemp();

        AIPropertyExtractor a = new AIPropertyExtractor();
        a.getAIProperties(ai);

    }

}
