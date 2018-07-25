Dim rawFilePath, saveAsName
Set rawFilePath = CreateObject("Scripting.Dictionary")
Set saveAsName = CreateObject("Scripting.Dictionary")
rawFilePath = Wscript.Arguments(0)
saveAsName = Wscript.Arguments(1)
Set Application = CreateObject("Excel.Application") 
Application.DisplayAlerts = False
Application.visible=False
Set resultFile = Application.Workbooks.Open(rawFilePath)
resultFile.CheckCompatibility = True
Application.DisplayAlerts = False
resultFile.SaveAs saveAsName, 56
resultFile.Close True
Application.Quit
Set Application = Nothing