# restaure les correctifs
function restoreFixes()
{
    logInfo "Restoring fixes..."
    
    # si on n'est pas en release, on met à jour le patch sonore
    if ($directories.mod.target -ne $directories.mod.release.directory)
    {
        delete ($directories.mod.$target.sources.directory + "\*.java")
        replace $files.mod.$target.fixes.sound $directories.mcp.fixes.sound
    }

    # mise à jour du patch de démarrage
    replace $files.mod.$target.fixes.start $files.mod.startFix
}