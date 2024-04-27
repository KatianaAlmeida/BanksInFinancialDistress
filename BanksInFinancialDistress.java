/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this 
license
*/
package com.mycompany.banksinfinancialdistress;
/**
*
* @author Katiana
*/
public class BanksInFinancialDistress {
 EvaluateSafety evaluate = new EvaluateSafety();
 private int numberOfBanks = 5, limitForKeepingBankSafe = 201;
 public static void main(String[] args) {
 BanksInFinancialDistress objectReference = new BanksInFinancialDistress();
 System.out.println("*".repeat(60));
 System.out.println("Welcome to the Banks In Financial Distress program!!");
 System.out.println("*".repeat(60));
 objectReference.startTheProgram();
 }
 public void startTheProgram() {
 // Display the number of banks and minimum total assets for safety
 System.out.printf("%s%d%n%5s%d%n%n", "The number of banks is: ", numberOfBanks, 
"The the minimum total assets for keeping a bank safe is: ", limitForKeepingBankSafe);
 // Evaluate the safety of banks and check for exceptions
 evaluate.EvaluateSafetyBanks(numberOfBanks, limitForKeepingBankSafe);
 }
}
