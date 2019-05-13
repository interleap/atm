package com.example.atm;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class AutoTellerMachineTest {

    @Test
    public void shouldCallBankingServiceToWithdrawAmount() {
        FakeBankingService bankingService = new FakeBankingService(true);
        AutoTellerMachine autoTellerMachine = new AutoTellerMachine(bankingService);

        final Integer expectedValue = 500;
        autoTellerMachine.withdraw(expectedValue);

        assertTrue(bankingService.wasInvoked);
        assertEquals(expectedValue, bankingService.amount.get());
    }
}

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