param
(
    [bool]$chain = $false
)

# configuration
. .\configuration\setup.ps1 "debug"

logWarning "Updating debug..."
[datetime]$startTime = date

# préparation
restoreFixes

# import des sources de core
importSources $directories.mod.core.sources.directory

# on ne build pas le mod si l'on est en train d'enchaîner les scripts
if (!$chain)
{
    buildMod
}

[datetime]$endTime = date
logWarning ("Done in " + (New-TimeSpan -Start $startTime -End $endTime))

# on ne met pas en pause le script si l'on est en train d'enchaîner les scripts
if (!$chain)
{
    sleep 5
}
