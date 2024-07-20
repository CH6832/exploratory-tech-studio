<?php
/**
* Homepage: Entry point of the website.
*/
require_once "includes/bootstrap_header.php";
require_once "includes/include_db.php"
?>

<!DOCTYPE html>

	<!-- header: Include CSS files and necessary bootstrap files form the web --> 
	<head>
		<title>Guestbook</title>
		<meta charset="UTF-8">
		<?php require_once "includes/include_bootstrap.php"; ?>
		<link rel="stylesheet" href="../resources/css/blink.css">
	</head>
	<!-- /header -->

	<!-- body: Contains the Greeting text, name of travel agency and additional offers -->
	<body style="width:auto;background-color:whitesmoke;">
        <div class="container">

            <!-- Enter data -->
            <div class="row">
                <div class="col-18">
                <main>
                    <?php
                    $vorname="";
                    $nachname="";
                    $kommentar="";
                    if(isset($_POST["eintragen"]))
                    {
                        $vorname=strip_tags($_POST["vorname"]);
                        $nachname=$_POST["nachname"];
                        $kommentar=$_POST["kommentar"];

                        $sql="INSERT INTO kommentare
                        (vorname,nachname,kommentar)
                        VALUES
                        (:vorname,:nachname,:kommentar)";
                        
                        $abfrage=$db->prepare($sql);
                        $abfrage->bindParam(":vorname",$vorname);
                        $abfrage->bindParam(":nachname",$nachname);
                        $abfrage->bindParam(":kommentar",$kommentar);
                        $abfrage->execute();
                    }
                    $vorname="";
                    $nachname="";
                    $kommentar="";
                    ?>
                    <br>
                    <h2 style='text-align:center;font-family:"Brush Script MT";font-size:50px'>Lassen Sie einen Kommentar hier ... <br></h2>
                    <br>
                    <div id="wrapper">
                        <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" method="post">
                        Ihr Vorname:<br>
                        <input type="text" name="vorname" value="<?php echo $vorname;?>"><br>
                        Ihr Nachname:<br>
                        <input type="text" name="nachname" value="<?php echo $nachname;?>"><br><br>
                        Kommentar:<br>
                        <textarea type="kommentar" name="kommentar" rows="5" value="<?php echo $kommentar; ?>"></textarea><br>
                        <br>
                        <input type="submit" name="eintragen" value="eintragen"><br>
                        </form>
                        </main>
                    </div>
                <div class="col-14">
                    <br>
                    <hr width="100%">
                    <br>
                    <h1 style='text-align:center;font-family:"Brush Script MT";font-size:80px'>Gästebucheinträge<br></h1>
                    
                    <?php
                    $sql="SELECT * FROM kommentare";
                    $abfrage=$db->query($sql);
                    $abfrage_headers = $db->query($sql);

                    # Count rows in a table:
                    #-------------------------
                    $con = mysqli_connect("localhost","root","","gaestebuch");
                    if( $result = mysqli_query($con,$sql))
                    {
                        $rowcount = mysqli_num_rows($result);
                    }
                    printf("<h3 style='text-align:center;font-family:'Brush Script MT';font-size:40px'>Gesamt: ".$rowcount."</h3>");
                    ?>
                    <br><br>
                    <?php
                    echo "<table class='table table-striped'>";
                    echo "<thead class='table-dark'>";
                    $fields = array_keys( $abfrage_headers->fetch( PDO::FETCH_ASSOC ) );
                    foreach( $fields as $value )
                    {
                        $value_upper = strtoupper( $value );
                        echo "<td>".$value_upper."</td>";
                    }
                    echo "</thead>";
                    echo "<tbody>";
                    while( $row= $abfrage->fetch() )
                    {
                        echo "<tr>";
                        echo "<td>".$row["id"]."</td>";
                        echo "<td>".$row["date"]."</td>";
                        echo "<td>".$row["vorname"]."</td>";
                        echo "<td>".$row["nachname"]."</td>";
                        echo "<td>".$row["kommentar"]."</td>";
                        echo "</tr>";
                    }
                    echo "</tbody>";
                    echo "</table>";
                    ?>
                </div>
                <div class="col"><!-- right border --></div>
            </div>
            <!-- /Enter data -->

            <!-- Output data -->
            <div class="row">
                <div class="col"><!-- left border --></div>
                    <div class="col-14">
                        <!-- Enter database output here -->
                    </div>
                <div class="col"><!-- right border --></div>
            </div>
        </div>
        <!-- /Output data -->

        <br><br><br><br><br>
        <?php
        require_once "includes/bootstrap_footer.php";
        ?>
    </body>
    <!-- /body: Contains the Greeting text, name of travel agency and additional offers -->

</html>