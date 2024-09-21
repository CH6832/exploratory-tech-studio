using GAAPCalcPro.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace GAAPCalcPro.Pages
{
    public class BusinessCombinationModel : PageModel
    {
        public readonly FinancialCalculationsService _service;

        public BusinessCombinationModel()
        {
        }

        public BusinessCombinationModel(FinancialCalculationsService service)
        {
            _service = service;
        }

        [BindProperty]
        public BusinessCombinationModel BusinessCombinationData { get; set; } = new BusinessCombinationModel();
        public decimal Goodwill { get; private set; }
        public decimal ConsiderationTransferred { get; private set; }
        public decimal FairValueOfAssets { get; private set; }
        public decimal FairValueOfLiabilities { get; private set; }

        public void OnGet()
        {
        }

        public void OnPost()
        {
            BusinessCombinationData.Goodwill = _service.CalculateGoodwill(
                BusinessCombinationData.ConsiderationTransferred,
                BusinessCombinationData.FairValueOfAssets,
                BusinessCombinationData.FairValueOfLiabilities
            );
        }
    }
}
