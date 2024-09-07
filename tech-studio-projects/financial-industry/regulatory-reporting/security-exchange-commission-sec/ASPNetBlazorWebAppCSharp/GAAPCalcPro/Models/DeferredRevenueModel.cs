namespace GAAPCalcPro.Services
{
    public class DeferredRevenueModel
    {
        public double TotalContractValue { get; set; }
        public int NumberOfPeriods { get; set; }
        public int PeriodsElapsed { get; set; }
        public double DeferredRevenue { get; set; }
    }
}
