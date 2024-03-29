param
(
    [string]$_target = "error"
)

# configuration globale
. ..\..\configuration\globalSetup.ps1 $_target

logWarning "Local configuration..."
[datetime]$startTime = date

# répertoires
foreach ($_target in $targets)
{
    foreach ($_package in $directories.vanilla.src.Server.packages.Keys)
    {
        $directories.mod.$_target.sources.packages += @{ $_package = $directories.vanilla.src.Server.packages.$_package.Replace($directories.vanilla.src.Server.directory, $directories.mod.$_target.sources.directory) }
    }
    $directories.mod.$_target.output.mods = $directories.mod.$_target.output.directory + "\mods"
}

#fichiers
$version = Get-Content $files.mod.version
foreach ($_target in $targets)
{
    $files.mod.$_target.archives = @{
        "dependances" = $directories.mod.$_target.output.mods + "\mod_Dependances.jar";
        "mod" = $directories.mod.$_target.output.mods + "\mod_smpMoCreaturesServer.jar";
    }
}
$files.publicPackage = $files.mod.$_target.archives.mod.Replace($directories.mod.$_target.output.mods, $directories.packages).Replace(".jar", "_$version.jar")

# fonctions
. ($directories.mod.configuration.directory + "\functions.ps1")

[datetime]$endTime = date
logWarning ("Done in " + (New-TimeSpan -Start $startTime -End $endTime))