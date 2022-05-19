import java.util.*;
/*Assumption
name of members is unique.
income greater than expense
the sum of proportion for each member is 100%
*/
public class CalculationService {

    public static void main(String[] args) {
        List<Membership> memberships = new LinkedList<Membership>();
        memberships.add(new Membership("Bob", 60));
        memberships.add(new Membership("Lilian", 30));
        memberships.add(new Membership("Rob", 10,true));
        FundInfo f = new FundInfo(100, 10, 10, memberships);

        fundAnalysis(f);
    }

    private static double calProfit(double income, double expense){
        return income - expense;
    }

    private static double calTaxPayable(double income, double expense, double taxRate){
        return  Math.round(calProfit(income, expense) * taxRate / 100.0);
    }

    public static void fundAnalysis(FundInfo fundInfo){
        double income = fundInfo.getIncome();
        double expense = fundInfo.getExpense();
        double taxRate = fundInfo.getTaxRate();
        //calculate profit
        double profit = calProfit(income,expense);

        //calculate payable tax
        double taxPayable = calTaxPayable(income,expense,taxRate);

        //calculate profit for each member
        List<Membership> memberships = fundInfo.getMemberships();
        Collections.sort(memberships);
        HashMap<String,Double> profitMap = new HashMap<>();
        HashMap<String,Double> proportionMap = new HashMap<>();
        String largestProportionMember = memberships.get(0).getName();
        double profitSum = 0;
        double freeTaxProportion = 0;
        for(Membership m : memberships){
            double proportion = m.getProportion();
           double memberProfit =  Math.round(proportion * profit / 100);
            profitSum = profitSum + memberProfit;
            profitMap.put(m.getName(),Double.valueOf(memberProfit));
            if(!m.isPension()){
                proportionMap.put(m.getName(),Double.valueOf(proportion));
            }else
               freeTaxProportion += proportion;
        }

        //calculate tax proportion for each member
        for (Map.Entry mapElement : proportionMap.entrySet()) {
            double value = ((double)mapElement.getValue()/(100-freeTaxProportion)*100);
            mapElement.setValue(value);
        }

        Double largestProfit = profitMap.get(largestProportionMember) + profit - profitSum;
        profitMap.put(largestProportionMember, largestProfit);
        HashMap<String,Double> profitMapSorted  = sortByValue(profitMap);

        System.out.printf("profit = %.2f - %.2f = %.2f\n", income,expense,profit);
        System.out.printf("income tax payable = %.2f x %.2f%% = %.2f\n", profit,taxRate,taxPayable);
        System.out.println("income tax payable : " + taxPayable);
        profitMapSorted.forEach((k,v)->System.out.print(k+"'s profit = "+v+"; "));
        System.out.println();
        proportionMap.forEach((k,v)->System.out.printf("%s's tax proportion = %.2f%%.\n",k,v));
        proportionMap.forEach((k,v)-> System.out.printf("%s's tax allocation = %d; ",k,Math.round(v*taxPayable/100)));
        System.out.println();
    }

    private static HashMap<String, Double> sortByValue(HashMap<String, Double> hm)
    {

        List<Map.Entry<String, Double> > list = new LinkedList<>(hm.entrySet());


        Collections.sort(list, (i1, i2) -> i2.getValue().compareTo(i1.getValue()));


        HashMap<String, Double> temp
                = new LinkedHashMap<>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
