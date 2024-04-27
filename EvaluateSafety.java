/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this 
license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.mycompany.banksinfinancialdistress;
/**
*
* @author Katiana
*/
public class EvaluateSafety {
 private int numberOfLoans = 0, bankID = 0;
 private double loanAmount = 0.0, totalAssets = 0.0;
 private double[][] amountBorrowed;
 private double[] bankTotalAssets, bankBalanceArray;
 /**
 * Evaluates the safety of banks based on predefined parameters.
 *
 * @param numberOfBanks The total number of banks in the system.
 * @param limit The minimum total assets required to deem a bank safe.
 * @return True if any exceptions occur during the evaluation, false
 * otherwise.
 */
 public void EvaluateSafetyBanks(int numberOfBanks, int limit) {
 // Initialize arrays to store bank data
 initializeArray(limit, numberOfBanks);
 try {
 // Gather information about borrowers and loans
 getBorrowers(numberOfBanks);
 // Calculate and display initial total assets
 calculateBankAssets(numberOfBanks, amountBorrowed, bankBalanceArray);
 System.out.println("Initial Total Assets:");
 System.out.println("/".repeat(60));
 printBankTotalAssets(bankTotalAssets, numberOfBanks);
 // Calculate and display current total assets after considering loans
 System.out.println("Current Total Assets: :");
 System.out.println("/".repeat(60));
 checkUnsafeBanks(bankTotalAssets, numberOfBanks, limit);
 printBankTotalAssets(bankTotalAssets, numberOfBanks);
 // Display unsafe banks based on the calculated assets
 printUnsafeBanks(bankTotalAssets, numberOfBanks, limit);
 } catch (NullPointerException e) {
 System.out.printf("%s%s%n", "Null Pointer Exception: in the Evaluate Safety Banks 
Methods.With the following input: ", e.getMessage());
 }
 }
 /**
 * Initializes arrays to store bank-related data based on the specified
 * length.
 *
 * @param limit The limit used in array initialization.
 * @param length The length of the arrays to be initialized.
 */
 public void initializeArray(int limit, int lenght) {
 bankTotalAssets = new double[lenght];
 bankBalanceArray = new double[lenght];
 amountBorrowed = new double[lenght][lenght];
 }
 /**
 * Populates arrays with predefined values representing loans and amounts
 * borrowed for each bank based on a predefined set of borrowers and loan
 * amounts.
 *
 * @param numberOfBanks The total number of banks in the system.
 */
 public void getBorrowers(int numberOfBanks) {
 int[][] borrowers = {{1, 4}, {2, 3}, {0, 3}, {0}, {2}};
 double[][] amounts = {{100.5, 320.5}, {40, 85}, {125, 75}, {125}, {125}};
 for (int lenderID = 0; lenderID < numberOfBanks; lenderID++) {
 System.out.println("*".repeat(60));
 System.out.println("Bank " + lenderID + "'s loan information!!");
 System.out.println("*".repeat(60));
 // Setthing predefined bank balance
 bankBalanceArray[lenderID] = getPredefinedBalance(lenderID);
 System.out.println("Bank's balance: " + bankBalanceArray[lenderID]);
 // Setthing predefined number of loans
 numberOfLoans = borrowers[lenderID].length;
 System.out.println("Bank " + lenderID + " lended money to " + numberOfLoans + " 
banks.");
 // Setthing predefined loan amounts and bank IDs
 for (int loanNumber = 0; loanNumber < numberOfLoans; loanNumber++) {
 bankID = borrowers[lenderID][loanNumber];
 loanAmount = amounts[lenderID][loanNumber];
 System.out.println("Borrower's ID: " + bankID);
 System.out.println("Amount borrowed: " + loanAmount);
 amountBorrowed[lenderID][bankID] = loanAmount; // save loan
 }
 System.out.println();
 }
 }
 /**
 * Retrieves the predefined balance for a given bank ID.
 *
 * @param bankID The unique identifier of the bank.
 * @return The predefined balance for the specified bank.
 */
 private double getPredefinedBalance(int bankID) {
 double[] balances = {25, 125, 175, 75, 181};
 return balances[bankID]; // balance corresponding to the provided bank ID
 }
 /**
 * Calculates the total assets of each bank by considering their initial
 * balance and the amounts borrowed from other banks.
 *
 * @param length The number of banks in the system.
 * @param amountBorrowed 2D array representing the amounts borrowed between
 * banks.
 * @param bankBalanceArray Array representing the initial balance of each
 * bank.
 */
 public void calculateBankAssets(int lenght, double[][] amountBorrowed, double[] 
bankBalanceArray) {
 for (int lender = 0; lender < lenght; lender++) {
 totalAssets = bankBalanceArray[lender]; // Initialize total assets with the initial balance of 
the bank
 for (int borrower = 0; borrower < lenght; borrower++) {
 totalAssets = totalAssets + amountBorrowed[lender][borrower]; // Add the amounts 
borrowed from other banks to the total assets
 }
 bankTotalAssets[lender] = totalAssets;
 }
 }
 /**
 * Identifies unsafe banks by checking if their total assets fall below a
 * specified limit.
 *
 * @param bankTotalAssets Array representing the total assets of each bank.
 * @param length The number of banks in the system.
 * @param limit The minimum total assets required to deem a bank safe.
 */
 public void checkUnsafeBanks(double[] bankTotalAssets, int lenght, int limit) {
 double[] newBankTotalAssets = new double[lenght];
 for (int index = 0; index < lenght; index++) {
 // Check if the total assets of the bank are below the specified limit
 if (bankTotalAssets[index] < limit) {
 do {
 // Save the current total assets to break the loop if it remains unchanged
 newBankTotalAssets = bankTotalAssets;
 // Recalculate total assets by handling unsafe loans
 calculateBankAssets(lenght, handleUnsafeBank(lenght, index), 
bankBalanceArray);
 } while (newBankTotalAssets != bankTotalAssets);
 }
 }
 }
 /**
 * Handles unsafe loans by setting the amounts borrowed from all lenders to
 * a certain borrower to zero.
 *
 * @param length The number of banks in the system.
 * @param borrower The index of the borrower in question.
 * @return The updated 2D array representing amounts borrowed between banks.
 */
 public double[][] handleUnsafeBank(int lenght, int borrower) {
 for (int lender = 0; lender < lenght; lender++) {
 // set the amount borrowed to the specified borrower to zero
 amountBorrowed[lender][borrower] = 0.0;
 }
 return amountBorrowed;
 }
 // Prints the amounts borrowed between banks.
 public void printAmountBorrowed(double[][] amountBorrowed, int lenght) {
 for (int index = 0; index < lenght; index++) {
 for (int index2 = 0; index2 < lenght; index2++) {
 System.out.println("Bank " + index + " offers a loan to Bank " + index2 + " of " + 
amountBorrowed[index][index2] + " milion rands.");
 }
 }
 }
 // Prints the total assets of each bank.
 public void printBankTotalAssets(double[] bankTotalAssets, int lenght) {
 for (int index = 0; index < lenght; index++) {
 System.out.println("Bank " + index + " assets is: " + bankTotalAssets[index] + " million 
rands.");
 }
 System.out.println();
 }
 // Prints the list of unsafe banks after deducting non-recoverable amounts.
 public void printUnsafeBanks(double[] bankTotalAssets, int lenght, int limit) {
 System.out.println("After deducting non-recoverable amounts, the list of vulnerable banks 
is as follows: ");
 for (int index = 0; index < lenght; index++) {
 if (bankTotalAssets[index] < limit) {
 System.out.println("Bank " + index + " with " + bankTotalAssets[index] + " million 
rands.");
 }
 }
 }
}
