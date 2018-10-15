package pl.oblivion.engine;

public class Timer {
    
    private double lastLoopTime;
    
    public Timer(){
        this.init();
    }
    
    private void init(){
        this.lastLoopTime = getTime();
    }
    
    public double getTime(){
        return System.nanoTime() / 1000_000_000.0;
    }
    
    public float getElapsedTime(){
        double time = getTime();
        float elapsedTime = (float)(time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }
    
    public double getLastLoopTime(){
        return lastLoopTime;
    }
}
