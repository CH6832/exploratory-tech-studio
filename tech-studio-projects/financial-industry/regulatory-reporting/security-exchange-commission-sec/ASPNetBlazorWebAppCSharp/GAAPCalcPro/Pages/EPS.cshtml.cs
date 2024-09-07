using GAAPCalcPro.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace GAAPCalcPro.Pages
{
    public class EPSModel : PageModel
    {
        private readonly FinancialCalculationsService _service;

        public EPSModel()
        {
        }

        public EPSModel(FinancialCalculationsService service)
        {
            _service = service;
        }

        [BindProperty]
        public EPSModel EPSData { get; set; } = new EPSModel();
        public decimal EPS { get; private set; }
        public decimal NetIncome { get; private set; }
        public int NumberOfShares { get; private set; }

        public void OnGet()
        {
        }

        public void OnPost()
        {
            EPSData.EPS = _service.CalculateEPS(
                EPSData.NetIncome,
                EPSData.NumberOfShares
            );
        }
    }
}
