<?php
/**
 * file: Order.php
 * reason: Contains orders
 */
class Order
{
    private $order_number;
    private $order_info;
    private $order_items=array();
    private $cxn;
    private $table;

    function __construct(mysqli $cxn,$table)
    {
        $this->cxn=$cxn;
        if(is_string($table))
        {
            $this->table = $table;
        }
        else
        {
            throw new Exception("$table is not a valid table name.");
        }
    }

    function createOrder()
    {
        $today = date("Y-m-d");
        $sql = "INSERT INTO $this->table (bestell_date) VALUES ('$today')";
        if($result = $this->cxn->query($sql))
        {
            $this->bestell_nr = $this->cxn->insert_id;
            $_SESSION['bestell_nr'] = $this->bestell_nr;
        }
        else
        {
            throw new Exception("Database not available. Try again later");
        }
        
    }

    function getOrderNumber()
    {
        return $this->bestell_nr;
    }

    function addCart(ShoppingCart $cart)
    {
        foreach($cart->getAllItems() as $n=>$item)
        {
            $cat_no=$item->getCatalogNumber();
            $quantity=$item->getQuantity();
            $price=$item->getPrice();
            $sql = "INSERT INTO bestell_nr (bestell_nr,katalog_nr,menge,pos_nr,preis) VALUES ($this->katalog_nr,$cat_no,$quantity,($n+1),$price)";
            $result = $this->cxn->query($sql);
        }
    }

    function selectOrder($order_number)
    {
        if(is_int($order_number))
        {
            $this->bestell_nr = $order_number;
        }
        else
        {
            throw new Exception("$order_number not Integer.");
        }
    }

    function getOrderInfo()
    {
        $sql = "SELECT * FROM $this->table WHERE bestell_nr = '$this->bestell_nr'";
        if($result = $this->cxn->query($sql))
        {
            return $result->fetch_assoc();
        }
        else
        {
            throw new Exception("Database not available. Try again later.");
        }
    }

    function getItemInfo()
    {
        $sql = "SELECT pos_nr,katalog_nr,menge,preis FROM bestell_auftrag WHERE bestell_nr = '$this->bestell_nr'";
        if($result = $this->cxn->query($sql))
        {
            $n=1;
            while($row=$result->fetch_assoc())
            {
                foreach($row as $field => $value)
                {
                    $item[$n][$field] = $value;
                }
                $cat = new Catalog("vars.inc");
                $cat->selectCatalog("baumarkt");
                $item[$n]['name'] = $cat->getName($item[$n]['katalog_nr']);
                $n++;
            }
            return $item;
        }
        else
        {
            throw new Exception("Database not available. Try again later.");
        }
    }

    function updateOrderInfo($data)
    {
        if(!is_array($data))
        {
            throw new Exception("Data not available as an array!");
            exit();
        }
        $sql = "UPDATE $this->table SET ";
        foreach($data as $field => $value)
        {
            if(preg_match("ship",$field) || $field == "telefon" || $field == "email")
            {
                $data_array[] = "$field='$value'";
            }
        }
        $sql .= implode($data_array,',');
        $sql .= "WHERE bestell_nr = '$this->bestell_nr'";
        if(!$result = $this->cxn->query($sql))
        {
            throw new Exception("Database not available. Try again later.");
        }
        return true;
    }

    function displayOrderInfo($field_info,$field_page)
    {
        $data = $this->getOrderInfo();
        $items = $this->getItemInfo();
        extract($data);
        if(is_string($field_info) and is_string($field_page))
        {
            include($field_info);
            include($field_page);
        }
        else
        {
            throw new Exception("Parameter are no filenames.");
        }
    }
}
?>