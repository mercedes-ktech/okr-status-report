import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;


public class OKRTest {

    @Test
    public void shouldCalculateCompletionForGoodProgress() {
        RandomProgressGenerator randomProgressGenerator = new RandomProgressGenerator();
        KeyResult keyResult = new KeyResult("my key result", randomProgressGenerator);
        Collection<KeyResult> keyResults = new ArrayList<>();
        keyResults.add(keyResult);
        OKR okr = new OKR("my objective", keyResults, true, randomProgressGenerator);

        int progress = okr.calculateOKRCompletion();

        assertTrue(progress > 40 && progress < 100);
    }

    @Test
    public void shouldCalculateCompletionForBadProgress() {
        RandomProgressGenerator randomProgressGenerator = new RandomProgressGenerator();
        KeyResult keyResult = new KeyResult("my key result", randomProgressGenerator);
        Collection<KeyResult> keyResults = new ArrayList<>();
        keyResults.add(keyResult);
        OKR okr = new OKR("my objective", keyResults, false, randomProgressGenerator);

        int progress = okr.calculateOKRCompletion();

        assertTrue(progress > 1 && progress < 39);
    }

    @Test
    public void shouldReturnAverageOKRCompletionBasedOnKeyResultsCompletion() {
        ProgressGenerator stubProgressGenerator = new StubProgressGenerator();
        KeyResult keyResult1 = new KeyResult("my key result", false, stubProgressGenerator);
        KeyResult keyResult2 = new KeyResult("my key result", true, stubProgressGenerator);

        Collection<KeyResult> keyResults = new ArrayList<>();
        keyResults.add(keyResult1);
        keyResults.add(keyResult2);
        OKR okr = new OKR("my objective", keyResults, stubProgressGenerator);

        int progress = okr.calculateOKRCompletion();

        assertEquals(50, progress);
    }
}
