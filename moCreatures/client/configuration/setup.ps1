param
(
    [string]$_target = "error"
)

# configuration globale
. ..\..\configuration\globalSetup.ps1 $_target

logWarning "Local configuration..."
[datetime]$startTime = date

# répertoires
$directories.mod.configuration += @{
    "resources" = @{ "directory" = $directories.mod.configuration.directory + "\resources" }
}
$directories.mod.configuration.resources += @{
    "textures" = $directories.mod.configuration.resources.directory + "\moCreatures\textures";
    "resources" = $directories.mod.configuration.resources.directory + "\resources"
}
foreach ($_target in $targets)
{
    $directories.mod.$_target.sources += @{
        "textures" = $directories.mod.configuration.resources.textures.Replace($directories.mod.configuration.resources.directory, $directories.mod.$_target.sources.directory)
    }
    foreach ($_package in $directories.vanilla.src.Client.packages.Keys)
    {
        $directories.mod.$_target.sources.packages += @{ $_package = $directories.vanilla.src.Client.packages.$_package.Replace($directories.vanilla.src.Client.directory, $directories.mod.$_target.sources.directory) }
    }
    $directories.mod.$_target.output.bin = (Split-Path -Parent $directories.mod.$_target.output.Client)
    $directories.mod.$_target.output.mods = $directories.mod.$_target.output.directory + "\mods"
}

#fichiers
$files.mod += @{ "startFix" = $directories.mcp.fixes.start.Replace($directories.mcp.fixes.directory, $directories.mod.configuration.directory) }
$version = Get-Content $files.mod.version
foreach ($_target in $targets)
{
    $files.mod.$_target.archives = @{
        "dependances" = $directories.mod.$_target.output.mods + "\mod_Dependances.jar";
        "mod" = $directories.mod.$_target.output.mods + "\mod_smpMoCreaturesClient.jar";
    }
}
$files.publicPackage = $files.mod.$_target.archives.mod.Replace($directories.mod.$_target.output.mods, $directories.packages).Replace(".jar", "_$version.jar")

# fonctions
. ($directories.mod.configuration.directory + "\functions.ps1")

[datetime]$endTime = date
logWarning ("Done in " + (New-TimeSpan -Start $startTime -End $endTime))