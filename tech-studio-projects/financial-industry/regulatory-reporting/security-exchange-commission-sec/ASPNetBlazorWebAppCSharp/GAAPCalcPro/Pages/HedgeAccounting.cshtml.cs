using GAAPCalcPro.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace GAAPCalcPro.Pages
{
    public class HedgeAccountingModel : PageModel
    {
        private readonly FinancialCalculationsService _service;

        public HedgeAccountingModel()
        {
        }

        public HedgeAccountingModel(FinancialCalculationsService service)
        {
            _service = service;
        }

        [BindProperty]
        public HedgeAccountingModel HedgeAccountingData { get; set; } = new HedgeAccountingModel();
        public decimal Effectiveness { get; private set; }
        public decimal HedgedItemFairValue { get; private set; }
        public decimal HedgingInstrumentFairValue { get; private set; }

        public void OnGet()
        {
        }

        public void OnPost()
        {
            HedgeAccountingData.Effectiveness = _service.CalculateHedgeEffectiveness(
                HedgeAccountingData.HedgedItemFairValue,
                HedgeAccountingData.HedgingInstrumentFairValue
            );
        }
    }
}
