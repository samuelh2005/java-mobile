package me.samuelh2005.java_mobile.msc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void hasApp() {
        App app = new App();
        assertNotNull(app);
    }
}
