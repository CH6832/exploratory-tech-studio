using GAAPCalcPro.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace GAAPCalcPro.Pages
{
    public class LeaseClassificationModel : PageModel
    {
        private readonly FinancialCalculationsService _service;

        public LeaseClassificationModel()
        {
        }

        public LeaseClassificationModel(FinancialCalculationsService service)
        {
            _service = service;
        }

        [BindProperty]
        public LeaseClassificationModel LeaseClassificationData { get; set; } = new LeaseClassificationModel();
        public string LeaseClassification { get; private set; }
        public int LeaseTerm { get; private set; }
        public decimal PresentValueOfPayments { get; private set; }
        public decimal DiscountRate { get; private set; }

        public void OnGet()
        {
        }

        public void OnPost()
        {
            LeaseClassificationData.LeaseClassification = _service.DetermineLeaseClassification(
                LeaseClassificationData.LeaseTerm,
                LeaseClassificationData.PresentValueOfPayments,
                LeaseClassificationData.DiscountRate
            );
        }
    }
}
