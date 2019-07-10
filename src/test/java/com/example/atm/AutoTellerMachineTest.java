package com.example.atm;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AutoTellerMachineTest {

    @Test
    public void shouldCallBankingServiceToWithdrawAmount() {
        FakeBankingService bankingService = new FakeBankingService(false);
        AutoTellerMachine autoTellerMachine = new AutoTellerMachine(bankingService);

        final Integer expectedValue = 500;
        autoTellerMachine.withdraw(expectedValue);

        assertTrue(bankingService.wasInvoked);
        assertEquals(expectedValue, bankingService.amount.get());
    }

    @Test
    public void shouldCallBankingServiceToWithdrawAmountWithMockito(){
        BankingService bankingService = mock(BankingService.class);
        AutoTellerMachine autoTellerMachine = new AutoTellerMachine(bankingService);

        final Integer expectedValue = 500;
        autoTellerMachine.withdraw(expectedValue);

        verify(bankingService).withdraw(500);
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