package expression;

public interface Priority {
    default int getLeftPriorityOperator() {
        return Integer.MAX_VALUE;
    }

    default int getRightPriorityOperator() {
        return Integer.MAX_VALUE;
    }

    default int getPriorityOperator() {
        return Integer.MAX_VALUE;
    }
}
