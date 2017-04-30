/** Compound Interest.
@author Alex Dotterweich */

public class CompoundInterest {

    /** Current year. */
    static final int THIS_YEAR = 2014;

    /** Divisor.*/
    static final int ONE_HUNDRED = 100;

    /** Returns the number of years between TARGETYEAR and THIS_YEAR. */
    static int numYears(int targetYear) {
        return targetYear - THIS_YEAR;
    }

    /** Suppose we have an asset worth presentValue that appreciates
    by RATE compounded annually. This method returns the value of that
    asset in the year given by targetYear.

        The rate is given as a percentage return. For example, if the
        PRESENTVALUE is 10, the rate is 12, and the TARGETYEAR is 2016,
        then the futureValue will be 10*1.12*1.12 = 12.544. */
    static double futureValue(double presentValue, double rate,
                        int targetYear) {
        double value = presentValue;
        double change = 1 + rate / ONE_HUNDRED;
        int years = numYears(targetYear);
        for (int i = 0; i < years; i++) {
            value = value * change;
        }
        return value;
    }


    /** Same as futureValue, except that the final amount is converted
    into THIS_YEAR dollars, assuming a simple model where inflation
    compounds annually at a constant rate.

        For example, suppose the PRESENTVALUE is 10, the RATE is 12, the
        TARGETYEAR is 2016, and the INFLATIONRATE is 3.
        In this case, the nominal value is 12.544. If we convert this into
        2014 dollars, we get 12.544 * 0.97 * 0.97 = 11.8026496 dollars */

    static double futureValueReal(double presentValue, double rate,
                            int targetYear, double inflationRate) {
        double nominalValue = futureValue(presentValue, rate, targetYear);
        double change = (ONE_HUNDRED - inflationRate) / ONE_HUNDRED;
        int years = numYears(targetYear);
        nominalValue = nominalValue * Math.pow(change, years);
        return nominalValue;

    }

	/** Suppose you invest perYear dollars at the end of every year until
	    targetYear, with growth compounded annually at rate. This method
	    returns the total value of your savings in targetYear.

	    For example, if perYear is 5000, targetYear is 2016, and rate is 10,
	    then the result will be 5000*1.1*1.1 + 5000*1.1 + 5000 = 16550.*/

    static double totalSavings(double perYear, int targetYear, double rate) {
        double totalValueSavings = 0;
        int years = numYears(targetYear);
        for (int i = 0; i <= years; i++) {
            totalValueSavings += futureValue(perYear, rate, targetYear);
            targetYear--;
        }
        return totalValueSavings;
    }

	/** Same as totalSavingsReal, except given in THIS_YEAR dollars. */

    static double totalSavingsReal(double perYear, int targetYear, double rate,
							   double inflationRate) {
        double totalValueSavingsReal = 0;
        int years = numYears(targetYear);
        for (int i = 0; i <= years; i++) {
            totalValueSavingsReal += futureValueReal(perYear, rate, targetYear,
                                inflationRate);
            targetYear--;
        }
        return totalValueSavingsReal;
    }

	/** Prints out the future value of a dollar in a nice format, assuming
	  * yearly compounded interest at a rate equal to returnRate. */

    static void printDollarFV(int targetYear, double returnRate, double inflationRate) {

        double nominalDollarValue = futureValue(1, returnRate, targetYear);
        double realDollarValue = futureValueReal(1, returnRate, targetYear,
                            inflationRate);

	/* Do not change anything in this method below this line. */

        String dollarSummary = String.format("Assuming a %.2f%% rate of return,"
                            + " a dollar saved today would be worth"
                            + " %.2f dollars in the year %d, or %.2f dollars"
                            + " adjusted for inflation.", returnRate,
                            nominalDollarValue, targetYear, realDollarValue);

        System.out.println(dollarSummary);
    }

    /** Prints out the future value of your total savings in a nice format,
	* assuming yearly compounded interest at a rate equal to returnRate,
	* and an investment of perYear dollars each year. */

    static void printSavingsFV(int targetYear, double returnRate, double inflationRate,
                            double perYear) {
        double nominalSavings = totalSavings(perYear, targetYear, returnRate);
        double realSavings = totalSavingsReal(perYear, targetYear, returnRate,
                            inflationRate);

	/* Do not change anything in this method below this line. */

        String savingsSummary = String.format("Assuming a %.2f%% rate of return,"
			                   + "in the year %d, after saving %.2f"
                               + " dollars per year, you'll have %.2f dollars or"
                               + " %.2f dollars adjusted for inflation.", returnRate,
			                   targetYear, perYear, nominalSavings, realSavings);

        System.out.println(savingsSummary);
    }

    public static void main(String[] args) {
		/* Parameters for the analysis.

		 * targetYear is the year of interest.
		 * returnRate is the percentage rate that you expect to earn on
		      average until targetYear.
		 * inflationRate is the average inflation rate until targetYear
		 * perYear is how much money you will save per year until targetYear */

        int targetYear = 2054;
        double returnRate = 10;
        double inflationRate = 3;
        double perYear = 10000;

		/* Do not modify anything below this line in main. */

        printDollarFV(targetYear, returnRate, inflationRate);
        System.out.println();
        printSavingsFV(targetYear, returnRate, inflationRate, perYear);
    }
}
