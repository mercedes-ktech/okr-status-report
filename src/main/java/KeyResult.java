public class KeyResult {

    private String keyResult;
    private Boolean goodProgress;
    private Integer completion;
    private ProgressGenerator progressGenerator;

    public KeyResult(String keyResult, ProgressGenerator progressGenerator) {
        this.keyResult = keyResult;
        this.progressGenerator = progressGenerator;
    }

    public KeyResult(String keyResult, Boolean goodProgress, ProgressGenerator progressGenerator) {
        this.keyResult = keyResult;
        this.goodProgress = goodProgress;
        this.progressGenerator = progressGenerator;
    }

    public String getKeyResult() {
        return keyResult;
    }

    public Boolean hasGoodProgress() {
        return goodProgress;
    }

    public int getCompletion() {
        if (completion == null) {
            completion = calculateCompletion();
        }
        return completion;
    }

    public void setGoodProgress(Boolean goodProgress) {
        this.goodProgress = goodProgress;
    }

    private int calculateCompletion() {
        int completion = 0;

        if (goodProgress) {
            completion = progressGenerator.completionPercentageInGoodRange();
        } else if (!goodProgress) {
            completion = progressGenerator.completionPercentageInBadRange();
        }

        return completion;
    }
}
