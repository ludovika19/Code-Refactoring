package Esperimento2.Claude.smell13Refactored.classForRefactorSWST;


public enum TransactionType {
    WIRE {
        @Override
        public double getFee() {
            return 25.00;
        }
    },
    ATM {
        @Override
        public double getFee() {
            return 2.50;
        }
    },
    TRANSFER {
        @Override
        public double getFee() {
            return 0.00;
        }
    },
    CHECK {
        @Override
        public double getFee() {
            return 1.00;
        }
    },
    UNKNOWN {
        @Override
        public double getFee() {
            return 5.00;
        }
    };

    public abstract double getFee();

    public static TransactionType fromString(String type) {
        if (type == null) {
            return UNKNOWN;
        }
        try {
            return TransactionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}