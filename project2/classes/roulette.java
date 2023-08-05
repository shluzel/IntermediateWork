package project2.classes;

import java.util.*;

public class roulette {
    List<prize> prizesToAward;
    List<recieved> prizesAwarded;

    public roulette() {
        this.prizesToAward = new LinkedList<>();
        this.prizesAwarded = new LinkedList<>();
    }

    public List<prize> getPrizesToAward() {
        return prizesToAward;
    }

    public List<recieved> getPrizesAwarded() {
        return prizesAwarded;
    }

    public void setPrizesToAward(List<prize> prizesToAward) {
        this.prizesToAward = prizesToAward;
    }

    public void setPrizesAwarded(List<recieved> prizesAwarded) {
        this.prizesAwarded = prizesAwarded;
    }
}
