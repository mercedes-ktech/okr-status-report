import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class KeyResultTest {

    @Test
    public void shouldCalculateCompletionForGoodProgress() {
        RandomProgressGenerator randomProgressGenerator = new RandomProgressGenerator();
        KeyResult keyResult = new KeyResult("my key result", true, randomProgressGenerator);

        int progress = keyResult.getCompletion();

        assertTrue(progress > 40 && progress < 100);
    }

    @Test
    public void shouldCalculateCompletionForBadProgress() {
        RandomProgressGenerator randomProgressGenerator = new RandomProgressGenerator();
        KeyResult keyResult = new KeyResult("my key result", false, randomProgressGenerator);

        int progress = keyResult.getCompletion();

        assertTrue(progress > 1 && progress < 39);
    }
}
