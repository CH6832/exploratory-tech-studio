<?php
/**
 * Navigationsleiste der Webseite
 * Quelle der Vorlage @link https://getbootstrap.com/docs/4.1/components/navbar/
 */

printf( "<!-- navbar -->
<nav class='navbar navbar-expand-lg navbar-light bg-light'>
    <a class='navbar-brand' href='index.php'>Reisebüro Starlines</a>
    <!-- /blinking_button: Get to the travel offers -->
    <a class='navbar-brand' href='voyages.php' style='color:yellow;'><blink>Unsere Angebote</blink></a>
    <!-- /blinking_button -->
    <button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>
        <span class='navbar-toggler-icon'></span>
    </button>
</nav>
<!-- /navbar -->" );
printf( "<br><br>" );
?>