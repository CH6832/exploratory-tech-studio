<?php
/*
 * file: catalog_index_page.inc
 * reason: Displays the categories of the catalog
 */
include_once("fields_index_page.inc");
?>
<html>
    <head>
        <title>
            <?php echo $page['title'] ?>
        </title>
    </head>
<?php
/* Text vor Formular anzeigen */
echo "<form action='$_SERVER[PHP_SELF]' method='POST'>\n
        <p style='text-align:right'>\n
        <input type='submit' name='Cart' value='Show shopping cart'>\n
        </form>\n";
echo "<div style='margin-left:.1in'>
        <h1 style='text-align:center'>{$page['top']}</h1>
        <p style='font-style:italic'>{$page['top2']}<hr>";
/* Create form with the selection list */
echo "<form action='$_SERVER[PHP_SELF]' method='POST'>\n";
foreach($food_categories as $key => $subarray)
{
    echo "<h3>$key</h3>";
    echo "<ul>";
    foreach($subarray as $type)
    {
        echo "<input type='radio' name='interest' value='$type'><b>$type</b><br>\n";
    }
    echo "</ul>";
}
echo "<input type='submit' name='Products' value='Choose a category'>\n</form>\n";
?>
</div>
<hr>
<p style="text-align:center;font-size=-1"><?php echo $page['bottom'] ?></p>
</body>
</html>