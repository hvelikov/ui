package bg.comsoft.app.domain;

import io.quarkus.logging.Log;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;


public class RequestLoggingPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        Log.infof("after phase:%s", event.getPhaseId());
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        Log.infof("before phase:%s", event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}