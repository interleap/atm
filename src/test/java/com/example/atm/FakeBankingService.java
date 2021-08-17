package com.example.atm;

import java.util.Optional;

class FakeBankingService extends BankingService {
    private final boolean failWithdraw;
    boolean wasInvoked = false;
    Optional<Integer> amount = Optional.empty();

    public FakeBankingService(boolean failWithdraw) {
        this.failWithdraw = failWithdraw;
    }

    @Override
    public void withdraw(int amount) {
        wasInvoked = true;

        if (failWithdraw) {
            throw new RuntimeException();
        } else {
            this.amount = Optional.of(amount);
        }
    }
}
