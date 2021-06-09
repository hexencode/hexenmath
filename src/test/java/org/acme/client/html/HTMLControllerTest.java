package org.acme.client.html;

import com.dukescript.api.javafx.beans.ActionDataEvent;
import com.dukescript.api.javafx.beans.FXBeanInfo;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTMLControllerTest {

    @Test
    public void hello() {
        HTMLController controller = new HTMLController();
        FXBeanInfo fxBeanInfo = controller.getFXBeanInfo();
        ObservableValue<?> labelText = fxBeanInfo.getProperties().get("labelText");
        assertEquals("", labelText.getValue());
        EventHandler<? super ActionDataEvent> action = fxBeanInfo.getActions().get("action").getValue();
        action.handle(null);
        assertEquals("Hello World!", labelText.getValue());
    }
}