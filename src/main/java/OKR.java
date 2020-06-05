import java.util.Collection;

public class OKR {

    private String objective;
    private Collection<KeyResult> keyResults;
    private boolean goodProgress;
    private ProgressGenerator progressGenerator;

    public OKR(String objective, Collection<KeyResult> keyResults, boolean goodProgress, ProgressGenerator progressGenerator) {
        this.objective = objective;
        this.keyResults = keyResults;
        this.goodProgress = goodProgress;
        this.progressGenerator = progressGenerator;
    }

    public OKR(String objective, Collection<KeyResult> keyResults, ProgressGenerator progressGenerator) {
        this.objective = objective;
        this.keyResults = keyResults;
        this.progressGenerator = progressGenerator;
    }

    public String getObjective() {
        return objective;
    }

    public Collection<KeyResult> getKeyResults() {
        return keyResults;
    }

    public void setGoodProgress(boolean goodProgress) {
        this.goodProgress = goodProgress;
    }

    private boolean canNotCalculateCompletionWithKeyResults() {
        boolean canCalculate = true;

        for (KeyResult keyResult : getKeyResults()) {
            if (keyResult.hasGoodProgress() != null) {
                canCalculate = false;
                break;
            }
        }

        return canCalculate;
    }

    private int calculateCompletionUsingKRs() {
        int sum = 0;

        for (KeyResult keyResult : getKeyResults()) {
            int completion = keyResult.getCompletion();
            sum = sum + completion;
        }

        return sum / getKeyResults().size();
    }

    private int calculateCompletionUsingObjective() {
        int completion;

        if (goodProgress) {
            completion = progressGenerator.completionPercentageInGoodRange(); //this is where random is used
        } else {
            completion = progressGenerator.completionPercentageInBadRange();
        }
        return completion;
    }

    public int calculateOKRCompletion() {
        return canNotCalculateCompletionWithKeyResults() ? calculateCompletionUsingObjective() : calculateCompletionUsingKRs();
    }
}
