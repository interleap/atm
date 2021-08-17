package com.example.atm;

public class AutoTellerMachine {
    private final BankingService bankingService;
    private final Printer printer;

    public AutoTellerMachine(BankingService bankingService, Printer printer) {
        this.bankingService = bankingService;
        this.printer = printer;
    }

    public void withdraw(int amount){
        bankingService.withdraw(amount);
        printer.print("The amount was withdrawn.");
    }
}
