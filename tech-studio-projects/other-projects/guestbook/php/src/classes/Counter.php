
<!-- https://www.delftstack.com/howto/php/php-count-rows/ -->

In the example below, the superclass DB contains a connect() method which contians the database connection. The getRowsNumber() function is invoked from anothe php file as:

$testObj = new Test();
$testObj->getRowsNumber()
Example Code:

# php 7.*
<?php
class Test extends DB {
    public function getRowsNumber() {
        $sql = "SELECT COUNT(*) FROM users";
        $stmt = $this->connect()->query($sql);
        $count = $stmt->fetchColumn();
        print $count;
    }
}
