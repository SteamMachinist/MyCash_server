package tp35.mycashserver.utils;

import lombok.Data;

@Data
public class Pair<T, V> {
    private final T first;
    private final V second;
}
