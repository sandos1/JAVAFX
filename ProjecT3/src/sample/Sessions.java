package sample;

public class Sessions {
    private int session_ID;
    private int push_Up;
    private int pull_Up;
    private int performance;

    public Sessions(int session_ID, int Push_Up, int Pull_Up, int performance) {
        this.setSession_ID(session_ID);
        this.setPush_Up(Push_Up);
        this.setPull_Up(Pull_Up);
        this.setPerformance(performance);

    }

    public int getSession_ID() {
        return session_ID;
    }

    public void setSession_ID(int session_ID) {
        this.session_ID = session_ID;
    }

    public int getPush_Up() {
        return push_Up;
    }

    public void setPush_Up(int push_Up) {
        this.push_Up = push_Up;
    }

    public int getPull_Up() {
        return pull_Up;
    }

    public void setPull_Up(int pull_Up) {
        this.pull_Up = pull_Up;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }
}
