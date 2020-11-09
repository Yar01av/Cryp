package ledgers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SimpleTransaction {
    private final int senderId;
    private final int recipientId;
    private final int amount;
}
