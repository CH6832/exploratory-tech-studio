using GAAPCalcPro.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace GAAPCalcPro.Pages
{
    public class DeferredRevenueModel : PageModel
    {
        private readonly FinancialCalculationsService _service;

        public DeferredRevenueModel()
        {
        }

        public DeferredRevenueModel(FinancialCalculationsService service)
        {
            _service = service;
        }

        [BindProperty]
        public DeferredRevenueModel DeferredRevenueData { get; set; } = new DeferredRevenueModel();
        public decimal DeferredRevenue { get; private set; }
        public decimal TotalContractValue { get; private set; }
        public int NumberOfPeriods { get; private set; }
        public int PeriodsElapsed { get; private set; }

        public void OnGet()
        {
        }

        public void OnPost()
        {
            DeferredRevenueData.DeferredRevenue = _service.CalculateDeferredRevenue(
                DeferredRevenueData.TotalContractValue,
                DeferredRevenueData.NumberOfPeriods,
                DeferredRevenueData.PeriodsElapsed
            );
        }
    }
}

