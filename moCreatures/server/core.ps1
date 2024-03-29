param
(
    [bool]$chain = $false
)

# configuration
. .\configuration\setup.ps1 "core"

logWarning "Updating core..."
[datetime]$startTime = date

# préparation
fixPatches "Server"
addDependances "Server"
restoreFixes
decompile "Server"

logInfo "Importing vanilla packages..."
# récupération des packages décompilés
foreach ($_package in $directories.vanilla.src.Server.packages.Keys)
{
    replace $directories.mod.core.sources.packages.$_package $directories.mcp.OUTPUT.SrcServer.$_package
}

# import des sources de debug
importSources

# restauration de MCP
restoreMcp "Server"

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
