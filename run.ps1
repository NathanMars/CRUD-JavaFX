# Run JavaFX application without warnings
$javaPath = "C:\Program Files\Java\jdk-24.0.1\bin\java.exe"
$vmArgs = @(
    "--enable-native-access=javafx.graphics"
    "--add-opens", "java.base/java.lang=ALL-UNNAMED"
    "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED"
)

# Get the argfile path from the most recent run
$argFile = Get-ChildItem "C:\Users\nmarques\AppData\Local\Temp\cp_*.argfile" | Sort-Object LastWriteTime -Descending | Select-Object -First 1

if ($argFile) {
    & $javaPath $vmArgs "@$($argFile.FullName)" "-m" "Application.crud/Application.Main"
} else {
    Write-Host "Error: Could not find argfile. Please run the application from VS Code first to generate the classpath."
}
