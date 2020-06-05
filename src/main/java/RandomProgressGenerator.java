public class RandomProgressGenerator implements ProgressGenerator {
    @Override
    public int completionPercentageInGoodRange() {
        int goodRange = (100 - 40);
        return (int) (Math.random() * goodRange) + 40;
    }

    @Override
    public int completionPercentageInBadRange() {
        int badRange = (39 - 1);
        return (int) (Math.random() * badRange) + 1;
    }
}
