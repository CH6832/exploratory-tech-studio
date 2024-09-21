using GAAPCalcPro.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace GAAPCalcPro.Pages
{
    public class StockCompensationModel : PageModel
    {
        public readonly FinancialCalculationsService _service;
        public double OptionValue;
        public double StockPrice;
        public decimal StrikePrice;
        public decimal Volatility;
        public decimal TimeToMaturity;
        public decimal RiskFreeRate;

        public StockCompensationModel()
        {
        }

        public StockCompensationModel(FinancialCalculationsService service)
        {
            _service = service;
        }

        [BindProperty]
        public StockCompensationModel StockCompensationData { get; set; } = new StockCompensationModel();

        public void OnGet()
        {
        }

        public void OnPost()
        {
            StockCompensationData.OptionValue = _service.CalculateOptionValue(
                StockCompensationData.StockPrice,
                StockCompensationData.StrikePrice,
                StockCompensationData.Volatility,
                StockCompensationData.TimeToMaturity,
                StockCompensationData.RiskFreeRate
            );
        }
    }
}
