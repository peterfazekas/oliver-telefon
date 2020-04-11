package hu.callcenter.domain.model;

public class CallLog {

    private final int id;
    private final LogTime callBegin;
    private final LogTime speakBegin;
    private final LogTime callEnd;

    public CallLog(int id, LogTime callBegin, LogTime speakBegin, LogTime callEnd) {
        this.id = id;
        this.callBegin = callBegin;
        this.speakBegin = speakBegin;
        this.callEnd = callEnd;
    }

    public int getId() {
        return id;
    }

    public int getCallHour() {
        return callBegin.getHour();
    }

    public LogTime getCallBegin() {
        return callBegin;
    }

    public Integer getCallLength() {
        return LogTime.diff(callBegin, callEnd);
    }

    public boolean couldSpeak() {
        return speakBegin != null;
    }

    public boolean isSpeaking(LogTime time) {
        return couldSpeak() && time.between(speakBegin, callEnd);
    }

    public boolean isWaiting(LogTime time) {
        return !isSpeaking(time) && time.between(callBegin, callEnd);
    }

    public int getWaitingTime() {
        return couldSpeak() ? LogTime.diff(callBegin, speakBegin) : -1;
    }

    @Override
    public String toString() {
        return id + " " + speakBegin + " " + callEnd;
    }
}
