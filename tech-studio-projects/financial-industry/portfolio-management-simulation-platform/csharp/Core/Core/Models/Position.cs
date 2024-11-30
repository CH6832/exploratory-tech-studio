public class Position
{
    public string Asset { get; set; }  // Maps to AssetName in the original code
    public int Quantity { get; set; }
    public decimal CurrentPrice { get; set; }
    public decimal TotalValue => Quantity * CurrentPrice;
    public decimal ProfitLoss { get; set; }  // Assume this is calculated or stored
}
