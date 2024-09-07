using GAAPCalcPro.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace GAAPCalcPro.Pages
{
    public class AROModel : PageModel
    {
        private readonly FinancialCalculationsService _service;
        public decimal AROValue;
        public decimal FutureRetirementCost;
        public decimal DiscountRate;
        public int YearsUntilRetirement;

        public AROModel()
        {
        }

        public AROModel(FinancialCalculationsService service)
        {
            _service = service;
        }

        [BindProperty]
        public AROModel AROData { get; set; } = new AROModel();

        public void OnGet()
        {
        }

        public void OnPost()
        {
            AROData.AROValue = _service.CalculateARO(
                AROData.FutureRetirementCost,
                AROData.DiscountRate,
                AROData.YearsUntilRetirement
            );
        }
    }
}
