<?php
/**
 * file: ShoppingCart.php
 * reason: Creates a shopping cart (structure with articles)
 */
class ShoppingCart
{
    private $items = array();
    private $message;
    private $n_items=0;

    function __construct()
    {
        if(isset($_SESSION['items']))
        {
            $this->items=$_SESSION['items'];
            $this->n_items=sizeof($this->items);
        }
        $this->message="Shopping cart contains
                                    {$this->n_items} item.";
    }

    function addItem(Item $item)
    {
        $this->items[]=$item;
        $_SESSION['items']=$this->items;
        $this->n_items++;
        $this->message="Shopping card contains {$this->n_items} items.";
    }

    function getAllItems()
    {
        return $this->items;
    }

    function getMessage()
    {
        return $this->message;
    }

    function displayCart($file_fields,$file_page)
    {
        if(is_string($file_fields) || is_string($file_page))
        {
            include($file_fields);
            include($file_page);
        }
        else
        {
            throw new Exception("Two filenames needed.");
        }
    }

    function updateCart($new_array)
    {
        if(is_array($new_array))
        {
            foreach($new_array as $field => $value)
            {
                if(preg_match("/item/",$field) && $value>0)
                {
                    $cat_no = substr($field,4);
                    $items_new[] = new Item($cat_no,$value);
                }
            }
            $this->items = @$items_new;
            $_SESSION["items"]=$this->items;
            $this->n_items=sizeof($this->items);
            $this->message="Shopping cart contains {$this->n_items} items.";
        }
        else
        {
            throw new Exception("Parameter is not an Array");
        }
    }
}
?>