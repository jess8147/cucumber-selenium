Const ForReading=1 
Const ForWriting=2 
Set objFSO = CreateObject("Scripting.FileSystemObject") 
projectFolder = objFSO.getFolder(WScript.Arguments(0))
Set featureFolder = objFSO.getFolder(projectFolder & "\src\test\java\org\asite\automation\adoddle\p2\features\")
configFilePath = projectFolder & "\config.properties" 

Set cFile = objFSO.OpenTextFile(configFilePath, ForReading, True) 

Do While Not cFile.AtEndofStream 
  cLine = cFile.ReadLine 
  If InStr(cLine,"application.test.dcs")> 0 Then
		If Instr(cLine, "UK") > 0 Then
			ukArg = "UK"
		Else
			ukArg = ""
		End If
		If Instr(cLine, "US") > 0 Then
			usArg = "US"
		Else
			usArg = ""
		End If
		If Instr(cLine, "AU") > 0 Then
			ausArg = "AU"
		Else
			ausArg = ""
		End If
  End If 
 Loop 
 cFile.Close
 
For Each file In featureFolder.Files
    replaceParameterizedDataCenter(file)
Next

Sub replaceParameterizedDataCenter(filePath)
	Set myFile = objFSO.OpenTextFile(filePath, ForReading, True) 
	Set myTemp = objFSO.OpenTextFile(filePath & ".tmp", ForWriting, True) 
	strContents = myFile.ReadAll
	arrLines = Split(strContents, vbNewLine)
	'Do While Not myFile.AtEndofStream 
	For i=0 To UBound(arrLines)
		'myLine = myFile.ReadLine
		myLine = arrLines(i)
		If InStr(myLine,"@P2T2")> 0 OR InStr(myLine,"@P2T3")> 0 OR InStr(myLine,"@P2T4")> 0 Then
			If InStr(myLine, "@P2T2") > 0 Then
				myline = Replace(myLine, "@P2T2", "@P2Test1")
			ElseIf InStr(myLine, "@P2T3") > 0 Then
				myline = Replace(myLine, "@P2T3", "@P2Test1")
			ElseIf InStr(myLine, "@P2T4") > 0 Then
				myline = Replace(myLine, "@P2T4", "@P2Test1")
			Else
				myFile = myLine
			End If
		End If
		
		If InStr(myLine,"@P2T5")> 0 OR InStr(myLine,"@P2T6")> 0 OR InStr(myLine,"@P2T7")> 0 Then
			If InStr(myLine, "@P2T5") > 0 Then
				myline = Replace(myLine, "@P2T5", "@P2Test2")
			ElseIf InStr(myLine, "@P2T6") > 0 Then
				myline = Replace(myLine, "@P2T6", "@P2Test2")
			ElseIf InStr(myLine, "@P2T7") > 0 Then
				myline = Replace(myLine, "@P2T7", "@P2Test2")
			Else
				myFile = myLine
			End If
		End If
		
		If InStr(myLine,"|UK|")> 0 OR  InStr(myLine,"|US|")> 0 OR  InStr(myLine,"|AUS|")> 0 Then
			If InStr(myLine, "#") = 1 Then
			    myLine = Replace(myLine,"#", "", 1, 1)
			End If
			If Instr(myLine, "|UK|") >0 AND (Not(Instr(ukArg,"UK") > 0)) Then
				myLine = Replace(myLine,"|UK|", "#|UK|")
			End If
			If Instr(myLine, "|US|") >0 AND (Not(Instr(usArg,"US") > 0)) Then
				myLine = Replace(myLine,"|US|", "#|US|")
			End If
			If Instr(myLine, "|AUS|") >0 AND (Not(Instr(ausArg,"AU") > 0)) Then
				myLine = Replace(myLine,"|AUS|", "#|AUS|")
			End If
		End If 
		if(i < UBound(arrLines)) Then
			myTemp.WriteLine myLine 
		Else
			myTemp.Write myLine
		End If
	Next
	'Loop
	myFile.Close
	myTemp.Close 
	objFSO.CopyFile filePath&".tmp", filePath
	objFSO.DeleteFile(filePath&".tmp")
end Sub