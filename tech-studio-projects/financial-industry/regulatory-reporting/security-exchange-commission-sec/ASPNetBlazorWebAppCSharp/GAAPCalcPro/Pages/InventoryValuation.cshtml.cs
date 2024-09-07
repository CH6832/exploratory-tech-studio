using GAAPCalcPro.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace GAAPCalcPro.Pages
{
    public class InventoryValuationModel : PageModel
    {
        private readonly FinancialCalculationsService _service;

        public InventoryValuationModel()
        {
        }

        public InventoryValuationModel(FinancialCalculationsService service)
        {
            _service = service;
        }

        [BindProperty]
        public InventoryValuationModel InventoryValuationData { get; set; } = new InventoryValuationModel();
        public decimal InventoryValue { get; private set; }
        public decimal CostOfGoodsSold { get; private set; }
        public decimal EndingInventory { get; private set; }

        public void OnGet()
        {
        }

        public void OnPost()
        {
            InventoryValuationData.InventoryValue = _service.CalculateInventoryValue(
                InventoryValuationData.CostOfGoodsSold,
                InventoryValuationData.EndingInventory
            );
        }
    }
}
