<?php
/*
 * Datei: functions.inc
 * reason: Determination of the country codes for the
 *        Shipping data form.
 */
function getStateCode()
{
    $stateCode = array( 1 => 
        "BE",
        "DA",
        "DE",
        "FO",
        "FI",
        "FR",
        "EL",
        "GA",
        "IT",
        "LI",
        "LU",
        "MO",
        "NL",
        "AT",
        "PL",
        "SV",
        "CH",
        "ES",
        "TR" );
    return $stateCode;
}
function getStateName()
{
    $stateName = array( 1 => 
        "Belgium",
        "Danemark",
        "Germany",
        "Faroer Isles",
        "Finnland",
        "France",
        "Greece",
        "ireland",
        "Italy",
        "Liechtenstein",
        "Luxenbourg",
        "Monaco",
        "Netherlands",
        "Austria",
        "Poland",
        "Sweden",
        "Switzerland",
        "Spain",
        "Turkey" );
    return $stateName;
}
?>