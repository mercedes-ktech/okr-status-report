import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;


public class OKRTest {

    @Test
    public void shouldCalculateCompletionForGoodProgress() {
        //fixture
        RandomProgressGenerator randomProgressGenerator = new RandomProgressGenerator();
        KeyResult keyResult = new KeyResult("my key result", randomProgressGenerator);
        Collection<KeyResult> keyResults = new ArrayList<>();
        keyResults.add(keyResult);
        OKR okr = new OKR("my objective", keyResults, true, randomProgressGenerator);

        //action
        int progress = okr.calculateOKRCompletion();

        //assertion
        assertTrue(progress > 40 && progress < 100);
    }

    @Test
    public void shouldCalculateCompletionForBadProgress() {
        //fixture
        RandomProgressGenerator randomProgressGenerator = new RandomProgressGenerator();
        KeyResult keyResult = new KeyResult("my key result", randomProgressGenerator);
        Collection<KeyResult> keyResults = new ArrayList<>();
        keyResults.add(keyResult);
        OKR okr = new OKR("my objective", keyResults, false, randomProgressGenerator);

        //action
        int progress = okr.calculateOKRCompletion();

        //assertion
        assertTrue(progress > 1 && progress < 39);
    }

//    @Test - THIS SCENARIO DOESN'T EXIST ANYMORE
//    public void shouldNotCalculateCompletionIfKeyResultHasProgress() {
//        //fixture
//        KeyResult keyResult = new KeyResult("my key result", false);
//        Collection<KeyResult> keyResults = new ArrayList<>();
//        keyResults.add(keyResult);
//        OKR okr = new OKR("my objective", keyResults, false);
//
//        //action
//        int progress = okr.calculateCompletion();
//
//        //assertion
//        assertEquals(0, progress);
//    }

    @Test
    public void shouldReturnAverageOKRCompletionBasedOnKeyResultsCompletion() {
        //i want to test that: if KR1 completion is 50
        //and KR2 completion is 10
        //when i calculate OKR completion
        //OKR completion is 30

        //fixture
        ProgressGenerator stubProgressGenerator = new StubProgressGenerator();
        KeyResult keyResult1 = new KeyResult("my key result", false, stubProgressGenerator);
        KeyResult keyResult2 = new KeyResult("my key result", true, stubProgressGenerator);

        Collection<KeyResult> keyResults = new ArrayList<>();
        keyResults.add(keyResult1);
        keyResults.add(keyResult2);
        OKR okr = new OKR("my objective", keyResults, stubProgressGenerator);

        //action
        int progress = okr.calculateOKRCompletion();

        //assertion
        assertEquals(50, progress); //improve this assertion. also i don't think calculateCompletionUsingKRs is actually returning the average
    }
}
