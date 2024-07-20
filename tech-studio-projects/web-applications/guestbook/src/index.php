<?php
/**
* Homepage: Entry point of the website.
*/
require_once "includes/bootstrap_header.php";
require_once "classes/Database.php";
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
            <div class="row">
                <div class="col"></div>
                <div class="col-14">
                    <?php echo str_repeat( "<br>",3 ); ?>
                    <h1 style='text-align:center;font-family:"Brush Script MT";font-size:60px'>Your life is a story<br></h1>
                    <?php echo str_repeat( "<br>",3 ); ?>
                    <h1 style='text-align:center;font-family:"Brush Script MT";font-size:80px'><b>Share it with us ...</b></h1>
                    <?php echo str_repeat( "<br>",3 ); ?>
                </div>
                <div class="col"></div>
            </div>
            <div class="row">
                <div class="col">
                </div>
                <div class="col-14">
                    <a href="gaestebuch.php">
                        <?php echo str_repeat( "<br>",2 ); ?>
                        <img src="../resources/img/book.png" width="360px" height="260px"/>
                        <?php echo str_repeat( "<br>",2 ); ?>
                        <?php echo str_repeat( "<br>",3 ); ?>
                    </a>
                    <br>
                </div>
                <div class="col"></div>
            </div>
        </div>
        <?php
        require_once "includes/bootstrap_footer.php";
        ?>
	</body>
    <!-- /body: Contains the Greeting text, name of travel agency and additional offers -->

</html>