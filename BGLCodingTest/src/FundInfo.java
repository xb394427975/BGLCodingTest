import java.util.List;

public class FundInfo {
    List<Membership> memberships;
    double income,expense,taxRate;
    public FundInfo(double income,double expense,double taxRate,List<Membership> memberships) {
        this.income = income;
        this.expense = expense;
        this.taxRate = taxRate;
        this.memberships = memberships;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
