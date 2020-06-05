public class StubProgressGenerator implements ProgressGenerator {

    @Override
    public int completionPercentageInGoodRange() {
        return 50;
    }

    @Override
    public int completionPercentageInBadRange() {
        return 50;
    }
}
