package main;

import java.net.URL;

/**
 *
 * @author avile
 */
public class SourceSound
{

    private boolean priority, toLoop, type;
    private String sourceName, identifier;
    private URL url;
    private float x, y, z;

    public SourceSound(boolean priority, URL url, String sourceName, String identifier, boolean toLoop, float x, float y, float z, boolean type)
    {
        this.priority = priority;
        this.toLoop = toLoop;
        this.sourceName = sourceName;
        this.identifier = identifier;
        this.url = url;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
    }

    public boolean getType(){
        return type;
    }
    
    public boolean isPriority()
    {
        return priority;
    }

    public void setPriority(boolean priority)
    {
        this.priority = priority;
    }

    public boolean isToLoop()
    {
        return toLoop;
    }

    public void setToLoop(boolean toLoop)
    {
        this.toLoop = toLoop;
    }

    public String getSourceName()
    {
        return sourceName;
    }

    public void setSourceName(String sourceName)
    {
        this.sourceName = sourceName;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public URL getUrl()
    {
        return url;
    }

    public void setUrl(URL url)
    {
        this.url = url;
    }

    public void setPoint3D(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public float[] getPoint3D(){
        float[] values = new float[3];
        values[0] = x;
        values[1] = y;
        values[2] = z;
        return values;
    }
    
}
