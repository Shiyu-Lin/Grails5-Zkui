<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
%{--    <meta name="layout" content="main"/>--}%
    <title>Welcome to Grails</title>
    <z:resources/>

</head>
<body>
%{--Functional tags--}%

%{--<z:window title="Grid Demo" border="normal" width="360px">--}%
%{--    hello world!!--}%

%{--    <z:textbox value="text..."/>--}%
%{--    <z:textbox value="secret" type="password"/>--}%

%{--    --}%

%{--</z:window>--}%

<z:window title="Grid Demo" border="normal">

    <z:a href="http://www.zkoss.org" label="Visit ZK!"/>
    <z:div align="left" width="300px">
        <z:doublebox/>
    </z:div>
    <z:div align="right" width="300px">
        <z:doublebox/>
    </z:div>


    <z:caption label="This is a caption"/>
    <z:groupbox width="300px">
        <z:caption label="fruits"/>
        <z:radiogroup>
            <z:radio label="Apple"/>
            <z:radio label="Orange"/>
            <z:radio label="Banana"/>
        </z:radiogroup>
    </z:groupbox>

    <z:grid>
        <z:columns sizable="true">
            <z:column label="Type" width="50px"/>
            <z:column label="Content"/>
        </z:columns>
        <z:rows>
            <z:row>
                <z:label value="File:"/>
                <z:textbox width="99%"/>
            </z:row>
            <z:row>
                <z:label value="Type:"/>
                <z:hbox>
                    <z:listbox rows="1" mold="select">
                        <z:listitem label="Java Files,(*.java)"/>
                        <z:listitem label="All Files,(*.*)"/>
                    </z:listbox>
                    <z:button label="Browse..."/>
                </z:hbox>
            </z:row>
            <z:row>
                <z:label value="Options:"/>
                <z:textbox rows="3" width="99%"/>
            </z:row>
        </z:rows>
    </z:grid>
</z:window>





%{--Tags with issues--}%










</body>
</html>
