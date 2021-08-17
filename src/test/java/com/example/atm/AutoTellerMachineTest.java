package com.example.atm;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AutoTellerMachineTest {

    @Test
    public void shouldCallBankingServiceToWithdrawAmount() {
        FakePrinter fakePrinter = new FakePrinter();
        FakeBankingService bankingService = new FakeBankingService(false);
        AutoTellerMachine autoTellerMachine = new AutoTellerMachine(bankingService, fakePrinter );

        final Integer expectedValue = 500;
        autoTellerMachine.withdraw(expectedValue);

        assertTrue(bankingService.wasInvoked);
        assertEquals(expectedValue, bankingService.amount.get());
    }

    @Test
    public void shouldCallBankingServiceToWithdrawAmountWithMockito(){
        FakePrinter fakePrinter = new FakePrinter();
        BankingService bankingService = mock(BankingService.class);
        AutoTellerMachine autoTellerMachine = new AutoTellerMachine(bankingService, fakePrinter);

        autoTellerMachine.withdraw(500);

        verify(bankingService, times(7)).withdraw(500);
    }

    @Test
    public void shouldPrintSuccessfulTransactionWhenBankingServiceRespondsSuccessfully(){
        BankingService bankingService = mock(BankingService.class);
        Printer printer = mock(Printer.class);
        AutoTellerMachine atm = new AutoTellerMachine(bankingService, printer);

        doThrow(RuntimeException.class).when(bankingService).withdraw(400);
        atm.withdraw(400);

        verify(printer).print("Rupees 400 successfully withdrawn");

    }

}

