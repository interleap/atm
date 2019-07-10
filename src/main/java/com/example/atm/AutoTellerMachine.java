package com.example.atm;

public class AutoTellerMachine {
    private final BankingService bankingService;

    public AutoTellerMachine(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    public void withdraw(int amount){
        bankingService.withdraw(amount);
        printer.print("The amount was withdrawn.")
    }
}
