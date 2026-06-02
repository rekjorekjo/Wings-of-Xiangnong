# package.ps1 - ASCII only, compatible with Windows PowerShell 5.1
$ErrorActionPreference = 'Stop'

$Root = Split-Path -Parent $MyInvocation.MyCommand.Path
$ProjectName = Split-Path -Leaf $Root
if ([string]::IsNullOrWhiteSpace($ProjectName)) { $ProjectName = 'project' }
$Timestamp = Get-Date -Format 'yyyyMMdd-HHmmss'

$OutputDir = Join-Path $Root '_upload_packages'
$StageDir = Join-Path $OutputDir ('_staging_' + $Timestamp)
$ZipPath = Join-Path $OutputDir ($ProjectName + '-' + $Timestamp + '.zip')

$ExcludeDirs = @('.git', '.idea', '.vscode', 'node_modules', 'target', 'dist', 'unpackage', '.gradle', '.mvn', '_upload_packages')
$ExcludeFileNames = @('.DS_Store', 'Thumbs.db', 'package-lock.json', '.flattened-pom.xml', 'project.private.config.json', 'project.config.json')
$ExcludeExtensions = @('.zip', '.rar', '.7z', '.tar', '.gz', '.log', '.tmp', '.py')
$ExcludeSensitivePatterns = @('.env.local', '.env.*.local', 'application-prod.yaml', 'application-prod.yml', 'application-dev-secret.yaml', 'application-dev-secret.yml')

function Get-RelativePathSafe {
    param([string] $FullName)
    return ($FullName.Substring($Root.Length) -replace '^[\\/]+', '')
}

function Test-ExcludedFile {
    param([System.IO.FileInfo] $File)

    $relativePath = Get-RelativePathSafe $File.FullName
    $parts = $relativePath -split '[\\/]'

    # Exclude root assets directory only (not admin/src/assets or miniapp/static)
    if ($parts.Count -gt 0 -and $parts[0] -eq 'assets') {
        return $true
    }

    foreach ($dir in $ExcludeDirs) {
        if ($parts -contains $dir) { return $true }
    }

    if ($ExcludeFileNames -contains $File.Name) { return $true }
    if ($ExcludeExtensions -contains $File.Extension.ToLowerInvariant()) { return $true }

    foreach ($pattern in $ExcludeSensitivePatterns) {
        if ($File.Name -like $pattern) { return $true }
    }

    return $false
}

if (!(Test-Path -LiteralPath $OutputDir)) {
    New-Item -ItemType Directory -Path $OutputDir | Out-Null
}

if (Test-Path -LiteralPath $StageDir) {
    Remove-Item -LiteralPath $StageDir -Recurse -Force
}

New-Item -ItemType Directory -Path $StageDir | Out-Null

Write-Host ''
Write-Host 'Collecting files...' -ForegroundColor Cyan

$files = Get-ChildItem -Path $Root -Recurse -File -Force | Where-Object {
    -not (Test-ExcludedFile $_)
}

$count = 0
foreach ($file in $files) {
    $relativePath = Get-RelativePathSafe $file.FullName
    $targetPath = Join-Path $StageDir $relativePath
    $targetDir = Split-Path -Parent $targetPath

    if (!(Test-Path -LiteralPath $targetDir)) {
        New-Item -ItemType Directory -Path $targetDir -Force | Out-Null
    }

    Copy-Item -LiteralPath $file.FullName -Destination $targetPath -Force
    $count++
}

if (Test-Path -LiteralPath $ZipPath) {
    Remove-Item -LiteralPath $ZipPath -Force
}

Write-Host 'Creating zip...' -ForegroundColor Cyan
Compress-Archive -Path (Join-Path $StageDir '*') -DestinationPath $ZipPath -Force
Remove-Item -LiteralPath $StageDir -Recurse -Force

$sizeMb = [Math]::Round((Get-Item -LiteralPath $ZipPath).Length / 1MB, 2)

Write-Host ''
Write-Host 'Package complete.' -ForegroundColor Green
Write-Host ('Zip path: ' + $ZipPath)
Write-Host ('File count: ' + $count)
Write-Host ('Zip size: ' + $sizeMb + ' MB')
