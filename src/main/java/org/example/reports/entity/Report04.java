package org.example.reports.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Report04 {
    String data;
    String bankCode;
    String branchCode;
    String controlSum;

    String codeAction;
    String numberAccount1;
    String codeClient;
    String codeCurrency;

    String codeType;
    String intervalCode;
    String percentageYear;

    String DateOfEntry;
    String DateOfWithdrawal;
    String closingDate;
    String sanction;
    String minimumAmount;
    String deposit;
    String secureDeposit;
    String numberAccount2;
    String DepositAttraction;
    String DepositDescription;




    @Override
    public String toString() {
        return
                data + "#"+
                        bankCode + "#"+
                        branchCode + "#"+
                        controlSum + "#"+
                        codeAction + "#"+
                        numberAccount1 + "#"+
                        codeClient + "#"+
                        codeCurrency + "#"+
                        codeType + "#"+
                        intervalCode + "#"+
                        percentageYear + "#"+
                        DateOfEntry + "#"+
                        DateOfWithdrawal + "#"+
                        closingDate + "#"+
                        sanction + "#"+
                        minimumAmount + "#"+
                        deposit + "#"+
                        secureDeposit + "#"+
                        numberAccount2 + "#"+
                        DepositAttraction + "#"+
                        DepositDescription + "#" + "\n"
                ;
    }

    public String toString2() {
        return
                data +
                        bankCode +
                        branchCode +
                        codeAction +
                        numberAccount1 +
                        codeClient +
                        codeCurrency +
                        codeType +
                        intervalCode +
                        percentageYear +
                        DateOfEntry +
                        DateOfWithdrawal +
                        closingDate +
                        sanction +
                        minimumAmount +
                        deposit +
                        secureDeposit +
                        numberAccount2 +
                        DepositAttraction +
                        DepositDescription
                ;
    }


    public void controlSum() {
        int checksum = 0;
        String text = this.toString2();
        this.controlSum = controlSum + "\n";
        for (char ch : text.toCharArray()) {
            checksum += (int) ch;
        }

        this.controlSum = String.valueOf(checksum);
    }







}






