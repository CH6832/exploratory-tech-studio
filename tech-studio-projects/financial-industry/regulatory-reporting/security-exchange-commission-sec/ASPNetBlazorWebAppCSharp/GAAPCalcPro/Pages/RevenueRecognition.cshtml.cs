using GAAPCalcPro.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace GAAPCalcPro.Pages
{
    public class RevenueRecognitionModel : PageModel
    {
        private readonly FinancialCalculationsService _service;

        public RevenueRecognitionModel()
        {
        }

        public RevenueRecognitionModel(FinancialCalculationsService service)
        {
            _service = service;
        }

        [BindProperty]
        public RevenueRecognitionModel RevenueRecognitionData { get; set; } = new RevenueRecognitionModel();
        public decimal RecognizedRevenue { get; private set; }
        public decimal ContractRevenue { get; private set; }
        public int PerformanceObligations { get; private set; }

        public void OnGet()
        {
        }

        public void OnPost()
        {
            RevenueRecognitionData.RecognizedRevenue = _service.CalculateRevenueRecognition(
                RevenueRecognitionData.ContractRevenue,
                RevenueRecognitionData.PerformanceObligations
            );
        }
    }
}
