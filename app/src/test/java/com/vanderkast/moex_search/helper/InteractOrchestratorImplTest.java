package com.vanderkast.moex_search.helper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;
import java.util.Queue;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class InteractOrchestratorImplTest {
    private MockInteractiveActivity mockActivity;
    private InteractOrchestrator orchestrator = new InteractOrchestratorImpl();
    private Interact mockInteract;


    @Before
    public void setUp() throws Exception {
        mockActivity = new MockInteractiveActivity();
        mockInteract = new MockInteract();
    }

    @Test
    public void requestOnNullActivity() throws NoSuchFieldException, IllegalAccessException {
        orchestrator.request(mockInteract);
        assertEquals(0, mockActivity.interactCount);
        assertEquals(getOrchestratorQueue().poll(), mockInteract);
    }

    @Test
    public void requestWithQueueAndSetActivity() throws NoSuchFieldException, IllegalAccessException {
        orchestrator.request(mockInteract);
        assertEquals(0, mockActivity.interactCount);

        ((InteractOrchestrator.Registrar) orchestrator).bind(mockActivity);
        assertEquals(1, mockActivity.interactCount);

        orchestrator.request(mockInteract);
        assertEquals(2, mockActivity.interactCount);
        assertNull(getOrchestratorQueue().poll());
    }

    @Test
    public void bindWithFilledQueue() throws NoSuchFieldException, IllegalAccessException {
        orchestrator.request(mockInteract);
        ((InteractOrchestrator.Registrar) orchestrator).bind(mockActivity);
        assertEquals(1, mockActivity.interactCount);
    }

    private static class MockInteractiveActivity extends InteractiveActivity {
        int interactCount;

        @Override
        void interact(Interact interact) {
            interactCount++;
        }
    }

    @SuppressWarnings("unchecked")
    private Queue<Interact> getOrchestratorQueue() throws NoSuchFieldException, IllegalAccessException {
        Field field = InteractOrchestratorImpl.class.getDeclaredField("queue");
        field.setAccessible(true);
        return (Queue<Interact>) field.get(orchestrator);
    }
}
