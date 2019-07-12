package line.tip.extractor.shared;

import line.tip.utils.AflGameProvider;

public abstract class AflRound {
    protected AflGameProvider provider;
    public abstract void loadAflRound();
}
